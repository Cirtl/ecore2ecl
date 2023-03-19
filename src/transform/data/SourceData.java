package transform.data;

import transform.model.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public abstract class SourceData {
    private final String name;
    private List<ModelAttribute> attributes;

    private final List<SourceData> parents;
    private final List<SourceData> children;

    protected SourceData(String name, List<SourceData> parents) {
        this.name = name;
        this.parents = parents;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setAttributes(List<ModelAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<SourceData> getParents() {
        return parents;
    }

    public List<SourceData> getChildren() {
        return children;
    }

    /**
     * 返回该对象的所有最终子对象, 不包括自己
     * @return
     */
    public List<SourceData> getFinalChildren() {
        List<SourceData> ret = new ArrayList<>();
        for (SourceData child : children) {
            if (child.getChildren().isEmpty()) {
                ret.add(child);
            } else {
                ret.addAll(child.getFinalChildren());
            }
        }
        return ret;
    }

    public List<ModelAttribute> getAttributes() {
        return attributes;
    }
}
