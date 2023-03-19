package transform.data;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import transform.data.tmp.Entity;
import transform.model.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class SimpleClass extends SourceData {

    // 当且仅当在作为 parent 时起作用, 用于标志该对象属于 extends/implements
    private boolean isInterface;

    // 保存并处理得到实体间连接关系的 domain
    private final EClass selfEClass;
    private Entity selfEntity;

    public SimpleClass(EClass eClass, List<SourceData> parents, boolean isInterface) {
        super(eClass.getName(), parents);
        this.isInterface = isInterface;
        this.selfEClass = eClass;

        List<ModelAttribute> attributes = new ArrayList<>();
        eClass.getEAllAttributes().forEach(attr -> attributes.add(new ModelAttribute(attr.getName(), attr.getEAttributeType().getName())));
        super.setAttributes(attributes);
    }

    public void setInterface() {
        this.isInterface = true;
    }

    public boolean isInterface() {
        return isInterface;
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

    public List<EReference> getReferences() {
        return selfEClass.getEReferences();
    }

}
