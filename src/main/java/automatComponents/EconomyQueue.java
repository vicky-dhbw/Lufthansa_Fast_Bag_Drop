package automatComponents;

import livingComponents.Passenger;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EconomyQueue implements IQueue{


    private final Comparator<Passenger> passengerClassComparator=(p1,p2)->p1.getBoardingPass().getLeftBoardingPassPart().getBookingClass().getClassCode()-p2.getBoardingPass().getLeftBoardingPassPart().getBookingClass().getClassCode();
    private final Queue<Passenger> economyQueue=new PriorityQueue<>(passengerClassComparator);
    public Queue<Passenger> getEconomyQueue() {
        return economyQueue;
    }

    @Override
    public void addPassenger(Passenger passenger) {
        economyQueue.offer(passenger);
    }

    @Override
    public Passenger removePassenger() {
        return economyQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return economyQueue.isEmpty();
    }


}
