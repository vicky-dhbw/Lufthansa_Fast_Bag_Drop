package services;

import identityRelevants.BoardingPass;
import passengerRelevants.BaggageTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandyAppDatabase {

    public Map<String, BoardingPass> appDatabase1=new HashMap<>();   //key=passportId
    public Map<String,List<BaggageTag>> appDatabase2=new HashMap<>();  //key=passportId

    public Map<String, BoardingPass> getAppDatabase1(){
        return appDatabase1;
    }
    public Map<String,List<BaggageTag>> getAppDatabase2(){
        return appDatabase2;
    }
}
