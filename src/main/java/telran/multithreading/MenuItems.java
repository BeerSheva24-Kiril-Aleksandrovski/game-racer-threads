package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;
import static telran.multithreading.RaceConfig.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class MenuItems {
    public static Item[] getItems() {
        Item[] items = {
                Item.of("Start the Race!", MenuItems::startGame),
                Item.of("Exit", io -> {
                }, true)
        };
        return items;
    }

    static void startGame(InputOutput io) {
        int nThreads = io.readNumberRange("Enter number of the racers", "Wrong number of the racers",
                MIN_THREADS, MAX_THREADS).intValue();
        int distance = io.readNumberRange("Enter distance",
                "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE).intValue();
        Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP, new ArrayList<Racer>(), Instant.now());
        Racer[] racers = new Racer[nThreads];
        startRacers(racers, race);
        joinRacers(racers);
        displayResultsTable(race);
    }

    private static void displayResultsTable(Race race) {
        System.out.println("place\tracer number\ttime");
        ArrayList<Racer> resultsTable = race.getResultsTable();
        IntStream.range(0, resultsTable.size()).mapToObj(i -> toPrintedString(i, race))
                .forEach(System.out::println);
    }

    private static String toPrintedString(int index, Race race) {
        Racer runner = race.getResultsTable().get(index);
        return String.format("%3d\t%7d\t\t%d", index + 1, runner.getNumber(),
                ChronoUnit.MILLIS.between(race.getStartTime(), runner.getFinishTime()));
    }

    private static void joinRacers(Racer[] racers) {
        for (int i = 0; i < racers.length; i++) {
            try {
                racers[i].join();
            } catch (InterruptedException e) {

            }
        }
    }

    private static void startRacers(Racer[] racers, Race race) {
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }
}
