package services;

import automatComponents.*;
import flightRelevants.*;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;
import passengerRelevants.Baggage;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckIn {
    Queue<String> contents;

    CheckInSimulator checkInSimulator=new CheckInSimulator();

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight){
        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            //simulateCheckInForBusinessQueue(fastBagDrop,passenger,flight);
            checkInSimulator.simulateCheckIn(fastBagDrop,passenger,flight,Position.LEFT);
            //generateBoardingPass(fastBagDrop.getDatabase(), passenger,flight);
            fastBagDrop.getLeftSection().getDisplay().displayBoardingPass(passenger);
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
