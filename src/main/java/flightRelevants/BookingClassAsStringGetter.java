package flightRelevants;

import identityRelevants.BookingClass;
import livingComponents.Passenger;

public class BookingClassAsStringGetter {

    public static String getBookingClassAsString(Passenger passenger){
        BookingClass bookingClass=passenger.getPassengerBookingClass();
        if(bookingClass==BookingClass.B){
            return "Business";
        }
        if(bookingClass==BookingClass.E){
            return "Economy";
        }
        if(bookingClass==BookingClass.P){
            return "Premium Economy";
        }
        return null;
    }
}
