package automatComponents;

import livingComponents.Passenger;

public interface IQueue {

    public void addPassenger(Passenger passenger);
    public Passenger removePassenger();
}
