import automatComponents.FastBagDrop;
import automatComponents.Position;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import livingComponents.FederalPolice;
import livingComponents.ServiceAgent;

public class Application {
    public static void main(String[] args) throws Exception {

        Flight flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG,Gate.A05);

        FastBagDrop fastBagDrop=new FastBagDrop();
        ServiceAgent serviceAgent=new ServiceAgent();
        FederalPolice federalPolice=new FederalPolice();

        // for ease, employees can start / shutdown / lock and unlock only through leftSection
        // service agent and federal police are issued valid id card that contains encrypted pin
        // the encrypted pins are store as decrypted in log in database in encryption manager in ID card scanner

        fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().setUpCard(serviceAgent.getIdCard());
        fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().setUpCard(federalPolice.getIdCard());


        //fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().

        fastBagDrop.setServiceAgent(serviceAgent);   // service agent and federal police is a part fast bag drop simulation processes
        fastBagDrop.setFederalPolice(federalPolice);

        serviceAgent.startUpMachine(fastBagDrop,serviceAgent.getIdCard());

        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);
        fastBagDrop.getServices().getCheckIn().executeCheckIn(fastBagDrop,flight);
        serviceAgent.executeExport(fastBagDrop.getServices().getExport());
        serviceAgent.executeDataAnalytics(fastBagDrop.getServices().getDataAnalytics(),fastBagDrop.getFastBagDropSection(Position.LEFT).getDisplay(), fastBagDrop.getDatabase());

        serviceAgent.shutDownMachine(fastBagDrop,serviceAgent.getIdCard());
    }

}
