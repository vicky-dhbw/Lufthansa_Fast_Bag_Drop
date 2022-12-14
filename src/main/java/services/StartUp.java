package services;

import automatComponents.FastBagDrop;
import automatComponents.FastBagDropState;
import identityRelevants.CardPurpose;
import identityRelevants.IDCard;

public class StartUp {

    public void executeStartUp(FastBagDrop fastBagDrop, IDCard idCard){

        if(idCard.getCardPurpose()== CardPurpose.ON_OFF){

            System.out.println();
            System.out.println("service agent trying to start up fast baggage drop machine with his id card...........");

            boolean authenticationSuccessful=fastBagDrop.getLeftSection().getIdCardScanner().authenticateIdCard(idCard,fastBagDrop.getLeftSection().getDisplay());
            if(authenticationSuccessful){
                fastBagDrop.setCurrentState(FastBagDropState.ON);
                System.out.println("-----  machine successfully started");
                System.out.println("Machine state: "+fastBagDrop.getCurrentState().toString());
            }
            else {
                System.out.println("machine could not be started");
            }
        }
        else{
            System.out.println("user unauthorized access to start up machine");
        }

    }

}
