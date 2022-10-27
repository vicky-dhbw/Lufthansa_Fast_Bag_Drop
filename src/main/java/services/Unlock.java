package services;

import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import configuration.Configuration;
import identityRelevants.IDCard;
import security.IDCardStatus;

public class Unlock {

    public FastBagDropState unlockMachine(FastBagDrop fastBagDrop, IDCard idCard){
        System.out.println();

        boolean authenticationSuccess=false;
        if(fastBagDrop.getCurrentState()== FastBagDropState.LOCKED){
            int counter= Configuration.INSTANCE.numberOfTries;

            while (counter!=-1){
                authenticationSuccess=fastBagDrop.getLeftSection().getIdCardScanner().authenticateIdCard(idCard,fastBagDrop.getLeftSection().getDisplay());
                if(authenticationSuccess){
                    break;
                }
                counter--;
            }
            if(!authenticationSuccess){
                idCard.setIdCardStatus(IDCardStatus.LOCKED);
                fastBagDrop.getLeftSection().getIdCardScanner().failAuthenticationMessage(fastBagDrop.getLeftSection().getDisplay());
            }
            if(authenticationSuccess){
                fastBagDrop.setCurrentState(FastBagDropState.UNLOCKED);
                System.out.println("Machine state: "+fastBagDrop.getCurrentState().toString());
            }
        }

        return fastBagDrop.getCurrentState();
    }
}
