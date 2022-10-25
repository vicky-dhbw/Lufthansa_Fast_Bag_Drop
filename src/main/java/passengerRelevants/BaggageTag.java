package passengerRelevants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BaggageTag {
    private String id;
    private BufferedImage qrCode;


    public BufferedImage getQrCode() {
        return qrCode;
    }

    public void setQrCode() throws IOException {
        qrCode= ImageIO.read(new File("src/main/java/baggageTags/baggageTag.jpg"));
        System.out.println("baggage got a qr code with source destination and flight number"); //the qr code is same for all baggage
    }
}
