package engine;

import java.util.HashMap;

public class Entity extends HashMap<String, String> {
    String name;
    Entity(String name) {
        super();
        this.name = name;
        this.put("name", this.name);
    }
}
