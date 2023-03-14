package transform;

import transform.einfo.SimpleEClass;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassesTree {
    private final EPackage ePackage;
    private final Map<String, SimpleEClass> simpleEClassMap;
    // 记录包中的类继承关系
    private final Map<String, List<EClass>> inherits;
    private final List<SimpleEClass> simpleEClasses;

    public ClassesTree(EPackage ePackage) {
        this.ePackage = ePackage;
        this.inherits = new HashMap<>();
        this.simpleEClassMap = new HashMap<>();
        this.simpleEClasses = new ArrayList<>();
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
            simpleEClasses.add(simplifyEClass(eClass));
        }
        for (SimpleEClass simpleEClass : simpleEClasses) {
            if (inherits.containsKey(simpleEClass.getName())) {
                for (EClass eClass : inherits.get(simpleEClass.getName())) {
                    simpleEClass.getChildren().add(simpleEClassMap.get(eClass.getName()));
                }
            }
        }
    }

    private SimpleEClass simplifyEClass(EClass eClass) {
        if (this.simpleEClassMap.containsKey(eClass.getName())) return this.simpleEClassMap.get(eClass.getName());

        List<SimpleEClass> parents = new ArrayList<>();
        if (!eClass.getESuperTypes().isEmpty()) {
            for (EClass parent : eClass.getESuperTypes()) {
                parents.add(simplifyEClass(parent));
            }
        }
        SimpleEClass ret = new SimpleEClass(eClass, parents);
        this.simpleEClassMap.put(eClass.getName(), ret);
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

    public List<SimpleEClass> getSimpleEClasses() {
        return simpleEClasses;
    }

    public SimpleEClass findSimpler(EClass eClass) {
        return simpleEClassMap.get(eClass.getName());
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
