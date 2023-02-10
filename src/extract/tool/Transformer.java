package extract.tool;

import extract.einfo.Entity;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Transformer {

    public static List<EClass> extractAllEClass(EPackage ePackage) {
        List<EClass> classes = new ArrayList<>();
        for (EClassifier classifier : ePackage.getEClassifiers()) {
            if (classifier instanceof  EClass) {
                classes.add((EClass) classifier);
            }
        }
        return classes;
    }

    public static List<EClass> extractNonAbstractEClass(EPackage ePackage) {
        List<EClass> classes = new ArrayList<>();
        for (EClassifier classifier : ePackage.getEClassifiers()) {
            if (classifier instanceof EClass && !((EClass) classifier).isAbstract()) {
                classes.add((EClass) classifier);
            }
        }
        return classes;
    }

    public static Map<String, Object> mappingEntities(List<Entity> entities) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("entities", entities);
        return map;
    }

}
