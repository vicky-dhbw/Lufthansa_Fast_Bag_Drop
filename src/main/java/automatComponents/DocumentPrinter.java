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
}
