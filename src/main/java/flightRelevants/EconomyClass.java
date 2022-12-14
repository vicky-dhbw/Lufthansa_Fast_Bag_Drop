package flightRelevants;

public class EconomyClass {
    public final int numberOfRowsEC = 26;    //EC: economy class
    public final int numberOfColumnsEC = 9;

    Seat[][] seats = new Seat[numberOfRowsEC][numberOfColumnsEC];

    public EconomyClass() {
        for (int i = 0; i <numberOfRowsEC; i++) {
            for (int j = 0; j < numberOfColumnsEC; j++) {
                if (assureNullSeat(i, j)) {
                    continue;
                }
                seats[i][j] = new Seat();
                seats[i][j].setSeatId(setSeatID(i + 16, j));
                seats[i][j].setSeatClass(SeatClass.E);
            }
        }
    }

    public static String setSeatID(int i, int j) {
        char[] columns = new char[]{'A', 'B', 'C', 'D', 'E', 'G', 'H', 'J', 'K'};
        if(i>16){
            return (Integer.toString(i+1)) + (columns[j]);
        }
        else{
            return (Integer.toString(i)) + (columns[j]);
        }

    }

    public boolean assureNullSeat(int i, int j) {
        String[] nullSeats = new String[]{"02", "06", "100", "108", "250", "251", "252", "256", "257", "258"};

        for (String nullSeat : nullSeats) {
            String ifNullSeat = Integer.toString(i) + Integer.toString(j);
            if (ifNullSeat.equals(nullSeat)) {
                return true;
            }
        }

        return false;
    }
    public Seat[][] getSeats(){
        return seats;
    }

    public void printSeatIds(){
        SeatPrinter(numberOfRowsEC, numberOfColumnsEC, seats);
    }

    static void SeatPrinter(int numberOfRowsEC, int numberOfColumnsEC, Seat[][] seats) {
        for (int i = 0; i < numberOfRowsEC; i++) {
            for (int j = 0; j < numberOfColumnsEC; j++) {
                if (seats[i][j]==null) {
                    System.err.print(" X ");
                }
                else{
                    System.err.print(" "+ seats[i][j].getSeatID()+" ");
                }
            }
            System.out.println(" ");
        }
    }


}
