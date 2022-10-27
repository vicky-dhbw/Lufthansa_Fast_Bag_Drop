package automatComponents;

import identityRelevants.IDCard;
import security.EncryptionManager;

public class IDCardScanner {

    private final EncryptionManager encryptionManager=new EncryptionManager();

    public IDCardScanner() throws Exception {
    }

    public boolean authenticateIdCard(IDCard idCard){
        String decryptedPin;
        try {
            decryptedPin= encryptionManager.getMachineDES().decrypt(idCard.getRfid_chip().getPIN());   //service officer giving pin over id card scanner
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for(String pins:encryptionManager.getLogInDatabase().getPins()){
            if(pins.equals(decryptedPin)){
                System.out.println("AUTHENTICATION SUCCESSFUL");
                return true;
            }
        }
        System.out.println("AUTHENTICATION FAILED");

        return false;

    }
    public EncryptionManager getEncryptionManager(){
        return this.encryptionManager;
    }

}
