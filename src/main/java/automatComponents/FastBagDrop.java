package automatComponents;

import automatComponents.FastBagDropSection;
import flightRelevants.Flight;
import services.BaggageDrop;
import services.Services;

import java.util.UUID;

public class FastBagDrop {

    private UUID serialNumber;
    private Manufacturer manufacturer;
    private FastBagDropState currentState;
    private final FastBagDropSection rightSection=new FastBagDropSection();
    private final FastBagDropSection leftSection=new FastBagDropSection();
    private final Database database=new Database();

    private final Services services=new Services();

    public FastBagDrop() {
        this.serialNumber = UUID.randomUUID();
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public Services getServices() {
        return services;
    }

    public FastBagDropState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(FastBagDropState currentState) {
        this.currentState = currentState;
    }

    public FastBagDropSection getRightSection() {
        return rightSection;
    }

    public FastBagDropSection getLeftSection() {
        return leftSection;
    }

}
