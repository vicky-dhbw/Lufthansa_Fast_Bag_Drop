package automatComponents;

import livingComponents.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class BusinessQueue implements IQueue{

    private final Queue<Passenger> businessQueue=new LinkedList<>();

    public Queue<Passenger> getBusinessQueue() {
        return businessQueue;
    }

    @Override
    public void addPassenger(Passenger passenger) {
        businessQueue.offer(passenger);
    }

    @Override
    public Passenger removePassenger() {
        return businessQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return businessQueue.isEmpty();
    }
}