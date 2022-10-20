
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
    }

    @Test
    @Order(1)
    public void TestQueueNotEmpty(){
        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);
        assertNotNull(fastBagDrop.getLeftSection().getQueue());
        assertNotNull(fastBagDrop.getRightSection().getQueue());
    }

}
