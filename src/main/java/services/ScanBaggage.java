package services;

import automatComponents.BaggageScanner;
import identityRelevants.BoardingPass;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import passengerRelevants.BaggageTag;
import passengerRelevants.BaggageTagIDGenerator;
import passengerRelevants.Result;
import searchAlgorithms.StringMatchingAlgorithm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Queue;

public class ScanBaggage {
    public void scanBaggage(Queue<Baggage> baggageQueue, BaggageScanner baggageScanner, BoardingPass boardingPass,Passenger passenger,Export export) throws IOException {
        baggageScanner = new BaggageScanner(StringMatchingAlgorithm.BF);   //scanner uses default brute force to scan baggage
        // you can try baggageScanner = new BaggageScanner(StringMatchingAlgorithm.BM);  <-- uses boyer moore
        // also try baggageScanner = new BaggageScanner(StringMatchingAlgorithm.KMP); <-- knuth-morris-algorithm
        while(!baggageQueue.isEmpty()){
            Baggage baggage=baggageQueue.poll();
            passenger.getBaggageList().remove(baggage);
            if(baggage.getWeight()<=23.0){
                boolean containsExplosives= baggageScanner.searchForExplosives(baggage);
                if(!containsExplosives){
                    System.out.println("......../ Baggage contains no explosives");
                    BaggageTag baggageTag=new BaggageTag();
                    baggage.setBaggageTag(baggageTag);
                    baggage.setResult(Result.OK);
                    baggage.getBaggageTag().setQrCode();
                    baggage.getBaggageTag().setBaggageTagID(BaggageTagIDGenerator.createRandomBaggageTagID());

                    boardingPass.addBaggageToTagList(baggageTag);

                    passenger.getBaggageList().add(baggage);
                }
                else {
                    System.out.println("Baggage contains explosives");
                }
            }
            else{
                System.out.println("BEEP");
                System.out.println("Baggage exceeds weight limit of 23 kg!..");
                baggage.setResult(Result.NOK);
                passenger.getBaggageList().add(baggage);
            }

        }

    }
}
