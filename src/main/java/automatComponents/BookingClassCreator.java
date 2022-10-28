package automatComponents;

import identityRelevants.BookingClass;

public class BookingClassCreator {

    public static BookingClass createBookingClass(String bookingClass){
        if(bookingClass.equals("Business")){
            return BookingClass.B;
        }
        if(bookingClass.equals("Premium Economy")){
            return BookingClass.P;
        }
        if(bookingClass.equals("Economy")){
            return BookingClass.E;
        }

        return null;
    }
}
