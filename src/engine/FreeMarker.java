package engine;

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
            this.cfg.setDirectoryForTemplateLoading(new File("resource/templates"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    public void generateEcl(Map<String, Object> root) throws Exception {
        Template template = this.cfg.getTemplate("test.ftl");
        Writer out = new FileWriter("src/test.ecl");
        template.process(root, out);
    }

    public static void main(String[] args) throws Exception {
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("resource/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

        /* Create a data-model */
        Map<String, Object> root = new HashMap<>();
        List<Entity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Entity(i+""));
        }
        root.put("entities", list);

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("test.ftl");

        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }
}
