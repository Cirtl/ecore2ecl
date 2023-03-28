import generator.AttributeXMLGenerator;
import generator.FreeMarker;
import process.wrapper.ESWrapper;
import process.wrapper.SEWrapper;
import process.wrapper.WrapperFactory;
import transform.model.Model;

import java.io.File;

public class Main {
    // 0 for e2s; 1 for s2e; no e2e
    private static final int E2S = 0;
    private static final int S2E = 1;

    /**
     * @param args 0 locate transformation type; 1 locate the input file path; the output files will generated in the same folder of the input file
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("参数数量错误!");
            System.exit(0);
        }

        String arg1 = args[0], inputPath = args[1];

        FreeMarker.init();

        int type = Integer.parseInt(arg1);

        switch (type) {
            case E2S: {
                ESWrapper wrapper0 = null;
                try {
                    wrapper0 = WrapperFactory.SINGLETON.createESWrapper(inputPath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper0 != null;

                Model model0 = wrapper0.buildModelData();
                try {
                    String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + ".ssecl";
                    FreeMarker.INSTANCE.generateSSEcl(model0, new File(outputPath));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            }
            case S2E: {
                SEWrapper wrapper1 = null;
                try {
                    wrapper1 = WrapperFactory.SINGLETON.createSEWrapper(inputPath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                assert wrapper1 != null;

                Model model1 = wrapper1.buildModelData();
                try {
                    String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + ".ecl";
                    String attrPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + ".xml";
                    FreeMarker.INSTANCE.generateEcl(model1, new File(outputPath));
                    AttributeXMLGenerator.INSTANCE.generateXML(wrapper1.entitySet(), attrPath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            }
            default: {
                System.out.println("The input type function not exist.");
            }
        }

    }
}
