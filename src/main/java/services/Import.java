package services;

import automatComponents.*;
import configuration.Configuration;
import flightRelevants.Flight;
import flightRelevants.FlightID;
import flightRelevants.Gate;
import flightRelevants.IATAAirportCodes;
import identityRelevants.BoardingPass;
import identityRelevants.BookingClass;
import identityRelevants.LeftBoardingPassPart;
import identityRelevants.RightBoardingPassPart;
import livingComponents.Passenger;
import passengerRelevants.Baggage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Import {

    public void executeImport(Flight forFlight, FastBagDrop fastBagDrop){

        fastBagDrop.getServices().getExport().eraseFileContent();  //the file needs to be erased to take new records....otherwise the file could be very large
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Configuration.INSTANCE.assignmentFile));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(";");
                List<Object> databaseObjects=new ArrayList<Object>();
                FlightID flightID=forFlight.getFlightID();
                IATAAirportCodes source=forFlight.getSource();
                IATAAirportCodes destination=forFlight.getDestination();
                Gate gate=forFlight.getFlightGate();
                String boardingTime= forFlight.getBoardingTime();
                String ticketId=entries[5];
                String key=entries[4];
                String name=entries[3];
                BookingClass bookingClass=BookingClassCreator.createBookingClass(entries[1]);
                String seqNo=entries[0];

                databaseObjects.add(flightID);
                databaseObjects.add(source);
                databaseObjects.add(destination);
                databaseObjects.add(gate);
                databaseObjects.add(boardingTime);
                databaseObjects.add(ticketId);
                databaseObjects.add(bookingClass);
                databaseObjects.add(name);
                databaseObjects.add(seqNo);


                fastBagDrop.getDatabase().getPassengerDatabase().put(key,databaseObjects);
                Passenger passenger=createPassenger(line);
                if(bookingClass==BookingClass.B){
                    addPassengersToBusinessQueue(fastBagDrop,passenger);
                }
                else if(bookingClass==BookingClass.P|| bookingClass==BookingClass.E){
                    addPassengerToEconomyQueue(fastBagDrop,passenger);
                }
            }
        } catch (Exception e) {
            System.out.println();
        }

    }



    public Passenger createPassenger(String line){
        String[] entries = line.split(";");
        Passenger passenger=new Passenger();
        passenger.setName(entries[3]);
        passenger.getPassport().setId(entries[4]);
        passenger.setPassengerBookingClass(BookingClassCreator.createBookingClass(entries[1]));  //comparator relevant for queueing
        passenger.setNumberOfBaggage(Integer.parseInt(entries[2]));    //since a passenger knows how much luggage he is carrying
        passenger.getPassport().setName(entries[3]);

        return passenger;
    }

    public void addPassengersToBusinessQueue(FastBagDrop fastBagDrop,Passenger passenger){
       fastBagDrop.getLeftSection().getBusinessQueue().addPassenger(passenger);

    }
    public void addPassengerToEconomyQueue(FastBagDrop fastBagDrop,Passenger passenger){
        fastBagDrop.getRightSection().getEconomyQueue().addPassenger(passenger);
    }
}

