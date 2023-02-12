package extract.minfo;

import extract.einfo.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelEntity extends HashMap<String, Object> {
    public ModelEntity(Entity entity) {
        put("name", entity.getName());
//        if (entity instanceof EdgeEntity) {
//            put("edge", true);
//            List<String> froms = new ArrayList<>();
//            List<String> tos = new ArrayList<>();
//            for (Connection connection : entity.getConnections()) {
//                if (connection.isTo()) {
//                    connection.getTarget().forEach(e -> tos.add(e.getName()));
//                } else {
//                    connection.getTarget().forEach(e -> froms.add(e.getName()));
//                }
//            }
//            put("from")
//        } else {
//            put("edge", false);
            List<ModelConnection> connections = new ArrayList<>();
            entity.getConnections().forEach(connection -> connections.add(new ModelConnection(connection)));
            put("connections", connections);
//        }
    }
}
