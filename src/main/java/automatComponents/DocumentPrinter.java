package automatComponents;

import flightRelevants.VoucherType;
import identityRelevants.BookingClass;
import livingComponents.Passenger;
import passengerRelevants.Voucher;

public class DocumentPrinter {

    public void printVoucher(Passenger passenger){
        Voucher voucher=new Voucher();
        if (passenger.getPassengerBookingClass()== BookingClass.B){
            passenger.setVoucher(voucher);
            voucher.setVoucherType(VoucherType.LOUNGE);
            System.out.println("printing LOUNGE voucher for "+passenger.name);
        }
        if (passenger.getPassengerBookingClass()== BookingClass.P){
            passenger.setVoucher(voucher);
            voucher.setVoucherType(VoucherType.ACDC);
            System.out.println("printing AC DC Concert voucher for "+passenger.name);
        }
        if(passenger.getPassengerBookingClass()==BookingClass.E){
            System.out.println("sorry "+ passenger.getName()+" we have no voucher for you! Have a nice day!");
        }

    }

    public void printBoardingPass(Passenger passenger){
        String source=passenger.getBoardingPass().getLeftBoardingPassPart().getSource().toString();
        String destination=passenger.getBoardingPass().getLeftBoardingPassPart().getDestination().toString();
        String flightID=passenger.getBoardingPass().getLeftBoardingPassPart().getFlightID().toString();
        String date=passenger.getBoardingPass().getLeftBoardingPassPart().getDate();
        String bookingClass=passenger.getBoardingPass().getLeftBoardingPassPart().getBookingClass().toString();
        String name=passenger.getName();
        String ticketId=passenger.getBoardingPass().getLeftBoardingPassPart().getId();
        String gate=passenger.getBoardingPass().getRightBoardingPassPart().getGate().toString();
        String time=passenger.getBoardingPass().getRightBoardingPassPart().getBoardingTime().toString();
        String seatId=passenger.getBoardingPass().getLeftBoardingPassPart().getSeatId();
        int numberOfBaggage=passenger.getBaggageList().size();
        int sequence=passenger.getBoardingPass().getLeftBoardingPassPart().getSequence();
        System.out.println();
        System.out.println("BOARDING PASS FOR "+passenger.getName().toUpperCase());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("LUFTHANSA                      "+ticketId+" /  "+name);
        System.out.println(name+"                                     /  "+ticketId);
        System.out.println(source+"   "+flightID+"   "+bookingClass+"  "+date+"                       /  "+ source);
        System.out.println(destination+"                                            /  "+ destination);
        System.out.println("SEAT ID: "+seatId +"                                   /  "+flightID+" "+bookingClass+" "+date);
        System.out.println("SEQ: "+sequence+"                                      / GATE: "+gate+"   "+time);
        System.out.println("MAX "+numberOfBaggage+ "  HANDLUGGAGE/ LUGGAGE");
        System.out.println("-----------------------------------------------------------------------------");
    }
}
