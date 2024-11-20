package telran.multithreading;

import java.util.Random;
import static telran.multithreading.RaceConfig.*;

public class Race {
    
    private int distance;

    public Race(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public static long getSleepTime() {
        Random random = new Random();
        return random.nextInt(MAX_SLEEP_TIMEOUT - MIN_SLEEP_TIMEOUT) + MIN_SLEEP_TIMEOUT;
    }
}
