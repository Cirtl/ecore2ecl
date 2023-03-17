package transform.target;

import transform.data.SimpleClass;

import java.util.ArrayList;
import java.util.List;

public class BlockEntity extends Entity {
    private final SimpleClass simpleClass;

    public BlockEntity(SimpleClass simpleClass) {
        super(simpleClass.getName());
        this.simpleClass = simpleClass;
    }

    public SimpleClass getSimpleEClass() {
        return simpleClass;
    }

    @Override
    public List<Connection> getConnections() {
        List<Connection> ret = new ArrayList<>(super.getConnections());
        simpleClass.getParents().forEach(parent -> {
            ret.addAll(parent.getSelfEntity().getConnections());
        });
        return ret;
    }

    public List<Entity> getAllFinalEntity() {
        List<Entity> ret = new ArrayList<>();
        if (simpleClass.getChildren().isEmpty()) {
            ret.add(this);
        } else {
            simpleClass.getFinalChildren().forEach(child -> {
                ret.add(child.getSelfEntity());
            });
        }
        return ret;
    }
}
