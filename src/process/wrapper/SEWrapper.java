package process.wrapper;

import transform.data.SGenealogy;
import transform.model.EModel;
import transform.model.ModelEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * SS-ECL file change to ECL file
 */
public class SEWrapper implements Modeler<EModel> {
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
    public EModel modelingEntities() {
        EModel model = new EModel();
        model.setStarter(genealogy.config.getStarter());
        model.setMono(genealogy.config.getMono());
        model.setColor(genealogy.config.getColor());
        model.setEntityList(modeling());
        return model;
    }

    public EModel buildModelData() {
        return this.modelingEntities();
    }
}
