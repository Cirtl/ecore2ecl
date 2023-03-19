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
        this.attributes = new ArrayList<>();
    }

    public String getName() {
        return name;
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

    public void setAttributes(List<ModelAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<ModelAttribute> getAttributes() {
        return attributes;
    }

    /**
     * @return 该类的全部属性
     */
    public List<ModelAttribute> getAllAttributes() {
        List<ModelAttribute> ret = new ArrayList<>(this.attributes);
        if(!this.parents.isEmpty()) {
            for (ModelAttribute attr: this.parents.get(0).getAllAttributes()) {
                boolean insert = true;
                for (ModelAttribute comp: this.attributes) {
                    if (attr.getName().equals(comp.getName())) {
                        insert = false;
                        break;
                    }
                }
                if (insert) ret.add(attr);
            }
        }
        return ret;
    }
}
