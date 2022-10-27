package livingComponents;

import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import identityRelevants.IDCard;

public class FederalPolice extends Employee{
    public FederalPolice() throws Exception {
    }
    public void unlockMachine(FastBagDrop fastBagDrop, IDCard idCard){
        System.out.println("federal police trying to unlock machine......");
        fastBagDrop.getServices().getUnlock().unlockMachine(fastBagDrop,idCard);
    }

    public void lockMachine(FastBagDrop fastBagDrop){
        System.out.println("federal police trying to lock machine......");
        fastBagDrop.setCurrentState(FastBagDropState.LOCKED);
    }
}
