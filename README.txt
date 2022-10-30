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

About the Fast Fag Drop Database:
- It has a passengerDatabase HasMap where relevant Objects from file assignment.csv are mapped to passenger's Passport ID
- The passengerDatabase HashMap cannot be used for Data Analysis nor can it be used as Log File for Baggage State Result after scanning Baggage...
- The database contains a list of Records.... this Records have information related to a passenger
- The Fast Bag Drop contains these records as List <--- which are used for Data Analysis
- The database does not contain status after baggage scan, the status with baggage id, passenger id, nanoseconds are saved in a hashmap in Export and are exported to fast_baggage_drop.csv

Repository : https://github.com/vicky-dhbw/Lufthansa_Fast_Bag_Drop.git