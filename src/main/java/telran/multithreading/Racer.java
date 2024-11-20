package telran.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Racer extends Thread {
    private Race race;
    private int number;
    private static AtomicInteger lastIteration = new AtomicInteger(0);
    private static AtomicInteger winner = new AtomicInteger();

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    public static int getWinner() {
        return winner.get();
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= race.getDistance() && !(lastIteration.get() == race.getDistance())) {
            try {
                sleep(Race.getSleepTime());
                if (i > lastIteration.get()) {
                    lastIteration.set(i);
                    winner.set(number);
                    System.out.println("Racer number "+ number + " is leading on the "+ i +" lap!");
                }
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
