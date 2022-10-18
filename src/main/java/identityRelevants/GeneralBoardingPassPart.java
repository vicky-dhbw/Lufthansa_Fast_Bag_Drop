package identityRelevants;


import flightRelevants.IATAAirportCodes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class GeneralBoardingPassPart {

    private String name;
    private BookingClass bookingClass;
    private IATAAirportCodes source;
    private IATAAirportCodes destination;
    private Date date;
    private String id;
    private int sequence;


    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public BookingClass getBookingClass() {
        return bookingClass;
    }

    public void setBookingClass(BookingClass bookingClass) {
        this.bookingClass = bookingClass;
    }

    public IATAAirportCodes getSource() {
        return source;
    }

    public void setSource(IATAAirportCodes source) {
        this.source = source;
    }

    public IATAAirportCodes getDestination() {
        return destination;
    }

    public void setDestination(IATAAirportCodes destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
