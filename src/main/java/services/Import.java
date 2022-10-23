package services;

import automatComponents.*;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Import {

    public void executeImport(Flight forFlight, FastBagDrop fastBagDrop){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/Data/assignment.csv"));
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
                //assignBaggageToPassenger(passenger,Integer.parseInt(entries[2]));
                if(bookingClass==BookingClass.B){
                    addPassengersToBusinessQueue(fastBagDrop,passenger);
                }
                else if(bookingClass==BookingClass.P|| bookingClass==BookingClass.E){
                    addPassengerToEconomyQueue(fastBagDrop,passenger);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



    public Passenger createPassenger(String line){
        String[] entries = line.split(";");
        Passenger passenger=new Passenger();
        passenger.setName(entries[3]);
        passenger.getPassport().setId(entries[4]);
        passenger.setPassengerBookingClass(BookingClassCreator.createBookingClass(entries[1]));  //comparator relevant for queueing
        passenger.setNumberOfBaggage(Integer.parseInt(entries[2]));

        return passenger;
    }

    public void addPassengersToBusinessQueue(FastBagDrop fastBagDrop,Passenger passenger){
        //G queue=new BusinessQueue();
       fastBagDrop.getLeftSection().getBusinessQueue().addPassenger(passenger);

    }
    public void addPassengerToEconomyQueue(FastBagDrop fastBagDrop,Passenger passenger){
        fastBagDrop.getRightSection().getEconomyQueue().addPassenger(passenger);
    }
}

