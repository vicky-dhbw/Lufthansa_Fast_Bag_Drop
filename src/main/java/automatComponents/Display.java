package automatComponents;

import flightRelevants.Flight;
import livingComponents.Passenger;

public class Display {

    public boolean enterPIN(int pin){
        return true;
    }

    public void searchBookingClassSeat(Flight flight){

    }
    public void showMessage(boolean ifSuccessful, Passenger passenger){
        if(ifSuccessful){
            System.err.println("Ticket found for "+ passenger.getName());
        }
        else {
            System.err.println("„Sorry. No registered ticket found for "+passenger.getName()+" and flight LH2121");
        }
    }
}
