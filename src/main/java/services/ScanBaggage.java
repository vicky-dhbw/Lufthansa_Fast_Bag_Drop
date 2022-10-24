package services;

import automatComponents.BaggageScanner;
import passengerRelevants.Baggage;
import passengerRelevants.BaggageTag;
import searchAlgorithms.StringMatchingAlgorithm;

import java.util.Queue;

public class ScanBaggage {
    public void scanBaggage(Queue<Baggage> baggageQueue, BaggageScanner baggageScanner){
        baggageScanner = new BaggageScanner(StringMatchingAlgorithm.BF);   //scanner uses default brute force to scan baggage
        // you can try baggageScanner = new BaggageScanner(StringMatchingAlgorithm.BM);  <-- uses boyer moore
        // also try baggageScanner = new BaggageScanner(StringMatchingAlgorithm.KMP); <-- knuth-morris-algorithm
        while(!baggageQueue.isEmpty()){
            Baggage baggage=baggageQueue.poll();
            if(baggage.getWeight()<=23.0){
                boolean containsExplosives= baggageScanner.searchForExplosives(baggage);
                if(!containsExplosives){
                    System.out.println("Baggage contains no explosives");
                    BaggageTag baggageTag=new BaggageTag();
                    baggage.setBaggageTag(baggageTag);
                }
                else {
                    System.out.println("Baggage contains explosives");
                }
            }
            else{
                System.out.println("BEEP");
                System.out.println("Baggage exceeds weight limit of 23 kg!..");
            }
        }

    }
}
