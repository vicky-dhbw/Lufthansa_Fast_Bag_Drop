
import automatComponents.*;
import com.google.zxing.WriterException;
import configuration.Configuration;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import livingComponents.Passenger;
import livingComponents.ServiceAgent;
import org.junit.jupiter.api.*;
import passengerRelevants.Baggage;
import services.BaggageDrop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestApplication {

    FastBagDrop fastBagDrop;
    ServiceAgent serviceAgent;
    Flight flight;
    @BeforeEach
    public void setUp() throws Exception {
        flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG, Gate.A05);
        serviceAgent=new ServiceAgent();
        fastBagDrop=new FastBagDrop();
        fastBagDrop.setServiceAgent(serviceAgent);
        fastBagDrop.setFederalPolice(fastBagDrop.getFederalPolice());
        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);
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
    public void checkBaggageQRCode() throws IOException, WriterException {
        BaggageDrop baggageDrop =new BaggageDrop();
        Queue<Passenger> businessQ=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();

        while(!businessQ.isEmpty()){
            Passenger passenger=businessQ.poll();
            baggageDrop.simulateCheckIn(fastBagDrop,passenger,flight, Position.LEFT);
            for(Baggage baggage: passenger.getBaggageList()){
                assertNotNull(baggage.getBaggageTag().getQrCode());  //all checked in baggage have baggage Tag with qr code
            }
        }

    }

}
