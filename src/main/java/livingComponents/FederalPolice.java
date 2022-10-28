package livingComponents;

import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import identityRelevants.CardPurpose;
import identityRelevants.IDCard;
import passengerRelevants.Baggage;

public class FederalPolice extends Employee{
    public FederalPolice() throws Exception {
        this.getIdCard().setCardPurpose(CardPurpose.LOCK_UNLOCK);
    }
    public void unlockMachine(FastBagDrop fastBagDrop, IDCard idCard){
        System.out.println("federal police trying to unlock machine......");
        fastBagDrop.getServices().getUnlock().unlockMachine(fastBagDrop,idCard);
    }

    public void lockMachine(FastBagDrop fastBagDrop){
        System.out.println("federal police trying to lock machine......");
        fastBagDrop.setCurrentState(FastBagDropState.LOCKED);
    }

    public void arrestPassenger(Passenger passenger){
        System.out.println("arresting : "+passenger.getName());
        for(Baggage baggage:passenger.getBaggageList()){
            baggage.setContent("");
            baggage.setWeight(0.0);
        }
        passenger.setCriminal(true);
    }


}
