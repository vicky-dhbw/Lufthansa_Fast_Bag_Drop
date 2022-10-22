package automatComponents;

import com.sun.source.tree.LambdaExpressionTree;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import identityRelevants.BookingClass;
import livingComponents.Passenger;

import java.util.List;

public class Display {

    public boolean enterPIN(int pin){
        return true;
    }

    public void searchBookingClassSeat(Flight flight){

    }
    public void showMessage(boolean ifSuccessful, Passenger passenger){
        if(ifSuccessful){
            System.out.println("Ticket found for "+ passenger.getName()+ " and Flight LH2121");
        }
        else {
            System.out.println("„Sorry. No registered ticket found for "+passenger.getName()+" and flight LH2121");
        }
    }

    public void showTicketRelevantInformation(Database database,Passenger passenger){
        List<Object> ticketDetails=database.getListForKey(passenger.getPassport().getId());
        FlightID flightID= (FlightID) ticketDetails.get(0);
        IATAAirportCodes source= (IATAAirportCodes) ticketDetails.get(1);
        IATAAirportCodes destination=(IATAAirportCodes) ticketDetails.get(2);
        Gate gate= (Gate) ticketDetails.get(3);
        String boardingTime= (String) ticketDetails.get(4);
        String ticketId= (String) ticketDetails.get(5);
        BookingClass bookingClass= (BookingClass) ticketDetails.get(6);
        String name= (String) ticketDetails.get(7);

        System.out.println("Below details were found in database");
        System.out.println("------------------------------------------");
        System.out.println("Name: "+name);
        System.out.println("Flight ID: "+flightID.toString());
        System.out.println("Source: "+source.toString());
        System.out.println("Destination: "+destination.toString());
        System.out.println("Gate No: "+gate.toString());
        System.out.println("Boarding time: "+boardingTime);
        System.out.println("Ticket ID: "+ticketId);
        System.out.println("Booking Class: "+bookingClass.toString());
        System.out.println("------------------------------------------");

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
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("LUFTHANSA                      "+ticketId+" |  "+name);
        System.out.println(name);
        System.out.println(source+"   "+flightID+"   "+bookingClass+"  "+date+"                       |  "+ source);
        System.out.println(destination+"                                            |  "+ destination);
        System.out.println("                                               |  "+flightID+" "+bookingClass+" "+date);
        System.out.println("                                               | GATE: "+gate+"   "+time);
        System.out.println("MAX "+numberOfBaggage+ "  HANDLUGGAGE");
        System.out.println("-----------------------------------------------------------------------------");
    }
}
