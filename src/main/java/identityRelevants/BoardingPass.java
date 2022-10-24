package identityRelevants;

import flightRelevants.Flight;
import passengerRelevants.BaggageTag;

import java.util.List;

public class BoardingPass{


    private List<BaggageTag> baggageTagList;
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

    public List<BaggageTag> getBaggageTagList() {
        return baggageTagList;
    }

    public void addBaggageToTagList(BaggageTag baggageTag) {
        baggageTagList.add(baggageTag);
    }
}
