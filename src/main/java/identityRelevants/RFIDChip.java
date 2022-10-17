package identityRelevants;

import security.AES;
import security.PinGenerator;

public class RFIDChip {
    private String PIN;
    private AES cardAES;

    public RFIDChip() throws Exception {
        PIN= PinGenerator.createRandomPIN();
        cardAES=new AES();
    }

    public AES getCardAES(){
        return cardAES;
    }
    public String getPIN() {
        return PIN;
    }
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
