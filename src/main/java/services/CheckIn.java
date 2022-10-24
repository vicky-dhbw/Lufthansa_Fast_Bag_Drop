package services;

import automatComponents.*;
import flightRelevants.*;
import livingComponents.Passenger;



public class CheckIn {

    CheckInSimulator checkInSimulator=new CheckInSimulator();

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight){
        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            checkInSimulator.simulateCheckIn(fastBagDrop,passenger,flight,Position.LEFT);
            //generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight);
            //fastBagDrop.getLeftSection().getDisplay().displayBoardingPass(passenger);
        }
        while (!(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().isEmpty())){
            Passenger passenger_=fastBagDrop.getRightSection().getEconomyQueue().removePassenger();
            //simulateCheckInForEconomyQueue(fastBagDrop,passenger_,flight);
            checkInSimulator.simulateCheckIn(fastBagDrop,passenger_,flight,Position.LEFT);
            //generateBoardingPass(fastBagDrop.getDatabase(), passenger_,flight);
            //fastBagDrop.getRightSection().getDisplay().displayBoardingPass(passenger_);
        }
    }
}
