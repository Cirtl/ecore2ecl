package transform.model;

import transform.data.tmp.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelConnection extends HashMap<String, Object> {
    private static final String DIRECTION = "direction";
    private static final String SINGLE = "single";
    private static final String NUMBER = "number";
    private static final String L_BOUND = "lowerBound";
    private static final String U_BOUND = "upperBound";
    private static final String TARGETS = "targets";
    private static final String TO = "to";
    private static final String FROM = "from";

    public ModelConnection(Connection connection) {
        this.put(DIRECTION, (connection.isTo() ? TO : FROM));
        if (connection.getLowerBound() == connection.getUpperBound()) {
            this.put(SINGLE, true);
            this.put(NUMBER, connection.getUpperBound());
        } else {
            this.put(SINGLE, false);
            this.put(L_BOUND, connection.getLowerBound() + "");
            this.put(U_BOUND, (connection.getUpperBound() == -1 ? "" : connection.getUpperBound()));
        }
        List<String> targets = new ArrayList<>();
        connection.getTarget().forEach(entity -> targets.add(entity.getName()));
        this.put(TARGETS, targets);
    }
}
