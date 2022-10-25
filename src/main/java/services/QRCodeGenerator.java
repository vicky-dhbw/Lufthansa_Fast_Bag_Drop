package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.*;
import com.google.zxing.qrcode.QRCodeWriter;
import java.lang.Object;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.zxing.*;
import flightRelevants.FlightID;
import flightRelevants.IATAAirportCodes;

public class QRCodeGenerator {

    public void generateQRCode() throws WriterException {
        try {
            String contents = "https://simplesolution.dev";
            String fileName = "qrcode.png";
            int width = 100;
            int height = 100;

            // generate QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, width, height);

            // write to file
            Path filePath = Paths.get(fileName);
            Object MatrixToImageWriter = new Object();
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }





}
