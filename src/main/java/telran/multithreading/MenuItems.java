package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;
import static telran.multithreading.RaceConfig.*;

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
        displayWinner(race);
    }

    private static void displayWinner(Race race) {
        System.out.println("Congratulations to Racer " + race.getWinner());
        
    }
    
	private static void joinRacers(Racer[] racers)  {
        for(int i = 0; i < racers.length; i++) {
            try {
                racers[i].join();
            } catch (InterruptedException e) {
                
            }
        }
    }

    private static void startRacers(Racer[] racers, Race race) {
        for(int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }
}
