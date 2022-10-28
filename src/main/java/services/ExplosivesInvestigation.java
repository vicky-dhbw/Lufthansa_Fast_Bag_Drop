package services;

import automatComponents.BaggageScanner;
import automatComponents.FastBagDropSection;
import automatComponents.Position;
import passengerRelevants.Baggage;
import security.searchAlgorithms.StringMatchingAlgorithm;

public class ExplosivesInvestigation {

    public boolean searchForExplosives(FastBagDropSection fastBagDropSection, Position position, Baggage baggage){

        //scanner uses default brute force to scan baggage
        // you can try baggageScanner = new BaggageScanner(StringMatchingAlgorithm.BM);  <-- uses boyer moore
        // also try baggageScanner = new BaggageScanner(StringMatchingAlgorithm.KMP); <-- knuth-morris-algorithm

        fastBagDropSection.setBaggageScanner(new BaggageScanner(StringMatchingAlgorithm.BF));
        return fastBagDropSection.getBaggageScanner().searchForExplosives(baggage);
    }
}
