package process.wrapper;

import transform.data.EGenealogy;
import transform.data.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import transform.data.tmp.*;

import java.util.Map;

/**
 * Ecore file change to ECL file
 */
public class EEWrapper {
    // 构建类继承树
    private final EGenealogy genealogy;
    // 生成实体信息
    private final EEModeler eclModeler;

    public EEWrapper(EPackage ePackage) {
        this.genealogy = new EGenealogy(ePackage);
        this.eclModeler = new EEModeler();
    }

    /**
     * 根据EClass对象找到对应的Entity对象
     * @param target EClass目标
     * @return 返回EClass对应的实体
     */
    private Entity findBlockEntity(EClass target) {
        return genealogy.findSimpler(target).getSelfEntity();
    }

    private void filterBlockEntity() {
        for (SimpleClass simpleClass : this.genealogy.getSimpleEClasses()) {
            if (genealogy.canBeEntity(simpleClass.getSelfEClass())) {
                BlockEntity tmp = new BlockEntity(simpleClass);
                simpleClass.setSelfEntity(tmp);
                eclModeler.getBlockEntities().add(tmp);
            }
        }
    }

    private void filterRelationEntity() {
        for (Entity entity : eclModeler.getBlockEntities()) {
            BlockEntity blockEntity = (BlockEntity) entity;
            for (EReference reference : blockEntity.getSimpleEClass().getReferences()) {
                if (genealogy.canBeEntity(reference.getEReferenceType())) {
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
        eclModeler.getAssistEntities().add(middleDiagram);
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
        eclModeler.getEdgeEntities().add(edge);

        e1.addConnection(new Connection(
                lowerBound,
                upperBound,
                edge,
                true
        ));
        edge.addConnection(new Connection(
                0,
                1,
                e1,
                false
        ));
        edge.addConnection(new Connection(
                0,
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
        return eclModeler.modelingEntities();
    }

}
