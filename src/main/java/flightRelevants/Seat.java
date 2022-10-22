package flightRelevants;

import identityRelevants.BookingClass;

public class Seat {
    private String seatId;
    private SeatStatus seatStatus;

    private SeatClass seatClass;

    public Seat(){
        seatStatus=SeatStatus.FREE;
    }


    public void setSeatId(String seatId){
        this.seatId=seatId;
    }

    public String getSeatID(){
        return seatId;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }
}
