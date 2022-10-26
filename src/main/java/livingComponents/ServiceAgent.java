package livingComponents;

import automatComponents.Database;
import automatComponents.Display;
import automatComponents.FastBagDrop;
import flightRelevants.Flight;
import services.DataAnalytics;
import services.Import;

public class ServiceAgent extends Employee{

    public void executeImport(Import importer, Flight flight, FastBagDrop fastBagDrop){
        importer.executeImport(flight,fastBagDrop);
    }

    public void executeDataAnalytics(DataAnalytics dataAnalytics, Display display,Database database){
        dataAnalytics.executeDataAnalysis(database,display);
    }
}
