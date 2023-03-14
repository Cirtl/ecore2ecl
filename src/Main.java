import generator.FreeMarker;
import process.wrapper.EEWrapper;
import process.wrapper.WrapperFactory;

import java.io.File;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        EEWrapper wrapper = null;
        try {
            wrapper = WrapperFactory.SINGLETON.createEEWrapper("D:/cirtl/user/project/ecore2ecl/resource/ecores/basicfamily.ecore");
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
