package process.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import process.parser.SSECL.SSECLLexer;
import process.parser.SSECL.SSECLParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class TestParser {

    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("C:\\Users\\86186\\Desktop\\test\\test.ssecl");
        ANTLRInputStream input = new ANTLRInputStream(is);
        SSECLLexer lexer = new SSECLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SSECLParser parser = new SSECLParser(tokens);

        ParseTree tree = parser.description();
        ParseTreeWalker walker = new ParseTreeWalker();
        SSDataExtractor extractor = new SSDataExtractor();
        walker.walk(extractor, tree);

        System.out.println(tree.toStringTree(parser));
    }
}
