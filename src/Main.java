import engine.FreeMarker;
import extract.tool.PackageWrapper;
import file.RecourceControl;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        PackageWrapper wrapper = null;
        try {
            wrapper = RecourceControl.SINGLETON.getEcore("resource/ecores/basicfamily.ecore");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }

        assert wrapper != null;

        Map<String, Object> map = wrapper.filterAllData();

        try {
            FreeMarker.INSTANCE.generateEcl(map);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }
}
