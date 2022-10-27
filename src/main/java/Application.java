import automatComponents.FastBagDrop;
import automatComponents.Manufacturer;
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

        FastBagDrop fastBagDrop=new FastBagDrop(Manufacturer.SMITH);
        ServiceAgent serviceAgent=new ServiceAgent();
        FederalPolice federalPolice=new FederalPolice();

        // for ease, employees can start / shutdown / lock and unlock only through leftSection <---- could also log in from right section
        // service agent and federal police are issued valid id card that contains encrypted pin
        // the encrypted pins are store as decrypted in log in database in encryption manager in ID card scanner

        fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().setUpCard(serviceAgent.getIdCard());    // card with rfid chip will be created with encrypted pin by the encryption manager in id scard scanner
        fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().setUpCard(federalPolice.getIdCard());

        // credentials are copied to the log in database of the id card reader
        // both sections must have the same encryption decryption algorithm
        // overriding des and log in database of id card scanner in right fast bag drop section
        fastBagDrop.getRightSection().getIdCardScanner().getEncryptionManager().setDes(fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().getMachineDES());
        fastBagDrop.getRightSection().getIdCardScanner().getEncryptionManager().setLogInDatabase(fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().getLogInDatabase());

        fastBagDrop.setServiceAgent(serviceAgent);   // service agent and federal police is a part fast bag drop simulation processes
        fastBagDrop.setFederalPolice(federalPolice);

        // note rfid chip pin is saved to the left section of the fast bag drop
        // the service agent and the federal police start, unlock shutdown the fast bag drop through the left section of the fast bag drop

        // federal officer tries to unlock the machine when the machine state is LOCKED,   <--- change of state of machine from LOCKED to UNLOCKED

        serviceAgent.startUpMachine(fastBagDrop,serviceAgent.getIdCard());
        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);  //prepare data for fast bag drop

        //somebody has to start the machine and set machine state to ON <---- machine can have only one state
        //fast bag drop state may also be LOCKED if authentication fails <--authenticates using id scanner
        fastBagDrop.getServices().getCheckIn().executeCheckIn(fastBagDrop,flight);
        serviceAgent.executeExport(fastBagDrop.getServices().getExport());
        serviceAgent.shutDownMachine(fastBagDrop,serviceAgent.getIdCard());

        serviceAgent.executeDataAnalytics(fastBagDrop.getServices().getDataAnalytics(),fastBagDrop.getFastBagDropSection(Position.LEFT).getDisplay(), fastBagDrop.getDatabase());



    }

}
