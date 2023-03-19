package transform.model;

import java.util.HashMap;
import java.util.List;

public class EModel extends HashMap<String, Object> {
    public static final String STARTER = "starter";
    public static final String MONO = "mono";
    public static final String COLOR = "color";
    public static final String ENTITY_LIST = "entities";

    public void setStarter(String starter) {
        this.put(STARTER, starter);
    }

    public void setMono(String mono) {
        this.put(MONO, mono);
    }

    public void setColor(String color) {
        this.put(COLOR, color);
    }

    public void setEntityList(List<ModelEntity> list) {
        this.put(ENTITY_LIST, list);
    }

}
