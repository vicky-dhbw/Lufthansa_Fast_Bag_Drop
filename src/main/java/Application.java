import automatComponents.BusinessQueue;
import automatComponents.EconomyQueue;
import automatComponents.FastBagDrop;
import com.google.zxing.WriterException;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import livingComponents.FederalPolice;
import livingComponents.Passenger;
import livingComponents.ServiceAgent;
import passengerRelevants.Baggage;
import services.QRCodeGenerator;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Application {
    public static void main(String[] args) throws IOException, WriterException {

        Flight flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG,Gate.A05);
        ServiceAgent serviceAgent=new ServiceAgent();
        FederalPolice federalPolice=new FederalPolice();
        FastBagDrop fastBagDrop=new FastBagDrop();
        fastBagDrop.setServiceAgent(serviceAgent);
        fastBagDrop.setFederalPolice(federalPolice);

        serviceAgent.executeImport(fastBagDrop.getServices().getImporter(),flight,fastBagDrop);
        fastBagDrop.getServices().getCheckIn().executeCheckIn(fastBagDrop,flight);
        fastBagDrop.getServices().getExport().write(serviceAgent);
    }

}
