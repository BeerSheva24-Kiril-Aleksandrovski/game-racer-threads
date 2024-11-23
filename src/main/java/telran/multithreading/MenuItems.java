package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;
import static telran.multithreading.RaceConfig.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuItems {
    public static Item[] getItems() {
        Item[] items = {
                Item.of("Start the Race!", MenuItems::startRace),
                Item.of("Exit", io -> {
                }, true)
        };
        return items;
    }

    static void startRace(InputOutput io) {
        int nThreads = io.readNumberRange("Enter number of the racers", "Wrong number of the racers",
                MIN_THREADS, MAX_THREADS).intValue();
        int distance = io.readNumberRange("Enter distance",
                "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE).intValue();
        Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
        Racer[] racers = new Racer[nThreads];
        startRacers(racers, race);
        joinRacers(racers);
        printRacersTable(race, racers);
        displayWinner(race);
    }

    private static void displayWinner(Race race) {
        System.out.printf("\nCongratulations to Racer #%d !!\n",race.getWinner());
    }

    private static void joinRacers(Racer[] racers) {
        for (int i = 0; i < racers.length; i++) {
            try {
                racers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startRacers(Racer[] racers, Race race) {
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }

    private static void printRacersTable(Race race, Racer[] racers){
        long startTime = race.getStartTime();
        long endTime;
        List<RaceResult> results = new ArrayList<>();
        for (int i = 0; i < racers.length; i++) {
            endTime =  racers[i].getEndTime();
            double racerTime = (endTime - startTime)/ 1_000_000.0; 
            results.add(new RaceResult(racers[i].getNumber(),racerTime));
        }

        /*
         * This comment used to create Racer#11 with the same time as
         * already presented by another Racer
         * As the result will be two Racers on the 10th place
         */

        // endTime =  racers[5].getEndTime();
        // double racerTime = (endTime - startTime)/ 1_000_000.0; 
        // results.add(new RaceResult(11,racerTime));
        
        results.sort(Comparator.comparingDouble(RaceResult::getTime));
        System.out.println("\nFinal Scoreboard:");
        double lastTime = -1;
        int position = 0;        
        for (RaceResult result : results) {
            if (result.getTime() != lastTime) {     //increasing position only in case if presented time is differs
                position++;                         //to current Racer's time
            }
            System.out.printf("On the %d place Racer #%s finished in %.3f milliseconds.\n",position, result.getNumber(),result.getTime());
            lastTime = result.getTime();
        }
    }
}
