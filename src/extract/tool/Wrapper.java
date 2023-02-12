package extract.tool;

import extract.einfo.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import java.util.Map;

public class Wrapper {
    // 构建类继承树
    private final Transformer transformer;
    // 生成实体信息
    private final Modeler modeler;

    public Wrapper(EPackage ePackage) {
        this.transformer = new Transformer(ePackage);
        this.modeler = new Modeler();
    }

    private Entity findBlockEntity(EClass target) {
        return transformer.findSimpler(target).getSelfEntity();
    }

    private void filterBlockEntity() {
        for (SimpleEClass simpleEClass : this.transformer.getSimpleEClasses()) {
            if (transformer.canBeEntity(simpleEClass.getSelfEClass())) {
                BlockEntity tmp = new BlockEntity(simpleEClass);
                simpleEClass.setSelfEntity(tmp);
                modeler.getBlockEntities().add(tmp);
            }
        }
    }

    private void filterRelationEntity() {
        for (Entity entity : modeler.getBlockEntities()) {
            BlockEntity blockEntity = (BlockEntity) entity;
            for (EReference reference : blockEntity.getSimpleEClass().getReferences()) {
                if (transformer.canBeEntity(reference.getEReferenceType())) {
                    Entity target = findBlockEntity(reference.getEReferenceType());
                    if (reference.isContainment()) {
                        buildContainConnection(blockEntity, target, reference.getLowerBound(), reference.getUpperBound());
                    } else if (!reference.isContainer()) {
                        buildRelationConnection(blockEntity, target, reference.getLowerBound(), reference.getUpperBound(), reference.getName());
                    }
                }
            }
        }
    }

    /**
     * 建立实体间包含关系
     * @param E 包含实体
     * @param e 被包含实体
     * @param lowerBound 下界
     * @param upperBound 上界
     */
    private void buildContainConnection(Entity E, Entity e, int lowerBound, int upperBound) {
        DiagramEntity middleDiagram = new DiagramEntity(E.getName() + "_Diagram");
        modeler.getAssistEntities().add(middleDiagram);
        E.addConnection(new Connection(
                1,
                1,
                middleDiagram,
                true
        ));
        middleDiagram.addConnection(new Connection(
                0,
                1,
                E,
                false
        ));
        middleDiagram.addConnection(new Connection(
                lowerBound,
                upperBound,
                e,
                true
        ));
        e.addConnection(new Connection(
                1,
                1,
                middleDiagram,
                false
        ));
    }

    // TODO: 上下界待确定

    /**
     * 建立实体间连接关系
     * @param e1 关系发出实体
     * @param e2 关系接收实体
     * @param lowerBound 下界
     * @param upperBound 上界
     * @param relationName 引用关系名称
     */
    private void buildRelationConnection(Entity e1, Entity e2, int lowerBound, int upperBound, String relationName) {
        EdgeEntity edge = new EdgeEntity(e1.getName() + "_" + relationName + "_Edge");
        modeler.getEdgeEntities().add(edge);

        e1.addConnection(new Connection(
                lowerBound,
                upperBound,
                edge,
                true
        ));
        edge.addConnection(new Connection(
                1,
                1,
                e1,
                false
        ));
        edge.addConnection(new Connection(
                1,
                1,
                e2,
                true
        ));
        e2.addConnection(new Connection(
                0,
                -1,
                edge,
                false
        ));
    }

    public Map<String, Object> filterAllData() {
        filterBlockEntity();
        filterRelationEntity();
        return modeler.modelingEntities();
    }

}
