package process.wrapper;

import transform.data.SGenealogy;
import transform.data.SSData;
import transform.model.Model;
import transform.model.ModelEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        for (SSData data: this.genealogy.getValidEntitySet()) {
            list.add(new ModelEntity(data, true));
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

    public Set<SSData> entitySet() {
        return this.genealogy.getValidEntitySet();
    }
}
