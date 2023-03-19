package process.wrapper;

import transform.data.SGenealogy;
import transform.model.Model;
import transform.model.ModelEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * SS-ECL file change to ECL file
 */
public class SEWrapper implements Modeler<Model> {
    private final SGenealogy genealogy;

    public SEWrapper(SGenealogy genealogy) {
        this.genealogy = genealogy;
        this.genealogy.rebuild();
    }

    private List<ModelEntity> modeling() {
        List<ModelEntity> list = new ArrayList<>();
        for (String name: this.genealogy.getValidEntitySet()) {
            list.add(new ModelEntity(this.genealogy.getEntityMap().get(name)));
        }
        return list;
    }

    @Override
    public Model modelingEntities() {
        Model model = new Model();
        model.setStarter(genealogy.config.getStarter());
        model.setMono(genealogy.config.getMono());
        model.setColor(genealogy.config.getColor());
        model.setEntityList(modeling());
        return model;
    }

    public Model buildModelData() {
        return this.modelingEntities();
    }
}
