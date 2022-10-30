package automatComponents;

import java.util.UUID;

public interface IFastBagDrop {

    void setSerialNumber(UUID serialNumber);
    public UUID getSerialNumber();
    public Manufacturer getManufacturer();
    public void setManufacturer(Manufacturer manufacturer);

    public void setCurrentState(FastBagDropState currentState);
    public FastBagDropState getCurrentState();

    public Database getDatabase();
    public void setDatabase(Database database);


}
