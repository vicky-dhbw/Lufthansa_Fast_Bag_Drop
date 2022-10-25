package automatComponents;

import automatComponents.FastBagDropSection;
import com.google.zxing.WriterException;
import flightRelevants.Flight;
import services.BaggageDrop;
import services.Services;

import java.io.IOException;
import java.util.UUID;

public class FastBagDrop {

    private final UUID serialNumber;
    private Manufacturer manufacturer;
    private FastBagDropState currentState;
    private final FastBagDropSection rightSection=new FastBagDropSection(Position.RIGHT);
    private final FastBagDropSection leftSection=new FastBagDropSection(Position.LEFT);
    private final Database database=new Database();

    private final Services services=new Services();

    public FastBagDrop() throws IOException, WriterException {
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

    public Database getDatabase() {
        return database;
    }

    public FastBagDropSection getFastBagDropSection(Position position){
        if(position==Position.LEFT){
            return leftSection;
        }
        if(position==Position.RIGHT){
            return rightSection;
        }
        return null;
    }
}
