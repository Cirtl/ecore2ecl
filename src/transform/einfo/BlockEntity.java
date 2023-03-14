package transform.einfo;

import java.util.ArrayList;
import java.util.List;

public class BlockEntity extends Entity {
    private final SimpleEClass simpleEClass;

    public BlockEntity(SimpleEClass simpleEClass) {
        super(simpleEClass.getName());
        this.simpleEClass = simpleEClass;
    }

    public SimpleEClass getSimpleEClass() {
        return simpleEClass;
    }

    @Override
    public List<Connection> getConnections() {
        List<Connection> ret = new ArrayList<>(super.getConnections());
        simpleEClass.getParents().forEach(parent -> {
            ret.addAll(parent.getSelfEntity().getConnections());
        });
        return ret;
    }

    public List<Entity> getAllFinalEntity() {
        List<Entity> ret = new ArrayList<>();
        if (simpleEClass.getChildren().isEmpty()) {
            ret.add(this);
        } else {
            simpleEClass.getFinalChildren().forEach(child -> {
                ret.add(child.getSelfEntity());
            });
        }
        return ret;
    }
}
