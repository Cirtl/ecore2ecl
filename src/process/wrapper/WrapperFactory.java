package process.wrapper;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import process.parser.SSDataExtractor;
import process.parser.SSECL.SSECLLexer;
import process.parser.SSECL.SSECLParser;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Wrapper生成工厂
 */
public class WrapperFactory {
    public static final WrapperFactory SINGLETON = new WrapperFactory();

    // 管理 Ecore 文件资源
    private final ResourceSet set;

    private WrapperFactory() {
        this.set = new ResourceSetImpl();
        this.set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    }

    private EPackage getEcore(String path) throws Exception {
        String sub = path.substring(path.lastIndexOf("."));
        if (!sub.equals(".ecore"))
            throw new Exception("not ecore file!");

        return (EPackage) this.set.getResource(URI.createFileURI(path), true).getContents().get(0);
    }

    public ESWrapper createESWrapper(String path) throws Exception {
        return new ESWrapper(getEcore(path));
    }

    public SEWrapper createSEWrapper(String path) throws Exception {
        InputStream stream = new FileInputStream(path);
        ANTLRInputStream input = new ANTLRInputStream(stream);
        SSECLLexer lexer = new SSECLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SSECLParser parser = new SSECLParser(tokens);
        ParseTree tree = parser.description();
        ParseTreeWalker walker = new ParseTreeWalker();
        SSDataExtractor extractor = new SSDataExtractor();
        walker.walk(extractor, tree);
        return new SEWrapper(extractor.getGenealogy());
    }

}
