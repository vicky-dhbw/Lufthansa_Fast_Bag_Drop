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

    public void write(String line){
        try (FileWriter fileWriter = new FileWriter(Configuration.INSTANCE.baggageLogs,true))
        {
            fileWriter.write(line);
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
