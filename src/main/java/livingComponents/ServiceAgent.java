package livingComponents;

import automatComponents.Database;
import automatComponents.Display;
import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import flightRelevants.Flight;
import identityRelevants.CardPurpose;
import identityRelevants.IDCard;
import services.DataAnalytics;
import services.Export;
import services.Import;

public class ServiceAgent extends Employee{

    public ServiceAgent() throws Exception {
        this.getIdCard().setCardPurpose(CardPurpose.ON_OFF);
    }

    public void executeImport(Import importer, Flight flight, FastBagDrop fastBagDrop){
        importer.executeImport(flight,fastBagDrop);
    }

    public boolean executeDataAnalytics(DataAnalytics dataAnalytics, Display display,Database database){
        dataAnalytics.executeDataAnalysis(database,display);
        return true;
    }
    public boolean executeExport(Export export){
        export.write();
        return true;
    }

    public void startUpMachine(FastBagDrop fastBagDrop, IDCard idCard){
        fastBagDrop.getServices().getStartUp().executeStartUp(fastBagDrop,idCard);
    }

    public void shutDownMachine(FastBagDrop fastBagDrop, IDCard idCard){
        fastBagDrop .getServices().getShutdown().executeShutDown(fastBagDrop,idCard); // the service agent starts the startUp over the display  ---> see display.serviceAgentStartsMachine
    }

}
