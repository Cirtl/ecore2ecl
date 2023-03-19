package transform.model;

import transform.data.SSData;
import transform.data.tmp.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelEntity extends HashMap<String, Object> {
    public static final String NAME = "name";
    public static final String CONNECTIONS_GROUP = "connections_group";

    // optional
    public static final String FUNCTION_LIST = "funcs";
    public static final String VISUALIZATION_LIST = "vis_list";

    public ModelEntity(Entity entity) {
        this.put(NAME, entity.getName());
        List<List<ModelConnection>> group = new ArrayList<>();
        List<ModelConnection> connections = new ArrayList<>();
        entity.getConnections().forEach(connection -> connections.add(new ModelConnection(connection)));
        group.add(connections);
        this.put(CONNECTIONS_GROUP, group);
    }

    public ModelEntity(SSData entity) {
        this.put(NAME, entity.getName());

        if (entity.getFunctionsBlock() != null) this.put(FUNCTION_LIST, entity.getFunctionsBlock());
        if (entity.getVisualizationBlock() != null) this.put(VISUALIZATION_LIST, entity.getVisualizationBlock());

        List<List<ModelConnection>> group = new ArrayList<>();
        List<ModelConnection> tmp;
        for (List<SSData.Connection> connections: entity.getConnectionGroups()) {
            tmp = new ArrayList<>();
            for (SSData.Connection connection: connections) {
                tmp.add(new ModelConnection(connection));
            }
            group.add(tmp);
        }
        this.put(CONNECTIONS_GROUP, group);
    }

}
