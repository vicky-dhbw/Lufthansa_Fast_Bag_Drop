package passengerRelevants;

public class Baggage {

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
}
