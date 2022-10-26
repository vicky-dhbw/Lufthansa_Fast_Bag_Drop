package services;

import configuration.Configuration;
import identityRelevants.BoardingPass;
import livingComponents.Human;
import passengerRelevants.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Export {

    private final Map<String, List<Object>> baggageRecords=new HashMap<>();

    public void write(){

        eraseFileContent();  //file needs to be empty first, otherwise there would be too many lines
        List<Object> objects;

        for (var entry : baggageRecords.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            objects=entry.getValue();
            
            String name= (String) objects.get(0);
            String bookingClass= (String) objects.get(1);
            String passportId= (String) objects.get(2);
            String ticketId= (String) objects.get(3);

            String result= (String) objects.get(5);
            String key=entry.getKey();

            String addLine = key+ ";" + name + ";" + bookingClass + ";" + passportId + ";" + ticketId  + ";" + objects.get(4)+";"+result + Configuration.INSTANCE.lineSeparator;
            try (FileWriter fileWriter = new FileWriter(Configuration.INSTANCE.baggageLogs,true))
            {
                fileWriter.write(addLine);
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }

        }


    }

    public void eraseFileContent(){
        try (FileWriter fileWriter = new FileWriter(Configuration.INSTANCE.baggageLogs))
        {
            fileWriter.write("");
            //fileWriter.append(line);
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public Map<String, List<Object>> getBaggageRecords() {
        return baggageRecords;
    }
    public List<Object> getListForKey(String baggageId){
        return baggageRecords.get(baggageId);
    }

}
