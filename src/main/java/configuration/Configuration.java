package configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum Configuration {
    INSTANCE;

    public final String fileSeparator = System.getProperty("file.separator");
    public final String lineSeparator = System.getProperty("line.separator");

    public final String baggageContents="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"Data"+fileSeparator+"baggage_content.txt";
    public final String baggageWeights="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"Data"+fileSeparator+"baggage_weight.txt";
    public final String assignmentFile="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"Data"+fileSeparator+"assignment.csv";
    //public final String qrCodeFile="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"baggageTags"+fileSeparator+"baggageTag.jpg";

    //public final String getQrCodeFile="C:"+fileSeparator+fileSeparator+"TAG"+fileSeparator+"baggageTag.jpg";
    final Path path= Paths.get("baggageTag.jpg");
    public final String qrCodeFile= String.valueOf(path.toAbsolutePath());


    public final String qrCodeFile1="./baggageTags/baggageTag.jpg";
    public final String qrCodeFile2="";
    public final String dataPath = "data" + fileSeparator;

}
