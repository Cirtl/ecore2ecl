package transform.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SGenealogy {
    private Map<String, SSData> entityMap;
    private Map<String, List<SSData>> inherits;

    public SGenealogy() {
        this.entityMap = new HashMap<>();
        this.inherits = new HashMap<>();
    }

    public Map<String, SSData> getEntityMap() {
        return entityMap;
    }

    public Map<String, List<SSData>> getInherits() {
        return inherits;
    }
}
