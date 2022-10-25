package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.*;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se
import flightRelevants.FlightID;
import flightRelevants.IATAAirportCodes;

public class QRCodeGenerator {

    public void generateQRCode() throws WriterException {
        try{
            String data="Hallo Vicky";
            String path= IATAAirportCodes.FRA.toString()+" "+IATAAirportCodes.HKG.toString()+" "+ FlightID.LH2121.toString();

            BitMatrix matrix =new MultiFormatWriter().encode(data,BarcodeFormat.QR_CODE,500,500);
            MatrixToImageWriter.write(matrix,"jpg",path);
        } catch(Exception e){

        }
    }





}
