package services;

import automatComponents.*;
import com.google.zxing.WriterException;
import flightRelevants.*;
import livingComponents.Passenger;

import java.io.IOException;


public class CheckIn {

    BaggageDrop baggageDrop =new BaggageDrop();

    public CheckIn() throws IOException, WriterException {
        QRCodeGenerator.generateQRCode();
    }

    public void executeCheckIn(FastBagDrop fastBagDrop,Flight flight) throws IOException {
        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            baggageDrop.simulateCheckIn(fastBagDrop,passenger,flight,Position.LEFT);

        }
        while (!(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().isEmpty())){
            Passenger passenger_=fastBagDrop.getRightSection().getEconomyQueue().removePassenger();
            baggageDrop.simulateCheckIn(fastBagDrop,passenger_,flight,Position.LEFT);

        }
    }
}
