package configuration;

public enum Configuration {
    INSTANCE;

    public final String fileSeparator = System.getProperty("file.separator");
    public final String lineSeparator = System.getProperty("line.separator");

    public final String baggageContents="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"Data"+fileSeparator+"baggage_content.txt";
    public final String baggageWeights="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"Data"+fileSeparator+"baggage_weight.txt";
    public final String assignmentFile="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"Data"+fileSeparator+"assignment.csv";
    public final String qrCodeFile="src"+fileSeparator+"main"+fileSeparator+"java"+fileSeparator+"baggageTags"+fileSeparator+"baggageTag.jpg";


    public final String dataPath = "data" + fileSeparator;

}
