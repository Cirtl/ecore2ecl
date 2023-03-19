package process.wrapper;

import transform.data.tmp.BlockEntity;
import transform.data.tmp.Entity;
import transform.model.Model;
import transform.model.ModelEntity;

import java.util.ArrayList;
import java.util.List;

public class EEModeler {
    private final List<Entity> blockEntities;
    private final List<Entity> assistEntities;  // 辅助实体, 如 DiagramEntity
    private final List<Entity> edgeEntities;

    public EEModeler() {
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

    public Model modelingEntities() {
        Model model = new Model();
        List<ModelEntity> entities = new ArrayList<>();
        blockEntities.forEach(entity -> {
            if (((BlockEntity)entity).getSimpleEClass().getChildren().isEmpty()) {
                entities.add(new ModelEntity(entity));
            }
        });
        assistEntities.forEach(entity -> entities.add(new ModelEntity(entity)));
        edgeEntities.forEach(entity -> entities.add(new ModelEntity(entity)));
        model.setEntityList(entities);
        return model;
    }
}
