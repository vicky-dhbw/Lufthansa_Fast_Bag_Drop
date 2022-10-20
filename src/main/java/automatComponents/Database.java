package automatComponents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    //Map has key TicketId, List linked to Ticket id contains Objects in order: IATACodes,IATACodes, Gate
    // Time as String, BookingClass, String name, String passportId
    private final Map<String, List<Object>> passengerDatabase=new HashMap<>();

    public Map<String, List<Object>> getPassengerDatabase() {
        return passengerDatabase;
    }

}
