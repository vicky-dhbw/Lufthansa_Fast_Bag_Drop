package automatComponents;

import java.util.ArrayList;

public class Sensor {

    private final ArrayList<IBaggageDetectorListener> listenerList;

    public Sensor() {
        listenerList = new ArrayList<>();
    }

    public void showDetectedObjects() {
        for (IBaggageDetectorListener listener : listenerList) {
            listener.revealPresence();
        }
    }

    public void addListener(IBaggageDetectorListener listener) {
        listenerList.add(listener);
        listener.revealPresence();
    }

    public void removeListener(IBaggageDetectorListener listener) {
        listenerList.remove(listener);
    }
}
