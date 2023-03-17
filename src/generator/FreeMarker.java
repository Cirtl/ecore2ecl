package generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

public class FreeMarker {

    public static final FreeMarker INSTANCE = new FreeMarker();

    private final Configuration cfg;

    private FreeMarker() {
        this.cfg = new Configuration(Configuration.VERSION_2_3_22);
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            this.cfg.setDirectoryForTemplateLoading(new File(FreeMarker.class.getResource("../templates").getPath()));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    public void generateEcl(Map<String, Object> root, File outputFile) throws Exception {
        Template template = this.cfg.getTemplate("ecl.ftl");
        Writer out = new FileWriter(outputFile);
        template.process(root, out);
    }

    public void generateSSEcl(Map<String, Object> root, File outputFile) throws Exception {
        Template template = this.cfg.getTemplate("ssecl.ftl");
        Writer out = new FileWriter(outputFile);
        template.process(root, out);
    }

}
