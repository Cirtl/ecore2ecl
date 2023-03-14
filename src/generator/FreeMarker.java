package generator;

import freemarker.template.*;
import java.util.*;
import java.io.*;

public class FreeMarker {

    public static final FreeMarker INSTANCE = new FreeMarker();

    private final Configuration cfg;

    private FreeMarker() {
        this.cfg = new Configuration(Configuration.VERSION_2_3_22);
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            this.cfg.setDirectoryForTemplateLoading(new File("process/templates"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    public void generateEcl(Map<String, Object> root, File outputFile) throws Exception {
        Template template = this.cfg.getTemplate("test.ftl");
        Writer out = new FileWriter(outputFile);
        template.process(root, out);
    }

}
