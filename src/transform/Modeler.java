package transform;

import transform.einfo.BlockEntity;
import transform.einfo.Entity;
import transform.minfo.ModelEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modeler {
    private final List<Entity> blockEntities;
    private final List<Entity> assistEntities;
    private final List<Entity> edgeEntities;

    public Modeler() {
        this.blockEntities = new ArrayList<>();
        this.assistEntities = new ArrayList<>();
        this.edgeEntities = new ArrayList<>();
    }

    public List<Entity> getBlockEntities() {
        return blockEntities;
    }

    public List<Entity> getAssistEntities() {
        return assistEntities;
    }

    public List<Entity> getEdgeEntities() {
        return edgeEntities;
    }

    public Map<String, Object> modelingEntities() {
        Map<String, Object> map = new HashMap<>();
        List<ModelEntity> models = new ArrayList<>();
        blockEntities.forEach(entity -> {
            if (((BlockEntity)entity).getSimpleEClass().getChildren().isEmpty()) {
                models.add(new ModelEntity(entity));
            }
        });
        assistEntities.forEach(entity -> models.add(new ModelEntity(entity)));
        edgeEntities.forEach(entity -> models.add(new ModelEntity(entity)));
        map.put("entities", models);
        return map;
    }
}
