import java.util.UUID;

public class Automat {

    private final UUID serialNumber;
    private Manufacturer manufacturer;
    private State state;
    private final Section rightSection=new Section();
    private final Section leftSection=new Section();


    public Automat(UUID serialNumber) {
        this.serialNumber = UUID.randomUUID();
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }


}
