package process.wrapper;

import org.eclipse.emf.ecore.EPackage;
import transform.data.EGenealogy;

/**
 * Ecore file change to SS-ECL file
 */
public class ESWrapper {
    private EGenealogy tree;

    public ESWrapper(EPackage ePackage) {
        this.tree = new EGenealogy(ePackage);
    }
}
