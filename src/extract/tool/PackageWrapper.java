package extract.tool;

import extract.einfo.BlockEntity;
import extract.einfo.Connection;
import extract.einfo.EdgeEntity;
import extract.einfo.Entity;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PackageWrapper {
    private final EPackage ePackage;
    // 记录包中的类继承关系
    private final Map<String, List<EClass>> inherits;
    // 保存需要生成的实体信息
    private final List<Entity> blockEntities;
    private final List<Entity> edgeEntities;

    public PackageWrapper(EPackage ePackage) {
        this.ePackage = ePackage;
        this.inherits = new HashMap<>();
        this.blockEntities = new ArrayList<>();
        this.edgeEntities = new ArrayList<>();
        initInherits();
    }

    private void initInherits() {
        List<EClass> tmp;
        for (EClass possibleChildClass : Transformer.extractAllEClass(this.ePackage)) {
            tmp = possibleChildClass.getESuperTypes();
            for (EClass parentClass : tmp) {
                if (!this.inherits.containsKey(parentClass.getName())) {
                    this.inherits.put(parentClass.getName(), new ArrayList<>());
                }
                this.inherits.get(parentClass.getName()).add(possibleChildClass);
            }
        }
/*
        检查函数输出
        for (String s : this.inherits.keySet()) {
            System.out.println(s);
            for (EClass eClass : this.inherits.get(s)) {
                System.out.println(eClass);
            }
        }
*/
    }

    // 实体仅考虑不含子类的类, 所有对父类的引用最终需要转为对其子类的引用
    private boolean canBeEntity(EClass eClass) {
        return (!eClass.isAbstract()) && (!this.inherits.containsKey(eClass.getName()));
    }

    private Entity findBlockEntity(EClass target) {
        for (Entity entity : this.blockEntities) {
            if (entity.getName().equals(target.getName())) {
                return entity;
            }
        }
        System.out.println("出现未确认类实体的寻找!");
        System.exit(0);
        return null;
    }

    /**
     * 过滤合并得到目标类的所有最终子类, 用以作为实体转换目标
     * @param root cannot be entity
     * @return canBeEntity list
     */
    private List<EClass> filterFinalChildEClass(EClass root) {
        Queue<EClass> waitingEClassQueue = new ConcurrentLinkedQueue<>();
        List<EClass> finalTargetEClassList = new ArrayList<>();
        for (EClass eClass : this.inherits.get(root.getName())) {
            if (canBeEntity(eClass)) {
                finalTargetEClassList.add(eClass);
            } else {
                waitingEClassQueue.add(eClass);
            }
        }
        while (!waitingEClassQueue.isEmpty()) {
            for (EClass eClass : this.inherits.get(waitingEClassQueue.poll().getName())) {
                if (canBeEntity(eClass)) {
                    finalTargetEClassList.add(eClass);
                } else {
                    waitingEClassQueue.add(eClass);
                }
            }
        }
        return finalTargetEClassList;
    }

    private void filterBlockEntity() {
        for (EClass eClass : Transformer.extractAllEClass(this.ePackage)) {
            if (canBeEntity(eClass)) {
                this.blockEntities.add(new BlockEntity(eClass));
            }
        }
    }

    private void filterRelationEntity() {
        for (Entity blockEntity : this.blockEntities) {
            BlockEntity entity = (BlockEntity) blockEntity;
            for (EReference reference : entity.getEClass().getEAllReferences()) {
                EClass target = reference.getEReferenceType();
                List<Entity> targetEntity = new ArrayList<>();
                // 如果该引用为组合关系
                if (reference.isContainment()) {
                    if (canBeEntity(target)) {
                        targetEntity.add(findBlockEntity(target));
                    } else {
                        filterFinalChildEClass(target).forEach(eClass -> targetEntity.add(findBlockEntity(eClass)));
                    }
                    buildContainConnection(entity, targetEntity, reference.getLowerBound(), reference.getUpperBound());
                }
                // 如果该引用为一般连接关系
                else {
                    if (canBeEntity(target)) {
                        targetEntity.add(findBlockEntity(target));
                    } else {
                        filterFinalChildEClass(target).forEach(eClass -> targetEntity.add(findBlockEntity(eClass)));
                    }
                    buildRelationConnection(entity, targetEntity, reference.getLowerBound(), reference.getUpperBound(), reference.getName());
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
    private void buildContainConnection(Entity E, List<Entity> e, int lowerBound, int upperBound) {
        E.addConnection(new Connection(
                lowerBound,
                upperBound,
                e,
                true,
                true
        ));
        for (Entity entity : e) {
            entity.addConnection(new Connection(
                    1,
                    1,
                    E,
                    false,
                    true
            ));
        }
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
    private void buildRelationConnection(Entity e1, List<Entity> e2, int lowerBound, int upperBound, String relationName) {
        EdgeEntity edge = new EdgeEntity(e1.getName() + "_" + relationName + "_Edge");
        this.edgeEntities.add(edge);

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
        for (Entity entity : e2) {
            entity.addConnection(new Connection(
                    1,
                    1,
                    edge,
                    false
            ));
        }
    }

    public Map<String, Object> filterAllData() {
        filterBlockEntity();
        filterRelationEntity();
        List<Entity> list = new ArrayList<>(this.blockEntities);
        list.addAll(this.edgeEntities);
        return Transformer.mappingEntities(list);
    }

}
