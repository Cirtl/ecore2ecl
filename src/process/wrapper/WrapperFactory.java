package process.wrapper;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * Wrapper生成工厂
 */
public class WrapperFactory {
    public static final WrapperFactory SINGLETON = new WrapperFactory();

    // 管理 Ecore 文件资源
    private final ResourceSet set;

    // 负责 SS-ECL 的解析


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

    public EEWrapper createEEWrapper(String path) throws Exception {
        return new EEWrapper(getEcore(path));
    }
}
