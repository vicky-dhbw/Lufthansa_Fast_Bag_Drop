package automatComponents;

import identityRelevants.Passport;

public class PassportScanner {

    public boolean scanPassport(Database database, Passport passport){
        return database.searchForTicketInDatabase(passport.getId());
    }

}
