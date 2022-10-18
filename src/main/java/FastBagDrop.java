import java.util.UUID;

public class FastBagDrop {

    private final UUID serialNumber;
    private Manufacturer manufacturer;
    private FastBagDropState currentState;
    private final FastBagDropSection rightSection=new FastBagDropSection();
    private final FastBagDropSection leftSection=new FastBagDropSection();


    public FastBagDrop(UUID serialNumber) {
        this.serialNumber = UUID.randomUUID();
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }


}
