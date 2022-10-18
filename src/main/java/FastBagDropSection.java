import automatComponents.Display;
import automatComponents.PassportScanner;

public class FastBagDropSection {

    PassportScanner passportScanner=new PassportScanner();
    DocumentPrinter documentPrinter =new DocumentPrinter();
    ConveyorBelt conveyorBelt=new ConveyorBelt();
    Sensor sensor=new Sensor();
    BaggageScanner baggageScanner=new BaggageScanner();
    IDCardScanner idCardScanner =new IDCardScanner();
    Display display=new Display();

    public FastBagDropSection(){
        passportScanner=new PassportScanner();
        documentPrinter =new DocumentPrinter();
        conveyorBelt=new ConveyorBelt();
        sensor=new Sensor();
        baggageScanner=new BaggageScanner();
        idCardScanner =new IDCardScanner();
        display=new Display();
    }

}
