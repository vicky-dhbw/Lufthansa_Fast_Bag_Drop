package automatComponents;

import livingComponents.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class BusinessQueue implements IQueue{

    private Queue<Passenger> businessQueue=new LinkedList<>();
    public BusinessQueue(){
    }

    public Queue<Passenger> getBusinessQueue() {
        return businessQueue;
    }

    public void setBusinessQueue(Queue<Passenger> businessQueue) {
        this.businessQueue = businessQueue;
    }
    public void addPassenger(Passenger passenger) {
        businessQueue.offer(passenger);
    }

    public Passenger removePassenger() {
        return businessQueue.poll();
    }
}