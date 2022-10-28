package automatComponents;

import identityRelevants.CardPurpose;
import identityRelevants.IDCard;
import security.EncryptionManager;

public class IDCardScanner {

    private final EncryptionManager encryptionManager=new EncryptionManager();

    public IDCardScanner() throws Exception {
    }

    public boolean authenticateIdCard(IDCard idCard,Display display){

        byte[] encryptedPIN=display.acceptPINForStartUpShutDown(idCard);
        String decryptedPin;
        try {
            decryptedPin= encryptionManager.getMachineDES().decrypt(encryptedPIN);   //service officer giving pin over id card scanner
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(idCard.getCardPurpose()== CardPurpose.ON_OFF){
            for(String pins:encryptionManager.getLogInDatabase().getPinsForOnOff()){
                if(pins.equals(decryptedPin)){
                    System.out.println("AUTHENTICATION SUCCESSFUL");
                    return true;
                }
            }
        }
        if(idCard.getCardPurpose()== CardPurpose.LOCK_UNLOCK){
            for(String pins:encryptionManager.getLogInDatabase().getPinsForLockUnlock()){
                if(pins.equals(decryptedPin)){
                    System.out.println("AUTHENTICATION SUCCESSFUL");
                    return true;
                }
            }
        }

        System.out.println("AUTHENTICATION FAILED");

        return false;

    }
    public EncryptionManager getEncryptionManager(){
        return this.encryptionManager;
    }

    public void failAuthenticationMessage(Display display){
        display.showAuthenticationError();
    }

}
