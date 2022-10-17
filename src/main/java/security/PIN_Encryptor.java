package security;

import identityRelevants.IDCard;

public class PIN_Encryptor {
    public static void encryptPIN(AES aes, IDCard idCard) throws Exception {
        idCard.getRfid_chip().setPIN(aes.encrypt(idCard.getRfid_chip().getPIN()));
    }
}
