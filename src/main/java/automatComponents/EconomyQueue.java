package automatComponents;

import livingComponents.Passenger;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EconomyQueue implements IQueue{

    private final Queue<Passenger> economyQueue;
    Comparator<Passenger> passengerClassComparator = (p1, p2) -> p1.getPassengerBookingClass().getClassCode() - p2.getPassengerBookingClass().getClassCode();

    public EconomyQueue(){
        economyQueue=new PriorityQueue<>(passengerClassComparator);
    }
    public Queue<Passenger> getEconomyQueue() {
        return this.economyQueue;
    }

    public void addPassenger(Passenger passenger) {
        this.economyQueue.offer(passenger);
    }

    public Passenger removePassenger() {
        return this.economyQueue.poll();
    }




}
