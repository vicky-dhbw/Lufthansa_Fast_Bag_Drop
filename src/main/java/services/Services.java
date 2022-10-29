package services;

import com.google.zxing.WriterException;

import java.io.IOException;

public class Services {

    private final CheckIn checkIn;
    private final DataAnalytics dataAnalytics;
    private final DetermineWeight determineWeight;
    private final ExplosivesInvestigation explosivesInvestigation;
    private final Export export;
    private final Import importer;
    private final ScanBaggage scanBaggage;
    private final ScanBaggageTag scanBaggageTag;
    private final Shutdown shutdown;
    private final StartUp startUp;
    private final Unlock unlock;
    private final BaggageDrop baggageDrop;

    public Services() throws IOException, WriterException {
        checkIn=new CheckIn();
        dataAnalytics=new DataAnalytics();
        determineWeight=new DetermineWeight();
        explosivesInvestigation=new ExplosivesInvestigation();
        export=new Export();
        importer=new Import();
        scanBaggage=new ScanBaggage();
        scanBaggageTag=new ScanBaggageTag();
        shutdown=new Shutdown();
        startUp=new StartUp();
        unlock=new Unlock();
        baggageDrop=new BaggageDrop();

    }


    public CheckIn getCheckIn() {
        return checkIn;
    }

    public DataAnalytics getDataAnalytics() {
        return dataAnalytics;
    }

    public DetermineWeight getDetermineWeight() {
        return determineWeight;
    }

    public ExplosivesInvestigation getExplosivesInvestigation() {
        return explosivesInvestigation;
    }

    public Export getExport() {
        return export;
    }

    public Import getImporter() {
        return importer;
    }

    public ScanBaggage getScanBaggage() {
        return scanBaggage;
    }

    public ScanBaggageTag getScanBaggageTag() {
        return scanBaggageTag;
    }

    public Shutdown getShutdown() {
        return shutdown;
    }

    public StartUp getStartUp() {
        return startUp;
    }

    public Unlock getUnlock() {
        return unlock;
    }
}
