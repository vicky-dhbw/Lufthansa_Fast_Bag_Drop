package livingComponents;

import automatComponents.Database;
import automatComponents.Display;
import automatComponents.PassportScanner;
import automatComponents.TakeDecision;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.Passport;
import passengerRelevants.Baggage;
import passengerRelevants.Voucher;
import services.CheckIn;
import services.HandyApp;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Human{

    private final Passport passport;
    private final List<Baggage> baggageList;
    private BoardingPass boardingPass;
    private BookingClass passengerBookingClass;
    private Voucher voucher;
    private int numberOfBaggage;

    private FingerPrint fingerPrint;
    private FaceID faceID;

    private HandyApp handyApp;

    public boolean hasFaceID(){
        if(faceID.getFaceID()==null){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean hasFingerPrint(){
        if(fingerPrint.getFingerPrint()==null){
            return false;
        }
        else{
            return true;
        }
    }
    private boolean isCriminal;
    public Passenger(){
        passport=new Passport();
        baggageList=new ArrayList<>();
        boardingPass=new BoardingPass();
        isCriminal=false;     // assume passengers are not criminal
        handyApp=new HandyApp();
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }


    public TakeDecision takeCheckInDecision(){
        System.out.println(name+" is deciding whether to check-in or to go back home and sleep............");
        System.out.println("YES CHECK-IN");
        return TakeDecision.YES; //default decision
    }

    public int enterNumberOfBaggage(){
        return this.numberOfBaggage;
    }

    public BoardingPass takeBoardingPass(){
        return new BoardingPass();
    }

    public List<Baggage> getBaggage() {
        return baggageList;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass){
        this.boardingPass=boardingPass;
    }


    public Passport getPassport() {
        return passport;
    }

    public List<Baggage> getBaggageList() {
        return baggageList;
    }

    public BookingClass getPassengerBookingClass() {
        return passengerBookingClass;
    }

    public void setPassengerBookingClass(BookingClass passengerBookingClass) {
        this.passengerBookingClass = passengerBookingClass;
    }

    public int getNumberOfBaggage() {
        return numberOfBaggage;
    }

    public void setNumberOfBaggage(int numberOfBaggage) {
        this.numberOfBaggage = numberOfBaggage;
    }
    public void layBaggageOnConveyorBelt(Baggage baggage){

    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public boolean isCriminal() {
        return isCriminal;
    }

    public void setCriminal(boolean criminal) {
        isCriminal = criminal;
    }

    public HandyApp getHandyApp() {
        return handyApp;
    }

    public void setHandyApp(HandyApp handyApp) {
        this.handyApp = handyApp;
    }
}
