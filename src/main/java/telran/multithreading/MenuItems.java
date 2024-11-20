package telran.multithreading;
import java.util.Arrays;
import telran.view.InputOutput;
import telran.view.Item;


public class MenuItems {
    public static Item[] getItems() {
		Item[] items = {
				Item.of("Start the Race!", MenuItems::startRace),
                Item.of("Exit", io -> {}, true)
		};
		return items;
	}

    static void startRace(InputOutput io) {
		int countRacers = io.readInt("Please, enter number of participants","");
        int dist = io.readInt("Please, enter distance of Race","");
        Race race = new Race(dist);
        Racer[] racers = new Racer[countRacers];
        startRacers(racers, race);
        waitRaceFinishing(racers);
        System.out.println("Racer number " + Racer.getWinner()+" - WIN!");
        System.out.println("Thanks you for using app,bye!");
        System.exit(0);
	}

    private static void waitRaceFinishing(Racer[] racers) {
            Arrays.stream(racers).forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
    }

    private static void startRacers(Racer[] racers, Race race) {
            for (int i = 0; i < racers.length; i++) {
                racers[i] = new Racer(race, i);
                racers[i].start();
            }
        }
}
