package transform.model;

import java.util.HashMap;

public class ModelAttribute extends HashMap<String, String> {
    public static final String NAME = "name";
    public static final String TYPE = "type";

    public ModelAttribute(String name, String type) {
        this.put(NAME, name);
        this.put(TYPE, type);
    }

    public String getName() {
        return this.get("name");
    }

    public String getType() {
        return this.get("type");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelAttribute that = (ModelAttribute) o;
        return this.getName().equals(that.getName()) && this.getName().equals(that.getName());
    }
}
