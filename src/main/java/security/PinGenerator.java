package security;

import java.util.Random;

public class PinGenerator {

    public static String createRandomPIN(){
        char[] personalNumberChar = new char[6];
        char[] pool = new char[]{'0','1','2','3','4','5','6','7','8','9'};

        for (int i = 0; i < 6; i++) {

            int randomAlpha = new Random().nextInt(0, pool.length);
            personalNumberChar[i] = pool[randomAlpha];
        }

        return String.valueOf(personalNumberChar);
    }
}
