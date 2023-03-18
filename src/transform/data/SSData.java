package transform.data;

import java.util.List;

public class SSData extends SourceData{

    private String functionsBlock;
    private String visualizationBlock;

    public SSData(String name, boolean isInterface, List<SourceData> parents) {
        super(name, isInterface, parents);
    }
}
