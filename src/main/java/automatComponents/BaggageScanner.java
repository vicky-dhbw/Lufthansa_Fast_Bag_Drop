package automatComponents;

import passengerRelevants.Baggage;

import searchAlgorithms.*;
import security.*;

public class BaggageScanner {

    private IStringMatching stringMatching;

    public boolean searchForExplosives(Baggage baggage){
        System.out.println("searching for explosives in baggage /////----o---");

        int contains=search("explosives",baggage.getContent());
        if(contains==-1){
            return false;
        }
        return true;
    }
    public BaggageScanner(StringMatchingAlgorithm stringMatchingAlgorithm) {

        if(stringMatchingAlgorithm==StringMatchingAlgorithm.BF){
            stringMatching=new BruteForce();
        }
        if(stringMatchingAlgorithm==StringMatchingAlgorithm.BM){
            stringMatching=new BoyerMoore();
        }
        if(stringMatchingAlgorithm==StringMatchingAlgorithm.KMP){
            stringMatching=new KnuthMorrisPratt();
        }
    }

    public int search(String pattern, String baggageContent) {
        return stringMatching.search(baggageContent, pattern);
    }
}
