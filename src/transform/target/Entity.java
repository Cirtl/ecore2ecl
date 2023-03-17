package transform.target;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private final String name;
    private final List<Connection> connections;

    public Entity(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void addConnection(Connection connection) {
        this.connections.add(connection);
    }

}
