package security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LogInDatabase {
    private List<String> pinsForOnOff=new ArrayList<>();

    private List<String> pinsForLockUnlock=new ArrayList<>();


    public List<String> getPinsForOnOff() {
        return pinsForOnOff;
    }

    public void setPinsForOnOff(List<String> pinsForOnOff) {
        this.pinsForOnOff = pinsForOnOff;
    }

    public List<String> getPinsForLockUnlock() {
        return pinsForLockUnlock;
    }

    public void setPinsForLockUnlock(List<String> pinsForLockUnlock) {
        this.pinsForLockUnlock = pinsForLockUnlock;
    }


}
