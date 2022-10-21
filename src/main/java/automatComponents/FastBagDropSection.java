package automatComponents;

import automatComponents.*;
import livingComponents.Passenger;

import java.util.Queue;

public class FastBagDropSection {

    private PassportScanner passportScanner=new PassportScanner();
    private DocumentPrinter documentPrinter =new DocumentPrinter();
    private ConveyorBelt conveyorBelt=new ConveyorBelt();
    private Sensor sensor=new Sensor();
    private BaggageScanner baggageScanner=new BaggageScanner();
    private IDCardScanner idCardScanner =new IDCardScanner();
    private Display display=new Display();

    private EconomyQueue economyQueue;

    private BusinessQueue businessQueue;

    public FastBagDropSection(Position position){
        passportScanner=new PassportScanner();
        documentPrinter =new DocumentPrinter();
        conveyorBelt=new ConveyorBelt();
        sensor=new Sensor();
        baggageScanner=new BaggageScanner();
        idCardScanner =new IDCardScanner();
        display=new Display();

        if(position==Position.LEFT){
            businessQueue=new BusinessQueue();
        }
        if(position==Position.RIGHT){
            economyQueue=new EconomyQueue();
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
}
