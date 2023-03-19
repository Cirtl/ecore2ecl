package transform.model;

import transform.data.SSData;
import transform.data.tmp.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelEntity extends HashMap<String, Object> {
    private static final String NAME = "name";
    private static final String CONNECTIONS_GROUP = "connections_group";

    // optional
    private static final String FUNCTION_LIST = "funcs";
    private static final String VISUALIZATION_LIST = "vis_list";
    private static final String ATTR_LIST = "attributes";
    private static final String EXTEND = "extend";
    private static final String ACTS = "acts";

    public ModelEntity(Entity entity) {
        this.put(NAME, entity.getName());
        List<List<ModelConnection>> group = new ArrayList<>();
        List<ModelConnection> connections = new ArrayList<>();
        entity.getConnections().forEach(connection -> connections.add(new ModelConnection(connection)));
        group.add(connections);
        this.put(CONNECTIONS_GROUP, group);
    }

    public ModelEntity(SSData entity, boolean forECL) {
        this.put(NAME, entity.getName());

        if (forECL) {
            if (entity.getFunctionsBlock() != null) this.put(FUNCTION_LIST, entity.getFunctionsBlock());
            if (entity.getVisualizationBlock() != null) this.put(VISUALIZATION_LIST, entity.getVisualizationBlock());
        } else {
            if (!entity.getAllAttributes().isEmpty()) this.put(ATTR_LIST, entity.getAllAttributes());
        }

        List<ModelConnectionGroup> groupList = new ArrayList<>();
        if (forECL) {
            for (SSData.ConnectionGroup group : entity.getFinalConnectionGroups()) {
                groupList.add(new ModelConnectionGroup(group, forECL));
            }
        } else {
            for (SSData.ConnectionGroup group : entity.refConnectionGroups()) {
                groupList.add(new ModelConnectionGroup(group, forECL));
            }
        }
        this.put(CONNECTIONS_GROUP, groupList);

        if (!forECL) {
            if (!entity.getParents().isEmpty())
                this.put(EXTEND, entity.getParents().get(0).getName());
            if (!entity.getElders().isEmpty()) {
                List<String> acts = new ArrayList<>();
                entity.getElders().forEach(elder -> acts.add(elder.getName()));
                this.put(ACTS, acts);
            }
        }
    }

}
