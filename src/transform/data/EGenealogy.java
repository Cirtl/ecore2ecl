package transform.data;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EGenealogy {
    private final EPackage ePackage;
    // name-SimpleClass 键值对
    private final Map<String, SimpleClass> simpleClassMap;
    // 记录包中的类继承关系
    private final Map<String, List<EClass>> inherits;
    private final List<SimpleClass> simpleClasses;

    public EGenealogy(EPackage ePackage) {
        this.ePackage = ePackage;
        this.inherits = new HashMap<>();
        this.simpleClassMap = new HashMap<>();
        this.simpleClasses = new ArrayList<>();
        initInherits();
        initSimpleEClasses();
    }

    private void initInherits() {
        for (EClass possibleChildClass : extractAllEClass()) {
            for (EClass parentClass : possibleChildClass.getESuperTypes()) {
                if (!this.inherits.containsKey(parentClass.getName())) {
                    this.inherits.put(parentClass.getName(), new ArrayList<>());
                }
                this.inherits.get(parentClass.getName()).add(possibleChildClass);
            }
        }
    }

    private void initSimpleEClasses() {
        for (EClass eClass : extractAllEClass()) {
            simpleClasses.add(simplifyEClass(eClass));
        }
        for (SimpleClass simpleClass : simpleClasses) {
            if (inherits.containsKey(simpleClass.getName())) {
                for (EClass eClass : inherits.get(simpleClass.getName())) {
                    simpleClass.getChildren().add(simpleClassMap.get(eClass.getName()));
                }
            }
        }
    }

    private SimpleClass simplifyEClass(EClass eClass) {
        if (this.simpleClassMap.containsKey(eClass.getName())) return this.simpleClassMap.get(eClass.getName());

        List<SourceData> parents = new ArrayList<>();
        if (!eClass.getESuperTypes().isEmpty()) {
            for (EClass parent : eClass.getESuperTypes()) {
                parents.add(simplifyEClass(parent));
            }
        }
        SimpleClass ret = new SimpleClass(eClass, parents, eClass.isInterface());
        this.simpleClassMap.put(eClass.getName(), ret);
        return ret;
    }

    public List<EClass> extractAllEClass() {
        List<EClass> classes = new ArrayList<>();
        for (EClassifier classifier : this.ePackage.getEClassifiers()) {
            if (classifier instanceof  EClass) {
                classes.add((EClass) classifier);
            }
        }
        return classes;
    }

    public List<SimpleClass> getSimpleEClasses() {
        return simpleClasses;
    }

    public SimpleClass findSimpler(EClass eClass) {
        return simpleClassMap.get(eClass.getName());
    }

    // 判断该类是否可实例化, 或者该类的子类中是否存在可实例化的类
    public boolean canBeEntity(EClass target) {
        if (target.isAbstract()) {
            for (EClass child : inherits.get(target.getName())) {
                if (canBeEntity(child)) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

}
