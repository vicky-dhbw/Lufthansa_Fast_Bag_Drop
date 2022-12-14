package services;

import automatComponents.*;
import identityRelevants.BoardingPass;
import identityRelevants.IDCard;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import passengerRelevants.BaggageTag;
import passengerRelevants.BaggageTagIDGenerator;
import passengerRelevants.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ScanBaggage {

    public void scanBaggage(Queue<Baggage> baggageQueue, FastBagDrop fastBagDrop, Position position, BoardingPass boardingPass, Passenger passenger, Database database) throws IOException {

        if(fastBagDrop.getCurrentState()==FastBagDropState.ON||fastBagDrop.getCurrentState()==FastBagDropState.UNLOCKED){
            while(!baggageQueue.isEmpty()){
                Baggage baggage=baggageQueue.poll();
                boolean baggageWeightOK=fastBagDrop.getServices().getDetermineWeight().checkWeight(baggage);
                passenger.getBaggageList().remove(baggage); // the passenger does not have the baggage in his hand, the baggage must be removed from the baggage list
                if(baggageWeightOK){

                    boolean containsExplosives= fastBagDrop.getServices().getExplosivesInvestigation().searchForExplosives(fastBagDrop.getFastBagDropSection(position),position,baggage);

                    if(!containsExplosives){
                        System.out.println("......../ Baggage contains no explosives");
                        BaggageTag baggageTag=new BaggageTag();
                        baggage.setBaggageTag(baggageTag);
                        baggage.setResult(Result.OK);
                        baggage.getBaggageTag().setQrCode();
                        baggage.getBaggageTag().setBaggageTagID(BaggageTagIDGenerator.createRandomBaggageTagID());

                        String passportId= passenger.getPassport().getId();
                        String baggageTagId=baggageTag.getBaggageTagID();
                        String result= baggage.getResult().toString();
                        String bookingClass=passenger.getPassengerBookingClass().toString();
                        String name=passenger.getName();
                        String ticketId= (String) database.getListForKey(passportId).get(5);

                        List<Object> baggageRecordsObjects=new ArrayList<>();
                        // baggageRecordsObjects.add(boardingPass);   //Ticket -> BoardingPass  // boardingPass is still empty, can only be issued after successful baggage checkIN
                        // Ticket is not realised as Object
                        baggageRecordsObjects.add(name);
                        baggageRecordsObjects.add(bookingClass);
                        baggageRecordsObjects.add(passportId);
                        baggageRecordsObjects.add(ticketId);
                        baggageRecordsObjects.add(System.nanoTime());
                        baggageRecordsObjects.add(result);

                        fastBagDrop.getServices().getExport().getBaggageRecords().put(baggageTagId,baggageRecordsObjects);  //Records

                        boardingPass.addBaggageToTagList(baggageTag);
                        passenger.getHandyApp().setBaggageTagList(passenger.getBoardingPass().getBaggageTagList());
                        passenger.getBaggageList().add(baggage);
                    }
                    else {
                        System.out.println("Baggage contains explosives");
                        fastBagDrop.getFederalPolice().lockMachine(fastBagDrop); // <----- the federal officer locks the machine if explosive is found
                        fastBagDrop.getFederalPolice().arrestPassenger(passenger);   //federal police declares passenger as criminal


                        IDCard federalPoliceIDCard=fastBagDrop.getFederalPolice().getIdCard();
                        fastBagDrop.getFederalPolice().unlockMachine(fastBagDrop,federalPoliceIDCard); //<------ federal police unlocks the machine after arresting passenger

                    }
                }
                else{
                    System.out.println("BEEP");
                    System.out.println("Baggage exceeds weight limit of 23 kg!..");
                    baggage.setResult(Result.NOK);
                    passenger.getBaggageList().add(baggage);
                }

            }
        }

    }
}
