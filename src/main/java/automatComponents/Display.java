package automatComponents;

import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import identityRelevants.BookingClass;
import identityRelevants.IDCard;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import services.Record;

import java.util.List;
import java.util.Map;

public class Display {

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

    public void showBaggageWeight(double weight){
        System.out.println("---- SHOWING BAGGAGE WEIGHT ON DISPLAY -----");
        System.out.println("Baggage weighs: "+weight+ " kg");
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

    public void showDataAnalytics1(Map<String,List<Record>> map01){
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("----- SHOWING DATA ANALYTICS ON DISPLAY ---------");
        System.out.println("-------------------------------------------------------");
        System.out.println("----- TOTAL WEIGHT OF BAGGAGE GROUPING BY BOOKING CLASS -----");
        map01.forEach((k,v)->System.out.println(k+" ["+"total weight: "+ v.stream().mapToDouble(Record::getBaggageWeight).sum()+" kg]"));
    }
    public void showDataAnalytics2(List<Record> businessClass,List<Record> premiumEconomyClass,List<Record> economyClass){
        System.out.println();
        System.out.println("----- PASSENGER LIST AND RELEVANT DETAILS GROUPING BY BOOKING CLASS -----");

        System.out.println("BUSINESS CLASS SORTED BY DESCENDING SURNAME");
        System.out.println();
        System.out.println("---------  BUSINESS CLASS");
        System.out.println();

        businessClass.forEach(r->System.out.println(r.getName()+ " "+r.getBookingClass()+" "+r.getSeatId()+" "+r.getNumberOfBaggage()));
        System.out.println();
        System.out.println("----------  PREMIUM ECONOMY CLASS");
        System.out.println();
        premiumEconomyClass.forEach(r->System.out.println(r.getName()+ " "+r.getBookingClass()+" "+r.getSeatId()+" "+r.getNumberOfBaggage()));
        System.out.println();
        System.out.println("---------- ECONOMY CLASS");
        System.out.println();
        economyClass.forEach(r->System.out.println(r.getName()+ " "+r.getBookingClass()+" "+r.getSeatId()+" "+r.getNumberOfBaggage()));


    }

    public byte[] acceptPINForStartUpShutDown(IDCard idCard){
        return idCard.getRfid_chip().getPIN();
    }
    public void showAuthenticationError(){
        System.out.println("machine could not be unlocked");
    }

}
