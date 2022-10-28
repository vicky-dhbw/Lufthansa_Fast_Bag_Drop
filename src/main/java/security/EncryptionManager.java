package security;

import identityRelevants.CardPurpose;
import identityRelevants.IDCard;

import java.util.UUID;

public class EncryptionManager {

    // the encryption manager is a part of id card scanner
    private DES des=new DES();
    private LogInDatabase logInDatabase=new LogInDatabase();

    public LogInDatabase getLogInDatabase(){
        return logInDatabase;
    }

    private IDCard IDCard;
    public EncryptionManager() throws Exception {
    }

    public void setDes(DES des){
        this.des=des;
    }

    public void setLogInDatabase(LogInDatabase logInDatabase){
        this.logInDatabase=logInDatabase;
    }

    public DES getMachineDES(){
        return des;
    }

    // set up card is relevant for service agent and federal police
    public void setUpCard(IDCard idCard) throws Exception {
        String randomPIN=PinGenerator.createRandomPIN();
        byte[] encryptedPIN= des.encrypt(randomPIN);  //random String pins are decrypted by DES and as PIN (bytes[]) for RFID chips
        idCard.getRfid_chip().setPIN(encryptedPIN);
        String decryptedPIN=des.decrypt(idCard.getRfid_chip().getPIN());  // decryption of pin is needed to store the pin as string into log in database

        if(idCard.getCardPurpose()== CardPurpose.ON_OFF){
            logInDatabase.getPinsForOnOff().add(decryptedPIN);
        }

        if(idCard.getCardPurpose()==CardPurpose.LOCK_UNLOCK){
            logInDatabase.getPinsForLockUnlock().add(decryptedPIN);
        }

    }


    public IDCard getIdCard() {
        return IDCard;
    }

    public void setCard(IDCard idCard){
        this.IDCard=idCard;
    }

}
