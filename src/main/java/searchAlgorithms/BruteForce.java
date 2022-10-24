package searchAlgorithms;

public class BruteForce implements IStringMatching {
    public int search(String text, String pattern) {
        System.out.println("......../ searching with brute force algorithm");
        int n = text.length();
        int m = pattern.length();
        int j;

        for (int i = 0; i <= (n - m); i++) {
            j = 0;

            while ((j < m) && (text.charAt(i + j) == pattern.charAt(j))) {
                j++;
            }

            if (j == m) {
                return i;
            }
        }

        return -1;
    }
}