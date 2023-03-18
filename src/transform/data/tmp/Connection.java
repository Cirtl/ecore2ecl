package transform.data.tmp;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    private final int lowerBound;
    private final int upperBound;
    private final Entity target;
    private final boolean isTo;

    public Connection(int lowerBound, int upperBound, Entity target, boolean isTo) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.target = target;
        this.isTo = isTo;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public List<Entity> getTarget() {
        if (target instanceof BlockEntity) {
            return ((BlockEntity)target).getAllFinalEntity();
        } else {
            List<Entity> ret = new ArrayList<>();
            ret.add(target);
            return ret;
        }
    }

    public boolean isTo() {
        return isTo;
    }
}
