package transform.data;

import java.util.*;

public class SGenealogy {
    public Config config;

    private final Set<String> entitySet;   // 持有所有实体声明以及是否作为interface
    private final Map<String, SSData> entityMap;

    public SGenealogy() {
        this.entitySet = new HashSet<>();
        this.entityMap = new HashMap<>();
    }

    /**
     * 根据 tmp 属性中存储的 String 字符串生成对应的实体
     * 同时建立继承树
     */
    public void rebuild() {
        // 初始化 parent、child 关系
        for (String name: this.entitySet) {
            SSData entity = this.entityMap.get(name);
            entity.rebuild(this.entityMap);
        }
    }

    public Set<String> getValidEntitySet() {
        Set<String> ret = new HashSet<>();
        for (String name: this.entitySet) {
            if (this.getEntityMap().get(name).getChildren().isEmpty()) ret.add(name);
        }
        return ret;
    }

    public Map<String, SSData> getEntityMap() {
        return entityMap;
    }

    public void initConfig(String starter, String mono, String color) {
        this.config = new Config(starter, mono, color);
    }

    public void initSet(List<String> list) {
        this.entitySet.addAll(list);
    }

    private boolean isStated(String name) {
        return this.entitySet.contains(name);
    }

    public void addEntity(SSData entity) {
        if (!isStated(entity.getName())) return;
        this.entityMap.put(entity.getName(), entity);
    }

    public static class Config {
        private final String starter;
        private final String mono;
        private final String color;

        Config(String starter, String mono, String color) {
            this.starter = starter;
            this.mono = mono;
            this.color = color;
        }

        public String getStarter() {
            return starter;
        }

        public String getMono() {
            return mono;
        }

        public String getColor() {
            return color;
        }
    }
}
