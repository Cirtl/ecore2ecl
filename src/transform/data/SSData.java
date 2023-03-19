package transform.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSData extends SourceData{
    private final List<String> tmpParents;
    private final List<String> tmpElders;

    // optional
    private List<String> functionsBlock = null;
    private List<String> visualizationBlock = null;

    private final List<SSData> elders;      //保存 acts 对象
    private final List<ConnectionGroup> connectionGroups;

    public SSData(String name, List<String> parents, List<String> elders, List<ConnectionGroup> connectionGroups) {
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

    /**
     * 将连接关系组合并, 同名的关系组将合并为一个Group
     * @param l1 连接关系组合1
     * @param l2 连接关系组合2
     * @return 合并后的连接关系组合
     */
    private List<ConnectionGroup> mergeGroups(List<ConnectionGroup> l1, List<ConnectionGroup> l2) {
        Map<String, ConnectionGroup> map = new HashMap<>();
        for (ConnectionGroup group: l1) {
            if (map.containsKey(group.getName())) map.get(group.getName()).addAll(group);
            else map.put(group.getName(), new ConnectionGroup(group));
        }
        for (ConnectionGroup group: l2) {
            if (map.containsKey(group.getName())) map.get(group.getName()).addAll(group);
            else map.put(group.getName(), new ConnectionGroup(group));
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 实现连接关系组和继承特性
     * @return 返回的是该实体经过extends后的全部连接关系组
     */
    public List<ConnectionGroup> getFinalConnectionGroups() {
        List<ConnectionGroup> ret;
        if (super.getParents().isEmpty())
            ret = new ArrayList<>(this.connectionGroups);
        else {
            SSData parent = (SSData) super.getParents().get(0);
            ret = mergeGroups(parent.getFinalConnectionGroups(), this.connectionGroups);
        }
        return ret;
    }

    public List<ConnectionGroup> refConnectionGroups() {
        return connectionGroups;
    }

    public void setFunctionsBlock(String functionsBlock) {
        this.functionsBlock = split(functionsBlock);
    }

    public void setVisualizationBlock(String visualizationBlock) {
        this.visualizationBlock = split(visualizationBlock);
    }

    public List<String> getFunctionsBlock() {
        if (this.functionsBlock == null) {
            if (super.getParents().isEmpty()) return null;
            else return ((SSData) (super.getParents().get(0))).getFunctionsBlock();
        }
        else return functionsBlock;
    }

    public List<String> getVisualizationBlock() {
        if (this.visualizationBlock == null) {
            if (super.getParents().isEmpty()) return null;
            else return ((SSData) (super.getParents().get(0))).getVisualizationBlock();
        }
        else return visualizationBlock;
    }

    public static class Connection {
        private final int lowerBound;
        private final int upperBound;
        private final boolean isTo;
        private final List<SSData> targets;

        private final List<String> tmpTargets;

        public Connection(int lowerBound, int upperBound, String target, boolean isTo) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.tmpTargets = new ArrayList<>();
            this.tmpTargets.add(target);
            this.isTo = isTo;
            this.targets = new ArrayList<>();
        }

        public Connection(int lowerBound, int upperBound, List<String> target, boolean isTo) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.tmpTargets = target;
            this.isTo = isTo;
            this.targets = new ArrayList<>();
        }

        public void rebuild(Map<String, SSData> map) {
            this.tmpTargets.forEach(name -> targets.add(map.get(name)));
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Connection that = (Connection) o;

            if (lowerBound != that.lowerBound) return false;
            if (upperBound != that.upperBound) return false;
            if (isTo != that.isTo) return false;
            return targets.equals(that.targets);
        }

        @Override
        public int hashCode() {
            int result = lowerBound;
            result = 31 * result + upperBound;
            result = 31 * result + (isTo ? 1 : 0);
            result = 31 * result + targets.hashCode();
            return result;
        }
    }

    public static class ConnectionGroup extends ArrayList<Connection> {
        private final String name;

        public ConnectionGroup(String name) {
            super();
            this.name = name;
        }

        public ConnectionGroup(ConnectionGroup src) {
            super();
            this.addAll(src);
            this.name = src.getName();
        }

        public String getName() {
            return name;
        }
    }
}
