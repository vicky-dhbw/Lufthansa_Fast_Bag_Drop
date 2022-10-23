package automatComponents;

import passengerRelevants.Baggage;

public class BaggageScanner {

    public boolean searchForExplosives(Baggage baggage){
        System.out.println("searching for explosives in baggage /////----o---");
        return baggage.getContent().contains("explosives");
    }
}
