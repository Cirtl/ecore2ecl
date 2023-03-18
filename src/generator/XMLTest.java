package generator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;

public class XMLTest {
    public static void main(String[] args) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element booksElement = document.addElement("books");
        booksElement.addComment("This is a test for dom4j, cirtl, 2023.3.14");
        Element bookElement = booksElement.addElement("book");
        bookElement.addAttribute("show", "yes");
        Element titleElement = bookElement.addElement("title");
        titleElement.setText("Dom4j Tutorials");
        XMLWriter writer = new XMLWriter(new FileWriter("src/test.xml"));
        writer.write(document);
        writer.close();
    }
}
