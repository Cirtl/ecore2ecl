package transform.model;

import java.util.HashMap;

public class ModelAttribute extends HashMap<String, String> {

    public ModelAttribute(String name, String type) {
        this.put("name", name);
        this.put("type", type);
    }

    public String getName() {
        return this.get("name");
    }

    public String getType() {
        return this.get("type");
    }
}
