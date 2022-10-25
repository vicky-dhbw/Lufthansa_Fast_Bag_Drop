package passengerRelevants;

import configuration.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BaggageTag {

    private String baggageTagID;
    private BufferedImage qrCode;


    public BufferedImage getQrCode() {
        return qrCode;
    }

    public void setQrCode() throws IOException {
        qrCode= ImageIO.read(new File(Configuration.INSTANCE.qrCodeFile));
        System.out.println("baggage got a qr code with source destination and flight number"); //the qr code is same for all baggage
    }

    public String getBaggageTagID() {
        return baggageTagID;
    }

    public void setBaggageTagID(String baggageTagID) {
        this.baggageTagID = baggageTagID;
    }
}
