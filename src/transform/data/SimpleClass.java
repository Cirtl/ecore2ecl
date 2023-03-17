package transform.data;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import transform.model.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class SimpleClass extends SourceData {
    // 保存并处理得到实体间连接关系的 domain
    private final EClass selfEClass;

    public SimpleClass(EClass eClass, List<SourceData> parents, boolean isInterface) {
        super(eClass.getName(), isInterface, parents);
        this.selfEClass = eClass;

        List<ModelAttribute> attributes = new ArrayList<>();
        eClass.getEAllAttributes().forEach(attr -> {
            attributes.add(new ModelAttribute(attr.getName(), attr.getEAttributeType().getName()));
        });
        super.setAttributes(attributes);
    }

    public EClass getSelfEClass() {
        return selfEClass;
    }

    public List<EReference> getReferences() {
        return selfEClass.getEReferences();
    }

}