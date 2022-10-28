package services;

import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import identityRelevants.IDCard;

public class Shutdown {
    public void executeShutDown(FastBagDrop fastBagDrop, IDCard idCard){
        System.out.println();
        System.out.println("service agent trying to shut down fast baggage drop machine with his id card...........");

        boolean authenticationSuccessful=fastBagDrop.getLeftSection().getIdCardScanner().authenticateIdCard(idCard,fastBagDrop.getLeftSection().getDisplay());

        if(authenticationSuccessful){
            fastBagDrop.setCurrentState(FastBagDropState.OFF);
            System.out.println("-----  machine successfully shut down");
            System.out.println("Machine state: "+fastBagDrop.getCurrentState().toString());
        }
        else {
            System.out.println("machine could not be shut down");
        }
    }
}
