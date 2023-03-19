package transform.model;

import transform.data.SSData;
import transform.data.tmp.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelConnection extends HashMap<String, Object> {
    private static final String DIRECTION = "direction";
    private static final String TO = "to";
    private static final String FROM = "from";

    private static final String L_BOUND = "lowerBound";
    private static final String U_BOUND = "upperBound";
    private static final String TARGETS = "targets";

    public ModelConnection(Connection connection) {
        this.put(DIRECTION, (connection.isTo() ? TO : FROM));
        this.put(L_BOUND, connection.getLowerBound());
        this.put(U_BOUND, connection.getUpperBound());

        List<String> targets = new ArrayList<>();
        connection.getTarget().forEach(entity -> targets.add(entity.getName()));
        this.put(TARGETS, targets);
    }

    public ModelConnection(SSData.Connection connection) {
        this.put(DIRECTION, (connection.isTo() ? TO : FROM));
        this.put(L_BOUND, connection.getLowerBound());
        this.put(U_BOUND, connection.getUpperBound());

        List<String> targets = new ArrayList<>();
        connection.getTargets().forEach(entity -> targets.add(entity.getName()));
        this.put(TARGETS, targets);
    }
}
