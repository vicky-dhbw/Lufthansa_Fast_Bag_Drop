
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

}
