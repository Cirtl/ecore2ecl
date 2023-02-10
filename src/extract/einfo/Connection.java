package extract.einfo;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    private final int lowerBound;
    private final int upperBound;
    private final List<Entity> target;
    private final boolean isTo;
    private final boolean isContainment;

    public Connection(int lowerBound, int upperBound, List<Entity> target, boolean isTo) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.target = target;
        this.isTo = isTo;
        this.isContainment = false;
    }

    public Connection(int lowerBound, int upperBound, List<Entity> target, boolean isTo, boolean isContainment) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.target = target;
        this.isTo = isTo;
        this.isContainment = isContainment;
    }


    public Connection(int lowerBound, int upperBound, Entity target, boolean isTo) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.target = new ArrayList<>();
        this.target.add(target);
        this.isTo = isTo;
        this.isContainment = false;
    }

    public Connection(int lowerBound, int upperBound, Entity target, boolean isTo, boolean isContainment) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.target = new ArrayList<>();
        this.target.add(target);
        this.isTo = isTo;
        this.isContainment = isContainment;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public List<Entity> getTarget() {
        return target;
    }

    public boolean isTo() {
        return isTo;
    }

    public boolean isContainment() {
        return isContainment;
    }
}
