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

    public void displayBoardingPass(Passenger passenger){
        String source=passenger.getBoardingPass().getLeftBoardingPassPart().getSource().toString();
        String destination=passenger.getBoardingPass().getLeftBoardingPassPart().getDestination().toString();
        String flightID=passenger.getBoardingPass().getLeftBoardingPassPart().getFlightID().toString();
        String date=passenger.getBoardingPass().getLeftBoardingPassPart().getDate();
        String bookingClass=passenger.getBoardingPass().getLeftBoardingPassPart().getBookingClass().toString();
        String name=passenger.getName();
        String ticketId=passenger.getBoardingPass().getLeftBoardingPassPart().getId();
        String gate=passenger.getBoardingPass().getRightBoardingPassPart().getGate().toString();
        String time=passenger.getBoardingPass().getRightBoardingPassPart().getBoardingTime().toString();
        int numberOfBaggage=passenger.getBaggageList().size();
        //String sequence=Integer.toString(passenger.getBoardingPass().getLeftBoardingPassPart().getSequence());
        System.err.println("-----------------------------------------------------------------------------");
        System.err.println("LUFTHANSA           "+ "Ticket ID: "+ticketId+" |  "+name);
        System.err.println(name);
        System.err.println(source+"   "+flightID+"   "+bookingClass+"  "+date+"                       |  "+ source);
        System.err.println(destination+"                                            |  "+ destination);
        System.err.println("                                               |  "+flightID+" "+bookingClass+" "+date);
        System.err.println("                                               | GATE: "+gate+"   "+time);
        System.err.println("MAX "+numberOfBaggage+ "  HANDLUGGAGE");
        System.err.println("-----------------------------------------------------------------------------");
    }
}
