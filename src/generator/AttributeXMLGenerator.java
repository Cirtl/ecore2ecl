package generator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import transform.data.SSData;
import transform.model.ModelAttribute;

import java.io.FileWriter;
import java.util.Set;

public class AttributeXMLGenerator {
    private static final String ENTITY_LIST = "entities";
    private static final String ENTITY = "entity";
    private static final String ATTR = "attribute";
    private static final String NAME = "name";
    private static final String TYPE = "type";

    public static final AttributeXMLGenerator INSTANCE = new AttributeXMLGenerator();

    private AttributeXMLGenerator() {}

    public void generateXML(Set<SSData> entitySet, String path) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement(ENTITY_LIST);
        for (SSData entity: entitySet) {
            if (!entity.getAllAttributes().isEmpty()) {
                Element element = rootElement.addElement(ENTITY);
                element.addAttribute(NAME, entity.getName());
                for (ModelAttribute attribute: entity.getAllAttributes()) {
                    Element attrElement = element.addElement(ATTR);
                    attrElement.addAttribute(NAME, attribute.getName());
                    attrElement.addAttribute(TYPE, attribute.getType());
                }
            }
        }
        XMLWriter writer = new XMLWriter(new FileWriter(path));
        writer.write(document);
        writer.close();
    }
}
