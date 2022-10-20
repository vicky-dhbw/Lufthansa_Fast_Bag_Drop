package automatComponents;

import automatComponents.*;

public class FastBagDropSection {

    private PassportScanner passportScanner=new PassportScanner();
    private DocumentPrinter documentPrinter =new DocumentPrinter();
    private ConveyorBelt conveyorBelt=new ConveyorBelt();
    private Sensor sensor=new Sensor();
    private BaggageScanner baggageScanner=new BaggageScanner();
    private IDCardScanner idCardScanner =new IDCardScanner();
    private Display display=new Display();

    public FastBagDropSection(){
        passportScanner=new PassportScanner();
        documentPrinter =new DocumentPrinter();
        conveyorBelt=new ConveyorBelt();
        sensor=new Sensor();
        baggageScanner=new BaggageScanner();
        idCardScanner =new IDCardScanner();
        display=new Display();
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
}
