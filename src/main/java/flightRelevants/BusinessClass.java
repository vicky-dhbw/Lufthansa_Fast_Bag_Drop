package flightRelevants;

public class BusinessClass {

    public final int numberOfRowsBC=8;
    public final int numberOfColumnsBC=6;  //BC: business class

    Seat[][] seats=new Seat[numberOfRowsBC][numberOfColumnsBC];

    public BusinessClass(){
        for(int i=0;i<numberOfRowsBC;i++){
            for(int j=0;j<numberOfColumnsBC;j++){
                seats[i][j]=new Seat();
                seats[i][j].setSeatId(setSeatID(i,j));
            }
        }
    }
    public static String setSeatID(int i,int j){
        char[] columns=new char[]{'A','C','D','G','H','K'};
        return (Integer.toString(i+1))+(columns[j]);
    }

    public Seat[][] getSeats(){
        return seats;
    }

}
