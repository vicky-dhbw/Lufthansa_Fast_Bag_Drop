package flightRelevants;

public class PremiumEconomyClass {

    public final int numberOfRowsPEC=3;
    public final int numberOfColumnsPEC=7; //PEC: premium economy class
    Seat[][] seats=new Seat[numberOfRowsPEC][numberOfColumnsPEC];

    public PremiumEconomyClass(){
        for(int i=0;i<numberOfRowsPEC;i++){
            for(int j=0;j<numberOfColumnsPEC;j++){
                seats[i][j]=new Seat();
                seats[i][j].setSeatId(setSeatID(i+12,j));
            }
        }
    }

    public static String setSeatID(int i,int j){
        char[] columns=new char[]{'A','C','D','E','G','H','K'};
        if(i>12){
            return (Integer.toString(i+1))+(columns[j]);
        }
        else{
            return (Integer.toString(i))+(columns[j]);
        }

    }
    public Seat[][] getSeats(){
        return seats;
    }

    public void printSeatIds(){
        EconomyClass.SeatPrinter(numberOfRowsPEC, numberOfColumnsPEC, seats);
    }

}
