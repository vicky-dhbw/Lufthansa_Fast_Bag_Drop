package automatComponents;

import passengerRelevants.Baggage;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConveyorBelt {

    Queue<Baggage> baggageQueue;

    public ConveyorBelt(){
        baggageQueue=new LinkedList<>();
    }
    public Queue<Baggage> acceptBaggage(List<Baggage> baggage, Sensor sensor){
        baggageQueue.addAll(baggage);
        for(Baggage baggage_:baggageQueue){
            sensor.addListener(baggage_);
        }
        return baggageQueue;

    }

    public void removeBaggage(){
        while(!baggageQueue.isEmpty()){
            baggageQueue.remove();
        }
    }


}
