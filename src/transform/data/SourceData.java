package transform.data;

import transform.model.ModelAttribute;
import transform.target.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class SourceData {
    private final String name;
    private final boolean isInterface;
    private Entity selfEntity;
    private List<ModelAttribute> attributes;

    private final List<SourceData> parents;
    private final List<SourceData> children;

    protected SourceData(String name, boolean isInterface, List<SourceData> parents) {
        this.name = name;
        this.isInterface = isInterface;
        this.parents = parents;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setSelfEntity(Entity selfEntity) {
        this.selfEntity = selfEntity;
    }

    public Entity getSelfEntity() {
        return selfEntity;
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
