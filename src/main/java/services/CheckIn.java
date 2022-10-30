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
        // QR code is same for all baggage since it should contain flight, source and destination info
        // this qr code will be assigned to every baggage tag after successful check in
        // the qr code is saved as buffered image in Baggage Tag
    }

    public boolean executeCheckIn(FastBagDrop fastBagDrop,Flight flight) throws IOException {

        // execute check in uses the baggage drop to simulate check in
        // execution of left section is carried out first as per specification <-- business queue
        // passengers are removed from the queue and sent for baggage drop
        // see parameter baggage.simulateCheckIn()

        while (!(fastBagDrop.getLeftSection().getBusinessQueue().getBusinessQueue().isEmpty())){
            Passenger passenger=fastBagDrop.getLeftSection().getBusinessQueue().removePassenger();
            baggageDrop.simulateCheckIn(fastBagDrop,passenger,flight,Position.LEFT);
            // go to Baggage Drop to see what happens to the passenger
        }
        // the economy queue is simulated right after business queue
        while (!(fastBagDrop.getRightSection().getEconomyQueue().getEconomyQueue().isEmpty())){
            Passenger passenger_=fastBagDrop.getRightSection().getEconomyQueue().removePassenger();
            baggageDrop.simulateCheckIn(fastBagDrop,passenger_,flight,Position.LEFT);
            // go to Baggage Drop to see what happens to the passenger

        }
        return true;
    }

}
