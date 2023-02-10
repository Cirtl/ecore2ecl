package extract.einfo;

import org.eclipse.emf.ecore.EClass;

public class BlockEntity extends Entity {
    private final EClass eClass;

    public BlockEntity(EClass eClass) {
        super(eClass.getName());
        this.eClass = eClass;
    }

    public EClass getEClass() {
        return eClass;
    }
}
