package extract.tool;

import extract.einfo.Connection;
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
        Map<String, Object> map = new HashMap<>();
        List<ModelEntity> models = new ArrayList<>();
        entities.forEach(entity -> models.add(new ModelEntity(entity)));
        map.put("entities", models);
        return map;
    }

    static class ModelEntity extends HashMap<String, Object> {
        ModelEntity(Entity entity) {
            this.put("name", entity.getName());
            List<ModelConnection> connections = new ArrayList<>();
            entity.getConnections().forEach(connection -> connections.add(new ModelConnection(connection)));
            this.put("connections", connections);
        }
    }

    static class ModelConnection extends HashMap<String, Object> {
        ModelConnection(Connection connection) {
            this.put("direction", (connection.isTo() ? "to" : "from"));
            if (connection.getLowerBound() == connection.getUpperBound()) {
                this.put("single", true);
                this.put("number", connection.getUpperBound());
            } else {
                this.put("single", false);
                this.put("lowerBound", connection.getLowerBound()+"");
                this.put("upperBound", (connection.getUpperBound()==-1 ? "" : connection.getUpperBound()));
            }
            List<String> targets = new ArrayList<>();
            connection.getTarget().forEach(entity -> targets.add(entity.getName()));
            this.put("targets", targets);
        }
    }

}
