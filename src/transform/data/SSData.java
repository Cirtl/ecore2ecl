package transform.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SSData extends SourceData{
    private final List<String> tmpParents;
    private final List<String> tmpElders;

    // optional
    private List<String> functionsBlock = null;
    private List<String> visualizationBlock = null;

    private final List<SSData> elders;      //保存 acts 对象
    private final List<List<Connection>> connectionGroups;

    public SSData(String name, List<String> parents, List<String> elders, List<List<Connection>> connectionGroups) {
        super(name, new ArrayList<>());
        this.tmpParents = parents;
        this.tmpElders = elders;
        this.elders = new ArrayList<>();
        this.connectionGroups = connectionGroups;
    }

    private static List<String> split(String s) {
        List<String> list = new ArrayList<>();
        String[] arr = s.split("[\\t\\r\\n]+", 0);
        for (String tmp: arr) {
            if (!(tmp.contains("[") || tmp.contains("]"))) list.add(tmp.trim());
        }
        return list;
    }

    /**
     * change tmp domains to object domains
     * @param map key-value of string to SSData
     */
    public void rebuild(Map<String, SSData> map) {
        // rebuild tmpParents
        for (String parent: this.tmpParents) {
            super.getParents().add(map.get(parent));
            map.get(parent).getChildren().add(this);
        }

        // rebuild tmpElders
        for (String elder: this.tmpElders) {
            this.elders.add(map.get(elder));
        }

        // rebuild connectionGroups
        for (List<Connection> group: this.connectionGroups) {
            for (Connection connection: group) {
                connection.rebuild(map);
            }
        }
    }

    public List<SSData> getElders() {
        return elders;
    }

    public List<List<Connection>> getConnectionGroups() {
        return connectionGroups;
    }

    public void setFunctionsBlock(String functionsBlock) {
        this.functionsBlock = split(functionsBlock);
    }

    public void setVisualizationBlock(String visualizationBlock) {
        this.visualizationBlock = split(visualizationBlock);
    }

    public List<String> getFunctionsBlock() {
        return functionsBlock;
    }

    public List<String> getVisualizationBlock() {
        return visualizationBlock;
    }

    public static class Connection {
        private final int lowerBound;
        private final int upperBound;
        private final boolean isTo;
        private List<SSData> targets;

        private final List<String> tmpTargets;

        public Connection(int lowerBound, int upperBound, List<String> target, boolean isTo) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.tmpTargets = target;
            this.isTo = isTo;
            this.targets = new ArrayList<>();
        }

        public void rebuild(Map<String, SSData> map) {
            this.tmpTargets.forEach(
                    name ->
                            targets.add(
                                    map.get(name)));
        }

        public int getUpperBound() {
            return upperBound;
        }

        public int getLowerBound() {
            return lowerBound;
        }

        public boolean isTo() {
            return isTo;
        }

        public List<SourceData> getTargets() {
            List<SourceData> ret = new ArrayList<>();
            for (SSData entity: this.targets) {
                if (entity.getChildren().isEmpty()) ret.add(entity);
                else ret.addAll(entity.getFinalChildren());
            }
            return ret;
        }
    }
}
