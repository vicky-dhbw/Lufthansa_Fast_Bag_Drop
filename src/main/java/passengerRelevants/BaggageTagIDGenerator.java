package passengerRelevants;

import java.util.Random;

public class BaggageTagIDGenerator {

    public static String createRandomBaggageTagID(){
        char[] baggageTagID = new char[8];
        char[] pool = new char[]{'1','0','2','3','4','5','6','7','8','9'};

        for (int i = 0; i < 8; i++) {

            int randomAlpha = new Random().nextInt(pool.length);

            baggageTagID[i] = pool[randomAlpha];
        }

        String baggageID="BAG"+String.valueOf(baggageTagID);
        System.out.println("baggage tag got a baggage id: "+baggageID );

        return baggageID;
    }
}
