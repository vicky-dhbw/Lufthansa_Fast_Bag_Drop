package automatComponents;

import flightRelevants.Flight;
import flightRelevants.Seat;
import flightRelevants.SeatStatus;
import identityRelevants.BookingClass;

public class FreeSeatSearcher {

    public static Seat getFreeSeatForClass(Flight flight, BookingClass bookingClass){

        if(bookingClass==BookingClass.B){
            Seat[][] BSeats=flight.getBusinessClass().getSeats();
            for(Seat[] seats: BSeats){
                for(Seat seat:seats){
                    if(seat!=null){
                        if(seat.getSeatStatus()== SeatStatus.FREE){
                            System.out.println("Found a seat with ID "+ seat.getSeatID());
                            return seat;
                        }
                    }
                }
            }
        }
        if(bookingClass==BookingClass.P){
            Seat[][] PESeats=flight.getPremiumEconomyClass().getSeats();
            for(Seat[] seats: PESeats){
                for(Seat seat:seats){
                    if(seat!=null){
                        if(seat.getSeatStatus()== SeatStatus.FREE){
                            System.out.println("Found a seat with ID "+ seat.getSeatID());
                            return seat;
                        }
                    }
                }
            }
        }
        if(bookingClass==BookingClass.E){
            Seat[][] ESeats=flight.getEconomyClass().getSeats();
            for(Seat[] seats: ESeats){
                for(Seat seat:seats){
                    if(seat!=null){
                        if(seat.getSeatStatus()== SeatStatus.FREE){
                            System.out.println("Found a seat with ID "+ seat.getSeatID());
                            return seat;
                        }
                    }
                }
            }
        }
        return new Seat();
    }
}
