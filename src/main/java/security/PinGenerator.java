package security;

import java.util.Random;

public class PinGenerator {

    public static String createRandomPIN(){
        char[] personalNumberChar = new char[6];
        char[] pool = new char[]{'8','9','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', '0','4','5','6','I', 'J', 'K', 'L', 'M', 'N', 'O', 'P','7', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', '1','2','3','X', 'Y', 'Z'};

        int randomNum = new Random().nextInt(0, 10);
        char c = (char) (randomNum + '0');
        personalNumberChar[1] = c;


        for (int i = 0; i < 6; i++) {
            if (i == 1) {
                continue;
            }
            int randomAlpha = new Random().nextInt(0, pool.length);
            personalNumberChar[i] = pool[randomAlpha];
        }

        return String.valueOf(personalNumberChar);
    }
}
