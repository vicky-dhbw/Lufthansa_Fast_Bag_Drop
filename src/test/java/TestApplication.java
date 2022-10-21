
import automatComponents.FastBagDrop;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import livingComponents.FederalPolice;
import livingComponents.ServiceAgent;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class TestApplication {

    FastBagDrop fastBagDrop;
    ServiceAgent serviceAgent;
    Flight flight;
    @BeforeEach
    public void setUp(){
        flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG, Gate.A05);
        serviceAgent=new ServiceAgent();
        FederalPolice federalPolice=new FederalPolice();
        fastBagDrop=new FastBagDrop();
        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);
    }

    @Test
    @Order(1)
    public void TestQueueNotEmpty(){
        assertNotNull(fastBagDrop.getLeftSection().getQueue());
        assertNotNull(fastBagDrop.getRightSection().getQueue());
    }
    @Test
    @Order(2)
    public void testBusinessQueue(){
        //assertEquals(48,fastBagDrop.getLeftSection().getQueue().s);
    }

}
