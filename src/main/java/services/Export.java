package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Export {

    private final Map<String, List<Object>> baggageRecords=new HashMap<>();

    public void record(){

    }


    public Map<String, List<Object>> getBaggageRecords() {
        return baggageRecords;
    }
}
