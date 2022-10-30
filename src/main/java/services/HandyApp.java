package services;

import identityRelevants.BoardingPass;
import livingComponents.Passenger;
import passengerRelevants.Baggage;
import passengerRelevants.BaggageTag;

import java.util.List;

public class HandyApp {

    private BoardingPass boardingPass;
    private List<BaggageTag> baggageTagList;

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    public List<BaggageTag> getBaggageTagList() {
        return baggageTagList;
    }

    public void setBaggageTagList(List<BaggageTag> baggageTagList) {
        this.baggageTagList = baggageTagList;
    }
}
