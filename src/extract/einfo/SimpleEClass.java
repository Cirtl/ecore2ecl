package extract.einfo;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import java.util.ArrayList;
import java.util.List;

public class SimpleEClass {
    private final String name;
    private final EClass selfEClass;
    private final List<SimpleEClass> parents;
    private final List<SimpleEClass> children;
    private Entity selfEntity;

    public SimpleEClass(EClass eClass, List<SimpleEClass> parents) {
        this.name = eClass.getName();
        this.selfEClass = eClass;
        this.parents = parents;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public EClass getSelfEClass() {
        return selfEClass;
    }

    public void setSelfEntity(Entity selfEntity) {
        this.selfEntity = selfEntity;
    }

    public Entity getSelfEntity() {
        return selfEntity;
    }

    public List<SimpleEClass> getParents() {
        return parents;
    }

    public List<SimpleEClass> getChildren() {
        return children;
    }

    public List<SimpleEClass> getFinalChildren() {
        List<SimpleEClass> ret = new ArrayList<>();
        for (SimpleEClass child : children) {
            if (child.getChildren().isEmpty()) {
                ret.add(child);
            } else {
                ret.addAll(child.getFinalChildren());
            }
        }
        return ret;
    }

    public List<EReference> getReferences() {
        return selfEClass.getEReferences();
    }
}
