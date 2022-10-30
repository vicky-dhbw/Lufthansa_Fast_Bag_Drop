package automatComponents;

import flightRelevants.*;
import livingComponents.Passenger;
import services.Record;

import java.util.*;

public class Database {

    //Map has key PassportId, List linked to Ticket id contains Objects in order: IATACodes,IATACodes, Gate
    // Time as String, BookingClass, String name, String TicketId
    private final Map<String, List<Object>> passengerDatabase=new HashMap<>();
    private List<Record> recordList=new ArrayList<>();

    public Map<String, List<Object>> getPassengerDatabase() {
        return passengerDatabase;
    }

    public boolean searchForTicketInDatabase(String passportId){
        return passengerDatabase.containsKey(passportId);
    }

    public List<Object> getListForKey(String passportId){
        return passengerDatabase.get(passportId);
    }
    //the database should search a free seat for the passenger
    //for the ease of developing, the seat plan of the flight is stored in this database
    public Seat searchForFreeSeat(Flight flight, Passenger passenger){
        System.out.println("----searching flight seat for "+passenger.getName()+"---------");
        return FreeSeatSearcher.getFreeSeatForClass(flight,passenger.getPassengerBookingClass());
    }


    public List<Record> getRecordList() {
        return recordList;
    }

}
