package automatComponents;

import automatComponents.*;
import livingComponents.Passenger;
import searchAlgorithms.StringMatchingAlgorithm;

import java.util.Queue;

public class FastBagDropSection {

    private PassportScanner passportScanner=new PassportScanner();
    private DocumentPrinter documentPrinter =new DocumentPrinter();
    private ConveyorBelt conveyorBelt=new ConveyorBelt();
    private Sensor sensor=new Sensor();
    private BaggageScanner baggageScanner;
    private IDCardScanner idCardScanner =new IDCardScanner();
    private Display display=new Display();

    private EconomyQueue economyQueue;

    private BusinessQueue businessQueue;

    private final Position position;

    public FastBagDropSection(Position position){
        passportScanner=new PassportScanner();
        documentPrinter =new DocumentPrinter();
        conveyorBelt=new ConveyorBelt();
        sensor=new Sensor();
        idCardScanner =new IDCardScanner();
        display=new Display();
        this.position=position;

        if(position==Position.LEFT){
            businessQueue=new BusinessQueue(); //new creation of left section creates only a new business Queue, --> note economyQueue is null for leftSection
        }
        if(position==Position.RIGHT){
            economyQueue=new EconomyQueue();  //new creation of right section creates only a new economy Queue, --> note businessQueue is null for rightSection
        }
    }

    public PassportScanner getPassportScanner() {
        return passportScanner;
    }

    public void setPassportScanner(PassportScanner passportScanner) {
        this.passportScanner = passportScanner;
    }

    public DocumentPrinter getDocumentPrinter() {
        return documentPrinter;
    }

    public void setDocumentPrinter(DocumentPrinter documentPrinter) {
        this.documentPrinter = documentPrinter;
    }

    public ConveyorBelt getConveyorBelt() {
        return conveyorBelt;
    }

    public void setConveyorBelt(ConveyorBelt conveyorBelt) {
        this.conveyorBelt = conveyorBelt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }

    public void setBaggageScanner(BaggageScanner baggageScanner) {
        this.baggageScanner = baggageScanner;
    }

    public IDCardScanner getIdCardScanner() {
        return idCardScanner;
    }

    public void setIdCardScanner(IDCardScanner idCardScanner) {
        this.idCardScanner = idCardScanner;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public EconomyQueue getEconomyQueue() {
        return economyQueue;
    }

    public void setEconomyQueue(EconomyQueue economyQueue) {
        this.economyQueue = economyQueue;
    }

    public BusinessQueue getBusinessQueue() {
        return businessQueue;
    }

    public void setBusinessQueue(BusinessQueue businessQueue) {
        this.businessQueue = businessQueue;
    }

    public Position getPosition() {
        return position;
    }
}
