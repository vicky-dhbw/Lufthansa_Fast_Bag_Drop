package livingComponents;

import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import identityRelevants.IDCard;

public class FederalPolice extends Employee{
    public FederalPolice() throws Exception {
    }
    public FastBagDropState unlockMachine(FastBagDrop fastBagDrop, IDCard idCard){
        System.out.println("federal police trying to unlock machine......");
        return fastBagDrop.getServices().getUnlock().unlockMachine(fastBagDrop,idCard);
    }
}
