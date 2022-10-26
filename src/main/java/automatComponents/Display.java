package automatComponents;

import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import identityRelevants.BookingClass;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import services.Record;

import java.util.List;
import java.util.Map;

public class Display {

    public boolean enterPIN(int pin){
        return true;
    }

    public void showMessage(boolean ifSuccessful, Passenger passenger){
        if(ifSuccessful){
            System.out.println("Ticket found for "+ passenger.getName()+ " and Flight LH2121");
        }
        else {
            System.out.println("„Sorry. No registered ticket found for "+passenger.getName()+" and flight LH2121");
        }
    }

    public boolean offerChoiceAndAcceptCheckInDecision(Flight flight,Passenger passenger){
        System.out.println("Proceed with check-in for flight "+flight.getFlightID()+" ?");
        System.out.println("Type YES to CHECK-IN or NO to cancel CHECK-IN");
        TakeDecision decision=passenger.takeCheckInDecision();
        return decision == TakeDecision.YES;
    }

    public void showCheckInMessage(){
        System.out.println("Checking you in...............");
    }

    public void showBaggageWeight(Baggage baggage){
        System.out.println("Baggage weighs: "+baggage.getWeight()+ " kg");
    }

    public int getNumberOfBaggage(Passenger passenger){
        return passenger.getNumberOfBaggage();
    }

    public void showCancellationMessage(){
        System.out.println("Check-In cancelled by user...");
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
        System.out.println();


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
        String seatId=passenger.getBoardingPass().getLeftBoardingPassPart().getSeatId();
        int numberOfBaggage=passenger.getBaggageList().size();
        //String sequence=Integer.toString(passenger.getBoardingPass().getLeftBoardingPassPart().getSequence());
        System.out.println();
        System.out.println("BOARDING PASS FOR "+passenger.getName().toUpperCase());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("LUFTHANSA                      "+ticketId+" /  "+name);
        System.out.println(name+"                                     /  "+ticketId);
        System.out.println(source+"   "+flightID+"   "+bookingClass+"  "+date+"                       /  "+ source);
        System.out.println(destination+"                                            /  "+ destination);
        System.out.println("SEAT ID: "+seatId +"                                   /  "+flightID+" "+bookingClass+" "+date);
        System.out.println("                                               / GATE: "+gate+"   "+time);
        System.out.println("MAX "+numberOfBaggage+ "  HANDLUGGAGE/ LUGGAGE");
        System.out.println("-----------------------------------------------------------------------------");
    }

    public void showDataAnalytics(Map<String,List<Record>> map01){
        System.out.println();
        System.out.println("----- SHOWING DATA ANALYTICS ON DISPLAY ---------");
        System.out.println("----- TOTAL WEIGHT OF BAGGAGE GROUPING BY BOOKING CLASS -----");
        map01.forEach((k,v)->System.out.println(k+" ["+"total weight: "+ v.stream().mapToDouble(Record::getBaggageWeight).sum()+" kg]"));
    }
}
