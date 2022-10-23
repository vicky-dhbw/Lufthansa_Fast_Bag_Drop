package passengerRelevants;

import automatComponents.IBaggageDetectorListener;

public class Baggage implements IBaggageDetectorListener {

    private String content;
    private double weight;

    private BaggageTag baggageTag;

    public Baggage(){
        baggageTag=new BaggageTag();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public BaggageTag getBaggageTag() {
        return baggageTag;
    }

    public void setBaggageTag(BaggageTag baggageTag) {
        this.baggageTag = baggageTag;
    }

    @Override
    public void revealPresence() {
        System.out.println("baggage is on conveyor belt-------");
        System.out.println("---------baggage detected by sensor------------- ");
    }
}
