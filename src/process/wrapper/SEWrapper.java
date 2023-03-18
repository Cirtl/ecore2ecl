package process.wrapper;

import transform.data.SGenealogy;

/**
 * SS-ECL file change to ECL file
 */
public class SEWrapper {
    private SGenealogy genealogy;
    private SEModeler eclModeler;

    public SEWrapper(SGenealogy genealogy) {
        this.genealogy = genealogy;
        this.eclModeler = new SEModeler();
    }
}
