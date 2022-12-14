package automatComponents;

import livingComponents.FederalPolice;
import livingComponents.ServiceAgent;
import services.Services;

import javax.xml.crypto.Data;
import java.util.UUID;

public class FastBagDrop implements IFastBagDrop{

    private UUID serialNumber;
    private Manufacturer manufacturer;
    private FastBagDropState currentState;
    private final FastBagDropSection rightSection=new FastBagDropSection(Position.RIGHT);
    private final FastBagDropSection leftSection=new FastBagDropSection(Position.LEFT);
    private Database database=new Database();

    private ServiceAgent serviceAgent=new ServiceAgent();
    private FederalPolice federalPolice=new FederalPolice();


    private final Services services=new Services();

    public FastBagDrop(Manufacturer manufacturer) throws Exception {
        this.serialNumber = UUID.randomUUID();
        this.currentState=FastBagDropState.OFF;
        this.manufacturer=manufacturer;
    }
    public void setSerialNumber(UUID serialNumber){
        this.serialNumber=UUID.randomUUID();
    }

    public void setDatabase(Database database){
        this.database=database;
    }

    public void setManufacturer(Manufacturer manufacturer){
        this.manufacturer=manufacturer;
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

    public ServiceAgent getServiceAgent() {
        return serviceAgent;
    }

    public FederalPolice getFederalPolice() {
        return federalPolice;
    }

    public void setServiceAgent(ServiceAgent serviceAgent1){
        this.serviceAgent=serviceAgent1;
    }

    public void setFederalPolice(FederalPolice federalPolice1){
        this.federalPolice=federalPolice1;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

}
