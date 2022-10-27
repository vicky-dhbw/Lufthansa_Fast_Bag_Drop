package livingComponents;

import automatComponents.Database;
import automatComponents.Display;
import automatComponents.FastBagDrop;
import flightRelevants.Flight;
import identityRelevants.IDCard;
import services.DataAnalytics;
import services.Export;
import services.Import;

public class ServiceAgent extends Employee{

    public ServiceAgent() throws Exception {
    }

    public void executeImport(Import importer, Flight flight, FastBagDrop fastBagDrop){
        importer.executeImport(flight,fastBagDrop);
    }

    public void executeDataAnalytics(DataAnalytics dataAnalytics, Display display,Database database){
        dataAnalytics.executeDataAnalysis(database,display);
    }
    public void executeExport(Export export){
        export.write();
    }

    public void startUpMachine(FastBagDrop fastBagDrop, IDCard idCard){
        fastBagDrop.getServices().getStartUp().executeStartUp(fastBagDrop,idCard);
    }

    public void shutDownMachine(FastBagDrop fastBagDrop, IDCard idCard){
        fastBagDrop.getServices().getShutdown().executeShutDown(fastBagDrop,idCard);
    }
}
