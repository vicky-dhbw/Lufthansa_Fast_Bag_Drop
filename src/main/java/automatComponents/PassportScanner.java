package automatComponents;

import identityRelevants.Passport;

public class PassportScanner {

    public boolean scanPassport(Database database, Passport passport){
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("scanning passport with passport id: "+passport.getId());
        System.out.println("searching for ticket in fast bag drop database..............");
        return database.searchForTicketInDatabase(passport.getId());
    }

}
