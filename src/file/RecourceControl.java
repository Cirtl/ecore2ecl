package file;

import extract.tool.PackageWrapper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * 负责管理ecore资源
 * 单实例
 */
public class RecourceControl {
    public static final RecourceControl SINGLETON = new RecourceControl();

    private final ResourceSet set;

    private RecourceControl() {
        this.set = new ResourceSetImpl();
        this.set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    }

    public PackageWrapper getEcore(String path) throws Exception {
        String sub = path.substring(path.lastIndexOf("."));
        if (!sub.equals(".ecore"))
            throw new Exception("not ecore file!");

        return new PackageWrapper(
                (EPackage) this.set
                    .getResource(URI.createURI(path), true)
                    .getContents().get(0)
        );
    }
}
