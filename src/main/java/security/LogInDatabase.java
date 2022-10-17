package security;

import java.util.HashMap;
import java.util.UUID;

public class LogInDatabase {
    private final HashMap<UUID,String> uuidPinDatabase=new HashMap<>();

    public HashMap<UUID,String> getUuidPinDatabase(){
        return uuidPinDatabase;
    }

    public void addToDatabase(UUID uuid,String pin){
        uuidPinDatabase.put(uuid,pin);
    }
}
