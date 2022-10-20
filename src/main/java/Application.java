import automatComponents.FastBagDrop;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;

import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {

        Flight flight=new Flight(FlightID.LH2121,"22:00", IATAAirportCodes.FRA,IATAAirportCodes.HKG,Gate.A05);
        FastBagDrop fastBagDrop=new FastBagDrop();
        fastBagDrop.getServices().getImporter().executeImport(flight);
    }

}
