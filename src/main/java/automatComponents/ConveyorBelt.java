package automatComponents;

import passengerRelevants.Baggage;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConveyorBelt {

    Queue<Baggage> baggageQueue;  //note --> this queue only stores baggage related to a passenger and then removes it from the conveyor belt

    public ConveyorBelt(){
        baggageQueue=new LinkedList<>();
    }
    public Queue<Baggage> acceptBaggage(List<Baggage> baggage, Sensor sensor,Display display){
        baggageQueue.addAll(baggage);
        for(Baggage baggage_:baggageQueue){
            sensor.addListener(baggage_);
            display.showBaggageWeight(baggage_);     //display showing the weight of the bag...
        }
        return baggageQueue;
    }

    public void removeBaggage(){
        while(!baggageQueue.isEmpty()){
            baggageQueue.remove();
        }
    }


}
