package services;

import configuration.Configuration;
import identityRelevants.BoardingPass;
import passengerRelevants.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Export {

    private final Map<String, List<Object>> baggageRecords=new HashMap<>();
    Path fileName= Path.of("configuration/fast_bag_drop.csv");

    public void record() throws IOException {

        Iterator<Map.Entry<String, List<Object>>> it = baggageRecords.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<Object>> pair = it.next();
            List<Object> recordObjects=pair.getValue();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            BoardingPass boardingPass= (BoardingPass) recordObjects.get(0);
            String passportId= (String) recordObjects.get(1);
            String baggageTagId= (String) recordObjects.get(2);
            Result result= (Result) recordObjects.get(3);

            String bookingClass=boardingPass.getLeftBoardingPassPart().getBookingClass().toString();
            String name=boardingPass.getLeftBoardingPassPart().getName();
            String ticketId=boardingPass.getLeftBoardingPassPart().getId();
            String addLine=pair.getKey()+";"+name+";"+bookingClass+";"+passportId+";"+ticketId+";"+baggageTagId+ Configuration.INSTANCE.lineSeparator;


            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(fileName)));
            writer.append(addLine);

            writer.close();
            System.out.println(addLine);
            it.remove(); // avoids a ConcurrentModificationException
        }

    }


    public Map<String, List<Object>> getBaggageRecords() {
        return baggageRecords;
    }
    public List<Object> getListForKey(String baggageId){
        return baggageRecords.get(baggageId);
    }
    public Path getFileName(){
        return fileName;
    }
}
