import generator.AttributeXMLGenerator;
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

    private static final int E2S = 0;
    private static final int S2E = 1;
    private static final int E2E = 2;

    public static void main(String[] args) throws Exception {
        // 0 for e2s; 1 for s2e; 2 for e2e
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();

        switch (type) {
            case E2S:
                ESWrapper wrapper0 = null;
                try {
                    wrapper0 = WrapperFactory.SINGLETON.createESWrapper("C:\\Users\\86186\\Desktop\\test\\basicfamily.ecore");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper0 != null;

                Model model0 = wrapper0.buildModelData();
                try {
                    FreeMarker.INSTANCE.generateSSEcl(model0, new File("C:\\Users\\86186\\Desktop\\test\\basicfamily.ssecl"));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.exit(0);
                }
                break;
            case S2E:
                SEWrapper wrapper1 = null;
                try {
                    wrapper1 = WrapperFactory.SINGLETON.createSEWrapper("C:\\Users\\86186\\Desktop\\test\\full-basicfamily.ssecl");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper1 != null;

                Model model1 = wrapper1.buildModelData();
                try {
                    FreeMarker.INSTANCE.generateEcl(model1, new File("C:\\Users\\86186\\Desktop\\test\\full-basicfamily.ecl"));
                    AttributeXMLGenerator.INSTANCE.generateXML(wrapper1.entitySet(), "C:\\Users\\86186\\Desktop\\test\\full-basicfamily.xml");
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.exit(0);
                }
                break;
            case E2E:
                EEWrapper wrapper2 = null;
                try {
                    wrapper2 = WrapperFactory.SINGLETON.createEEWrapper("C:\\Users\\86186\\Desktop\\test\\basicfamily.ecore");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper2 != null;

                Map<String, Object> model3 = wrapper2.buildModelData();
                try {
                    FreeMarker.INSTANCE.generateEcl(model3, new File("C:\\Users\\86186\\Desktop\\test\\basicfamily.ecl"));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    System.exit(0);
                }
                break;
        }

    }
}
