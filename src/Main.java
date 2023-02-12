import engine.FreeMarker;
import extract.tool.Wrapper;
import file.RecourceControl;

import java.io.File;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Wrapper wrapper = null;
        try {
            wrapper = RecourceControl.SINGLETON.getEcore("D:/cirtl/user/project/ecore2ecl/resource/ecores/basicfamily.ecore");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assert wrapper != null;

        Map<String, Object> map = wrapper.filterAllData();

        try {
            FreeMarker.INSTANCE.generateEcl(map, new File("src/test.ecl"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }
}
