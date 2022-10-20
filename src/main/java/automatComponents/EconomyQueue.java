package automatComponents;

import livingComponents.Passenger;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EconomyQueue implements IQueue{

    private final Queue<Passenger> economyQueue=new PriorityQueue<>();
    private final Comparator<Passenger> passengerClassComparator=(p1,p2)->p1.getBoardingPass().getLeftBoardingPassPart().getBookingClass().getClassCode()-p2.getBoardingPass().getLeftBoardingPassPart().getBookingClass().getClassCode();
    public Queue<Passenger> getEconomyQueue() {
        return economyQueue;
    }

    @Override
    public void addPassenger(Passenger passenger) {

    }

    @Override
    public void removePassenger(Passenger passenger) {

    }

}
