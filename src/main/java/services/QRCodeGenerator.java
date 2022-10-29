package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import configuration.Configuration;
import flightRelevants.FlightID;
import flightRelevants.IATAAirportCodes;

import java.io.IOException;
import java.nio.file.Paths;

public class QRCodeGenerator {

    public static void generateQRCode() throws WriterException, IOException {
        try {
            String contents = IATAAirportCodes.FRA.toString()+" "+IATAAirportCodes.HKG.toString()+" "+FlightID.LH2121.toString();
            String fileName = Configuration.INSTANCE.qrCodeFile;
            int width = 500;
            int height = 500;

            BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(fileName));

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}
