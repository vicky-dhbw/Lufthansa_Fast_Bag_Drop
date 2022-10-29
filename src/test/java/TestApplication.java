
import automatComponents.*;
import com.google.zxing.WriterException;
import configuration.Configuration;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import identityRelevants.IDCard;
import livingComponents.FederalPolice;
import livingComponents.Passenger;
import livingComponents.ServiceAgent;
import org.junit.jupiter.api.*;
import passengerRelevants.Baggage;
import searchAlgorithms.StringMatchingAlgorithm;
import services.BaggageDrop;
import services.ScanBaggage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestApplication {

    FastBagDrop fastBagDrop;
    ServiceAgent serviceAgent;
    FederalPolice federalPolice;
    Flight flight;
    @BeforeEach
    public void setUp() throws Exception {
        flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG, Gate.A05);
        serviceAgent=new ServiceAgent();
        federalPolice= new FederalPolice();
        fastBagDrop=new FastBagDrop(Manufacturer.SMITH);
        fastBagDrop.setServiceAgent(serviceAgent);
        fastBagDrop.setFederalPolice(fastBagDrop.getFederalPolice());
        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);


        fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().setUpCard(serviceAgent.getIdCard());    // card with rfid chip will be created with encrypted pin by the encryption manager in id scard scanner
        fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().setUpCard(federalPolice.getIdCard());

        fastBagDrop.getRightSection().getIdCardScanner().getEncryptionManager().setDes(fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().getMachineDES());
        fastBagDrop.getRightSection().getIdCardScanner().getEncryptionManager().setLogInDatabase(fastBagDrop.getLeftSection().getIdCardScanner().getEncryptionManager().getLogInDatabase());

    }

    @Test
    @Order(1)
    public void TestQueueNotEmpty(){
        assertNotNull(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue());
        assertNotNull(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue());
    }
    @Test
    @Order(2)
    public void testBusinessQueue(){
        assertEquals(293,fastBagDrop.getDatabase().getPassengerDatabase().size());

    }
    @Test
    @Order(3)
    public void importSuccess(){
        int leftQueue=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().size();
        int rightQueue=fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().size();
        assertEquals(fastBagDrop.getDatabase().getPassengerDatabase().size(),leftQueue+rightQueue);
    }

    @Test
    @Order(4)
    public void assureBaggageListNotNull() throws IOException, WriterException {
        Queue<Passenger> testQueue=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();
        BaggageDrop baggageDrop =new BaggageDrop();
        for(Passenger passenger: testQueue){
            baggageDrop.assignBaggageToPassenger(passenger,passenger.getNumberOfBaggage());
        }
        for(Passenger passenger:testQueue){
            List<Baggage> testBList=passenger.getBaggageList();
            assertTrue(testBList.size()>=1);
        }
    }

    @Test
    @Order(5)
    public void assureBaggageContent() throws IOException, WriterException {
        Queue<Passenger> testQueue=fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue();
        BaggageDrop baggageDrop =new BaggageDrop();
        while(!testQueue.isEmpty()){
            Passenger passenger=testQueue.poll();
            baggageDrop.assignBaggageToPassenger(passenger,passenger.getNumberOfBaggage());
            for(Baggage baggage:passenger.getBaggageList()){
                assertTrue(baggage.getContent().length()>0);
            }
        }
    }

    @Test
    @Order(5)
    public void assureBaggageContent_() throws IOException, WriterException {
        Queue<Passenger> testQueue=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();
        BaggageDrop baggageDrop =new BaggageDrop();
        while(!testQueue.isEmpty()){
            Passenger passenger=testQueue.poll();
            baggageDrop.assignBaggageToPassenger(passenger,passenger.getNumberOfBaggage());
            for(Baggage baggage:passenger.getBaggageList()){
                assertTrue(baggage.getContent().length()>0);
            }
        }
    }
    @Test
    @Order(6)
    public void baggageCountIsPerfect() throws IOException, WriterException {
        Queue<Passenger> businessQ=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();
        Queue<Passenger> economyQ=fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue();
        BaggageDrop baggageDrop =new BaggageDrop();

        for(Passenger passenger:businessQ){
            baggageDrop.assignBaggageToPassenger(passenger,passenger.getNumberOfBaggage());
        }
        for(Passenger passenger:economyQ){
            baggageDrop.assignBaggageToPassenger(passenger,passenger.getNumberOfBaggage());
        }

        int counterBusinessBaggage=0;
        int counterEconomyBaggage=0;

        for(Passenger passenger:businessQ){
            List<Baggage> testBB=passenger.getBaggageList();
            counterBusinessBaggage+=testBB.size();
        }

        for (Passenger passenger:economyQ){
            List<Baggage> testEB=passenger.getBaggageList();
            counterEconomyBaggage+=testEB.size();
        }
        assertEquals(375,counterBusinessBaggage+counterEconomyBaggage);
    }

    @Test
    @Order(7)
    public void checkIfNoOfContentsMatchNoOfBaggage(){

        List<String> entries_=new ArrayList<>();
        int counterBaggage=0;
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(Configuration.INSTANCE.assignmentFile));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                String[] entries=line.split(";");
                counterBaggage+=Integer.parseInt(entries[2]);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int counterContent=0;
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(Configuration.INSTANCE.baggageContents));
            while ((bufferedReader.readLine())!= null){
                counterContent++;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(counterBaggage-375,counterContent);
    }
    @Test
    @Order(8)
    public void checkBaggageTag() throws IOException, WriterException {
        BaggageDrop baggageDrop =new BaggageDrop();
        Queue<Passenger> businessQ=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();

        while(!businessQ.isEmpty()){
            Passenger passenger=businessQ.poll();
            baggageDrop.simulateCheckIn(fastBagDrop,passenger,flight, Position.LEFT);
            for(Baggage baggage: passenger.getBaggageList()){
                assertNotNull(baggage.getBaggageTag());
                assertNotNull(baggage.getBaggageTag().getQrCode());  //all checked in baggage have baggage Tag with qr code
            }
        }

    }

    @Test
    @Order(9)
    public void checkManagementAdministration() {

        IDCard serviceAgentIdCard=federalPolice.getIdCard();

        serviceAgent.startUpMachine(fastBagDrop,serviceAgentIdCard);
        assertEquals(FastBagDropState.ON,fastBagDrop.getCurrentState());


    }
    @Test
    @Order(10)
    public void testStartUp() {

        IDCard serviceAgentIdCard=serviceAgent.getIdCard();
        serviceAgent.startUpMachine(fastBagDrop,serviceAgentIdCard);
        assertEquals(FastBagDropState.ON,fastBagDrop.getCurrentState());
    }
    @Test
    @Order(11)
    public void testShutdown() {

        IDCard serviceAgentIdCard=serviceAgent.getIdCard();
        serviceAgent.shutDownMachine(fastBagDrop,serviceAgentIdCard);
        assertEquals(FastBagDropState.OFF,fastBagDrop.getCurrentState());
    }

    @Test
    @Order(12)
    public void testCheckIn() throws IOException, WriterException {
        fastBagDrop.getServices().getCheckIn().executeCheckIn(fastBagDrop,flight);

    }


    @Test
    @Order(13)
    public void testCheckInWthExplosives() throws IOException, WriterException {

        Baggage baggage = new Baggage();
        baggage.setContent("explosives");

        BaggageScanner baggageScanner = new BaggageScanner(StringMatchingAlgorithm.BM);
        baggageScanner.searchForExplosives(baggage);
    }

    // test 14 here
    @Test
    @Order(14)
    public void startUpOnlyThroughServiceAgent() {
// <---- play this
        // machine cannot be started with id card of federal police which is only authorized to lock and unlock machine

        // to start up machine ,one needs a id card with id card purpose for on off, which the service agent has
        IDCard federalPoliceIdCard=federalPolice.getIdCard();
        serviceAgent.startUpMachine(fastBagDrop,federalPoliceIdCard);  //<---- service agent tries to start up machine with id card of the federal police
        assertNotEquals(FastBagDropState.ON,fastBagDrop.getCurrentState());    // expected state of machine after should be on, but the current state of machine is OFF
    }

    // test 15 here
    @Test
    @Order(15)
    public void shutDownOnlyThroughServiceAgent() {
        // <---- play this
        // machine cannot be shut down with id card of federal police which is only authorized to lock and unlock machine

        // to start up machine ,one needs a id card with id card purpose for on off
        IDCard serviceAgentIdCard=serviceAgent.getIdCard();
        serviceAgent.startUpMachine(fastBagDrop,serviceAgentIdCard);   // the machine must be started for testing off successful shutdown

        IDCard federalPoliceIdCard=federalPolice.getIdCard();
        serviceAgent.shutDownMachine(fastBagDrop,federalPoliceIdCard);
        assertNotEquals(FastBagDropState.OFF,fastBagDrop.getCurrentState());    // expected state of machine after should be off, but the current state of machine is ON
    }


    @Test
    @Order(16)
    public void exportOnlyThroughServiceAgent() {


        assertTrue(serviceAgent.executeExport(fastBagDrop.getServices().getExport()));
        assertFalse(federalPolice.executeExport(fastBagDrop.getServices().getExport()));
    }

    @Test
    @Order(17)
    public void dataAnalyticsOnlyThroughServiceAgent() throws IOException {
        serviceAgent.startUpMachine(fastBagDrop,serviceAgent.getIdCard());

        fastBagDrop.getServices().getCheckIn().executeCheckIn(fastBagDrop,flight);
        serviceAgent.executeExport(fastBagDrop.getServices().getExport());
        assertTrue(serviceAgent.executeDataAnalytics(fastBagDrop.getServices().getDataAnalytics(),fastBagDrop.getFastBagDropSection(Position.LEFT).getDisplay(), fastBagDrop.getDatabase()));
        assertFalse(federalPolice.executeDataAnalytics(fastBagDrop.getServices().getDataAnalytics(),fastBagDrop.getFastBagDropSection(Position.LEFT).getDisplay(), fastBagDrop.getDatabase()));
    }


}
