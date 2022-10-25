package services;

import automatComponents.*;
import com.google.zxing.WriterException;
import flightRelevants.*;
import livingComponents.Passenger;

import java.io.IOException;


public class CheckIn {

    CheckInSimulator checkInSimulator=new CheckInSimulator();

    public CheckIn() throws IOException, WriterException {
        QRCodeGenerator.generateQRCode();
    }

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight) throws IOException {
        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            checkInSimulator.simulateCheckIn(fastBagDrop,passenger,flight,Position.LEFT);

        }
        while (!(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().isEmpty())){
            Passenger passenger_=fastBagDrop.getRightSection().getEconomyQueue().removePassenger();
            checkInSimulator.simulateCheckIn(fastBagDrop,passenger_,flight,Position.LEFT);

        }
    }
}
