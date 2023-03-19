package process.wrapper;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import transform.data.EGenealogy;
import transform.data.SSData;
import transform.data.SimpleClass;
import transform.model.Model;
import transform.model.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Ecore file change to SS-ECL file
 */
public class ESWrapper {
    private static final String DEFAULT_CONNECTION_GROUP_NAME = "default_name";

    private final EGenealogy genealogy;
    private final ESModeler modeler;

    public ESWrapper(EPackage ePackage) {
        this.genealogy = new EGenealogy(ePackage);
        this.modeler = new ESModeler();
    }

    private void filterEntity() {
        for (SimpleClass simpleClass: this.genealogy.getSimpleEClasses()) {
            // 该实体的长辈实体
            List<String> parents = new ArrayList<>(), elders = new ArrayList<>();
            simpleClass.getParents().forEach(k -> {
                if (((SimpleClass) k).isInterface()) elders.add(k.getName());
                else parents.add(k.getName());
            });

            SSData entity = new SSData(
                    simpleClass.getName(),
                    parents,
                    elders,
                    new ArrayList<>()
            );
            entity.refConnectionGroups().add(new SSData.ConnectionGroup(DEFAULT_CONNECTION_GROUP_NAME));
            modeler.getEntities().put(entity.getName(), entity);
        }
    }

    /**
     * 处理类的属性和关系
     * EAttribute作为属性加入
     * EReference作为关系, 引入辅助实体
     */
    private void filterRelation() {
        for (SimpleClass simpleClass: this.genealogy.getSimpleEClasses()) {
            SSData entity = this.modeler.getEntities().get(simpleClass.getName());

            List<ModelAttribute> attributes = new ArrayList<>();

            for (EStructuralFeature feature: simpleClass.getSelfEClass().getEStructuralFeatures()) {
                if (feature instanceof EAttribute) attributes.add(new ModelAttribute(feature.getName(), ((EAttribute) feature).getEAttributeType().getName()));
                else if (feature instanceof EReference){
                    SSData target = this.modeler.getEntities().get(((EReference) feature).getEReferenceType().getName());
                    if (((EReference) feature).isContainment()) {
                        buildContainReference(entity, target, feature.getLowerBound(), feature.getUpperBound());
                    } else if (!((EReference) feature).isContainer()) {
                        buildCommonReference(entity, target, feature.getLowerBound(), feature.getUpperBound(), feature.getName());
                    }
                }
            }

            entity.setAttributes(attributes);
        }
    }

    private void buildContainReference(SSData E, SSData e, int lowerBound, int upperBound) {
        String diagram = E.getName() + "_Diagram";
        SSData.ConnectionGroup group = new SSData.ConnectionGroup(DEFAULT_CONNECTION_GROUP_NAME);   // for diagram

        E.refConnectionGroups().get(0).add(new SSData.Connection(
                1,
                1,
                diagram,
                true
        ));
        group.add(new SSData.Connection(
                0,
                1,
                E.getName(),
                false
        ));
        group.add(new SSData.Connection(
                lowerBound,
                upperBound,
                e.getName(),
                true
        ));
        e.refConnectionGroups().get(0).add(new SSData.Connection(
                1,
                1,
                diagram,
                false
        ));

        List<SSData.ConnectionGroup> tmp = new ArrayList<>();
        tmp.add(group);
        this.modeler.getEntities().put(diagram, new SSData(
                diagram,
                new ArrayList<>(),
                new ArrayList<>(),
                tmp
        ));
    }

    private void buildCommonReference(SSData e1, SSData e2, int lowerBound, int upperBound, String relationName) {
        String edge = e1.getName() + "_" + relationName + "Edge";
        SSData.ConnectionGroup group = new SSData.ConnectionGroup(DEFAULT_CONNECTION_GROUP_NAME);   // for diagram

        e1.refConnectionGroups().get(0).add(new SSData.Connection(
                lowerBound,
                upperBound,
                edge,
                true
        ));
        group.add(new SSData.Connection(
                0,
                1,
                e1.getName(),
                false
        ));
        group.add(new SSData.Connection(
                0,
                1,
                e2.getName(),
                true
        ));
        e2.refConnectionGroups().get(0).add(new SSData.Connection(
                0,
                -1,
                edge,
                false
        ));

        List<SSData.ConnectionGroup> tmp = new ArrayList<>();
        tmp.add(group);
        this.modeler.getEntities().put(edge, new SSData(
                edge,
                new ArrayList<>(),
                new ArrayList<>(),
                tmp
        ));
    }

    public Model buildModelData() {
        filterEntity();
        filterRelation();
        return modeler.modelingEntities();
    }

}
