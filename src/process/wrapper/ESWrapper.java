package process.wrapper;

import org.eclipse.emf.ecore.EPackage;
import transform.ClassesTree;

/**
 * Ecore file change to SS-ECL file
 */
public class ESWrapper {
    private ClassesTree tree;

    public ESWrapper(EPackage ePackage) {
        this.tree = new ClassesTree(ePackage);
    }
}
