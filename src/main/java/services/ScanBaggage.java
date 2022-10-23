package services;

import automatComponents.BaggageScanner;
import passengerRelevants.Baggage;

import java.util.Queue;

public class ScanBaggage {
    public void scanBaggage(Queue<Baggage> baggageQueue, BaggageScanner baggageScanner){
        while(!baggageQueue.isEmpty()){
            Baggage baggage=baggageQueue.poll();
            if(baggage.getWeight()<=23.0){
                boolean containsExplosives=baggageScanner.searchForExplosives(baggage);
                if(containsExplosives){
                    System.out.println("Baggage contains explosives");
                }
                else {
                    System.out.println("Baggage contains no explosives");
                }
            }
            else{
                System.out.println("BEEP");
                System.out.println("Baggage exceeds weight limit of 23 kg!..");
            }
        }

    }
}
