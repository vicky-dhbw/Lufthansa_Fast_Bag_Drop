package identityRelevants;

import flightRelevants.Gate;

public class RightBoardingPassPart extends LeftBoardingPassPart{

    private Gate gate;

    public Gate getGate() {
        return gate;
    }

    private String boardingTime;

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }
}
