package identityRelevants;

import flightRelevants.Flight;

public class BoardingPass{
    private RightBoardingPassPart rightBoardingPassPart=new RightBoardingPassPart();
    private LeftBoardingPassPart leftBoardingPassPart=new LeftBoardingPassPart();

    public RightBoardingPassPart getRightBoardingPassPart() {
        return rightBoardingPassPart;
    }

    public LeftBoardingPassPart getLeftBoardingPassPart() {
        return leftBoardingPassPart;
    }
    public void setRightBoardingPassPart(RightBoardingPassPart rightBoardingPassPart){
        this.rightBoardingPassPart=rightBoardingPassPart;
    }

    public void setLeftBoardingPassPart(LeftBoardingPassPart leftBoardingPassPart){
        this.leftBoardingPassPart=leftBoardingPassPart;
    }

}
