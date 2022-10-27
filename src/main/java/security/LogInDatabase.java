package security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LogInDatabase {
    private List<String> pins=new ArrayList<>();

    public List<String> getPins() {
        return pins;
    }

    public void setPins(List<String> pins) {
        this.pins = pins;
    }
}
