package identityRelevants;

import security.PinGenerator;

public class RFIDChip {
    private byte[] PIN;
    public byte[] getPIN() {
        return PIN;
    }
    public void setPIN(byte[] PIN) {
        this.PIN = PIN;
    }
}
