
import automatComponents.BusinessQueue;
import automatComponents.EconomyQueue;
import automatComponents.FastBagDrop;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import livingComponents.FederalPolice;
import livingComponents.Passenger;
import livingComponents.ServiceAgent;
import org.junit.jupiter.api.*;
import passengerRelevants.Baggage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
public class TestApplication {

    FastBagDrop fastBagDrop;
    ServiceAgent serviceAgent;
    Flight flight;
    @BeforeEach
    public void setUp(){
        flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG, Gate.A05);
        serviceAgent=new ServiceAgent();
        fastBagDrop=new FastBagDrop();
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
    public void assureBaggageListNotNull(){
        Queue<Passenger> testQueue=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();
        for(Passenger passenger:testQueue){
            List<Baggage> testBList=passenger.getBaggageList();
            assertTrue(testBList.size()>=1);
        }
    }

    @Test
    @Order(5)
    public void assureBaggageContent(){
        Queue<Passenger> testQueue=fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue();
        for(Passenger passenger:testQueue){
            List<Baggage> testB=passenger.getBaggageList();
            for(Baggage baggage:testB){
                assertTrue(baggage.getContent().length()>0);
            }
        }
    }

    @Test
    @Order(6)
    public void baggageCountIsPerfect(){
        Queue<Passenger> businessQ=fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue();
        Queue<Passenger> economyQ=fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue();

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
            BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/Data/assignment.csv"));
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
            BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/java/Data/baggage_content.txt"));
            while ((bufferedReader.readLine())!= null){
                counterContent++;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(counterBaggage-375,counterContent);
    }


}
