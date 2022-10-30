Generelle Informationen
----------------------
Der Studierende S05 wurde exmatrikuliert und hat dementsprechend seine Aufgaben nicht bearbeitet.
Dies bitte bei der Bewertung beachten.

Getroffene Annahmen
----------------------
S03
- Das in den Spezifikationen genannte "Ticket" wird als unvollständiger Boarding Pass interpretiert
  und dementsprechend nicht gesondert moduliert.
-----------------------------------------------------------------------------------------
Message from S06:
-------------------
Please find QR Code generated for baggage for Flight LH2121 from FRA to HKG in src folder  src/baggageTag.jpg
Records for baggage Scan is in Data/fast_bag_drop.csv

Record Object is created for each Passenger after successful check in  --> Records are then saved in Database of Fast Baggage Drop
Note: The Fast Bag Drop Database has been implemented as an Object and not merely a hasp map

The Fast Fag Drop Database has the following:

- passengerDatabase HasMap : relevant Objects from file assignment.csv are mapped to passenger's Passport ID
  The passengerDatabase HashMap cannot be used for Data Analysis nor can it be used as Log File for baggage scan results after scanning Baggage...
  This is only useful for Import

- List fo Records : this Records have information related to a passenger
  Created after successful check of a passenger and are used for Data Analysis

- The database does not contain status after baggage scan, the status with baggage id, passenger id, nanoseconds are saved in a hashmap in Export and are exported to fast_baggage_drop.csv

Repository : https://github.com/vicky-dhbw/Lufthansa_Fast_Bag_Drop.git