package identityRelevants;

import security.IDCardStatus;

import java.util.UUID;

public class IDCard {
    private UUID uuid;
    private final RFIDChip rfid_chip=new RFIDChip();

    private CardPurpose cardPurpose;

    private IDCardStatus idCardStatus;
    public IDCard() throws Exception {
        uuid=UUID.randomUUID();
        idCardStatus=IDCardStatus.ACTIVE;
    }

    public UUID getUuid() {
        return uuid;
    }

    public IDCardStatus getIdCardStatus() {
        return idCardStatus;
    }

    public void setIdCardStatus(IDCardStatus idCardStatus) {
        this.idCardStatus = idCardStatus;
    }

    public RFIDChip getRfid_chip(){
        return rfid_chip;
    }

    public CardPurpose getCardPurpose() {
        return cardPurpose;
    }

    public void setCardPurpose(CardPurpose cardPurpose) {
        this.cardPurpose = cardPurpose;
    }
}
