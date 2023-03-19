import generator.FreeMarker;
import process.wrapper.EEWrapper;
import process.wrapper.ESWrapper;
import process.wrapper.SEWrapper;
import process.wrapper.WrapperFactory;
import transform.model.Model;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // 0 for e2s; 1 for s2e; 2 for e2e
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();

        switch (type) {
            case 0:
                ESWrapper wrapper0 = null;
                try {
                    wrapper0 = WrapperFactory.SINGLETON.createESWrapper("D:/cirtl/user/project/ecore2ecl/resource/ecores/basicfamily.ecore");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper0 != null;

                Model model0 = wrapper0.buildModelData();
                try {
                    FreeMarker.INSTANCE.generateSSEcl(model0, new File("src/test.ssecl"));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.exit(0);
                }
                break;
            case 1:
                SEWrapper wrapper1 = null;
                try {
                    wrapper1 = WrapperFactory.SINGLETON.createSEWrapper("C:\\Users\\86186\\Desktop\\test\\test.ssecl");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper1 != null;

                Model model1 = wrapper1.buildModelData();
                try {
                    FreeMarker.INSTANCE.generateSSEcl(model1, new File("src/test1.ecl"));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.exit(0);
                }
                break;
            case 2:
                EEWrapper wrapper2 = null;
                try {
                    wrapper2 = WrapperFactory.SINGLETON.createEEWrapper("D:/cirtl/user/project/ecore2ecl/resource/ecores/basicfamily.ecore");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper2 != null;

                Map<String, Object> model3 = wrapper2.buildModelData();
                try {
                    FreeMarker.INSTANCE.generateSSEcl(model3, new File("src/test2.ecl"));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.exit(0);
                }
                break;
        }

    }
}
