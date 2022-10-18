package flightRelevants;

public class EconomyClass {
    public final int numberOfRowsEC=8;    //EC: economy class
    public final int numberOfColumnsEC=9;

    Seat[][] seats=new Seat[numberOfRowsEC][numberOfColumnsEC];

    public EconomyClass(){
        for(int i=0;i<numberOfRowsEC;i++){
            for(int j=0;j<numberOfColumnsEC;j++){
                seats[i][j]=new Seat();

            }
        }
    }

    public static String setSeatId(int i,int j){
        char[] columns=new char[]{'A','C','D','G','H','K'};
        return (Integer.toString(i+1))+(columns[j]);
    }


}
