package automatComponents;

import livingComponents.Passenger;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EconomyQueue {

    private final Queue<Passenger> economyQueue;
    Comparator<Passenger> passengerClassComparator = (p1, p2) -> p1.getBoardingPass().getLeftBoardingPassPart().getBookingClass().getClassCode() - p2.getBoardingPass().getLeftBoardingPassPart().getBookingClass().getClassCode();

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
