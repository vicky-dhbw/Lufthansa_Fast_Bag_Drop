package flightRelevants;

public class FlightSeatStatusUpdater {

    public static String reserveSeat(Seat seat, Flight flight){
        if(seat.getSeatClass()==SeatClass.B){
            for(Seat[] seats: flight.getBusinessClass().getSeats()){
                for(Seat seat_: seats){
                    if(seat_!=null){
                        if(seat_.getSeatID().equals(seat.getSeatID())){
                            seat_.setSeatStatus(SeatStatus.RESERVED);
                            System.out.println("Seat with ID: "+seat.getSeatID()+ " has been reserved for you!");
                            return seat_.getSeatID();
                        }
                    }
                }
            }
        }
        if(seat.getSeatClass()==SeatClass.P){
            for(Seat[] seats: flight.getPremiumEconomyClass().getSeats()){
                for(Seat seat_: seats){
                    if(seat_!=null){
                        if(seat_.getSeatID().equals(seat.getSeatID())){
                            seat_.setSeatStatus(SeatStatus.RESERVED);
                            System.out.println("Seat with ID: "+seat.getSeatID()+ " has been reserved for you!");
                            return seat_.getSeatID();
                        }
                    }
                }
            }
        }
        if(seat.getSeatClass()==SeatClass.E){
            for(Seat[] seats: flight.getEconomyClass().getSeats()){
                for(Seat seat_: seats){
                    if(seat_!=null){
                        if(seat_.getSeatID().equals(seat.getSeatID())){
                            seat_.setSeatStatus(SeatStatus.RESERVED);
                            System.out.println("Seat with ID: "+seat.getSeatID()+ " has been reserved for you!");
                            return seat_.getSeatID();
                        }
                    }
                }
            }
        }
        return null;
    }
}
