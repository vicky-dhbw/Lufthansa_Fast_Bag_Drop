package livingComponents;

import automatComponents.Database;
import automatComponents.FastBagDrop;
import flightRelevants.Flight;
import services.Import;

public class ServiceAgent extends Employee{

    public void executeImport(Import importer, Flight flight, FastBagDrop fastBagDrop){
        importer.executeImport(flight,fastBagDrop);
    }
}
