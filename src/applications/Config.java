package applications;

import engine.FreeMarker;
import extract.tool.PackageWrapper;
import file.RecourceControl;

import java.io.File;

public class Config {
    private String inputPath;
    private File outputFile;

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(File file) {
        this.outputFile = file;
    }

    public boolean haveInputPath() {
        return inputPath != null;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void generateECLFile() throws Exception {
        PackageWrapper wrapper = RecourceControl.SINGLETON.getEcore(this.inputPath);
        FreeMarker.INSTANCE.generateEcl(wrapper.filterAllData(), outputFile);
    }
}
