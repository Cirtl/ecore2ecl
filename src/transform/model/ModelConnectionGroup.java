package transform.model;

import transform.data.SSData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelConnectionGroup extends HashMap<String, Object> {
    private static final String NAME = "name";
    private static final String LIST = "connections";

    public ModelConnectionGroup(SSData.ConnectionGroup group, boolean forECL) {
        this.put(NAME, group.getName());

        List<ModelConnection> tmp = new ArrayList<>();
        for (SSData.Connection connection: group) {
            tmp.add(new ModelConnection(connection, forECL));
        }
        this.put(LIST, tmp);
    }
}
