package generator;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import transform.model.Model;

import java.io.*;

public class FreeMarker {

    public static FreeMarker INSTANCE;

    private final Configuration cfg;

    public static void init() {
        INSTANCE = new FreeMarker();
    }

    private FreeMarker() {
        this.cfg = new Configuration(Configuration.VERSION_2_3_22);
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            StringTemplateLoader loader = new StringTemplateLoader();
            loader.putTemplate("ecl.ftl", loadTemplate("/templates/ecl.ftl"));
            loader.putTemplate("ssecl.ftl", loadTemplate("/templates/ssecl.ftl"));
            this.cfg.setTemplateLoader(loader);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("FreeMarker Instance Initializing Failed.");
            System.exit(1);
        }
    }

    /**
     * 因为 Jar 包环境不允许目录的方式加载模板
     * 所以通过通过字符串的方式加载模板
     * @return Template String
     */
    private String loadTemplate(String path) throws Exception {
        InputStream in = this.getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        in.close();
        reader.close();

        return builder.toString();
    }

    public void generateEcl(Model model, File outputFile) throws Exception {
        if (INSTANCE == null) throw new Exception("Instance has not been initialized.");

        Template template = this.cfg.getTemplate("ecl.ftl");
        Writer out = new FileWriter(outputFile);
        template.process(model, out);
    }

    public void generateSSEcl(Model model, File outputFile) throws Exception {
        if (INSTANCE == null) throw new Exception("Instance has not been initialized.");

        Template template = this.cfg.getTemplate("ssecl.ftl");
        Writer out = new FileWriter(outputFile);
        template.process(model, out);
    }

}
