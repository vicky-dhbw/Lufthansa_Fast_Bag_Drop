package automatComponents;

import livingComponents.Passenger;

public interface IQueue {

    void addPassenger(Passenger  passenger);
    Passenger removePassenger();
    boolean isEmpty();
}
