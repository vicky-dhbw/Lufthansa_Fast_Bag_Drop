package services;

import automatComponents.Database;
import automatComponents.Display;
import livingComponents.ServiceAgent;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataAnalytics {

    List<Record> recordList;

    public void executeDataAnalysis(Database database, Display display){
        recordList=database.getRecordList();
        executeQuery01(display);
        executeQuery02(display);
    }

    public void executeQuery01(Display display){
        Map<String,List<Record>> map01=recordList.stream().collect(Collectors.groupingBy(Record::getBookingClass));
        display.showDataAnalytics1(map01);

    }

    public void executeQuery02(Display display){
        Map<String,List<Record>> map01=recordList.stream().collect(Collectors.groupingBy(Record::getBookingClass));
        List<Record> businessClass=map01.get("B");
        List<Record> premiumEconomyClass=map01.get("P");
        List<Record> economyClass=map01.get("E");

        businessClass.sort(Comparator.comparing(Record::getSurname).reversed());
        premiumEconomyClass.sort(Comparator.comparing(Record::getSurname).reversed());
        economyClass.sort(Comparator.comparing(Record::getSurname).reversed());

        display.showDataAnalytics2(businessClass,premiumEconomyClass,economyClass);
    }


}
