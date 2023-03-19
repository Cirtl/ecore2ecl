package process.wrapper;

import transform.data.SSData;
import transform.model.Model;
import transform.model.ModelEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESModeler implements Modeler<Model> {

    private final Map<String, SSData> entities;

    public ESModeler() {
        this.entities = new HashMap<>();
    }

    public Map<String, SSData> getEntities() {
        return entities;
    }

    private List<ModelEntity> modeling() {
        List<ModelEntity> list = new ArrayList<>();
        entities.values().forEach(entity -> list.add(new ModelEntity(entity, false)));
        return list;
    }

    @Override
    public Model modelingEntities() {
        for (SSData entity: this.entities.values()) {
            entity.rebuild(this.entities);
        }
        Model model = new Model();
        model.setEntityList(modeling());
        return model;
    }
}
