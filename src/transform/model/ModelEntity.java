package transform.model;

import transform.data.tmp.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelEntity extends HashMap<String, Object> {
    public ModelEntity(Entity entity) {
        put("name", entity.getName());
        List<ModelConnection> connections = new ArrayList<>();
        entity.getConnections().forEach(connection -> connections.add(new ModelConnection(connection)));
        put("connections", connections);
    }
}
