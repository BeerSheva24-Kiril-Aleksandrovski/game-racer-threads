package telran.multithreading;

public class RaceResult {
    private int number;
    private double time;

    public RaceResult(int number, double time) {
        this.number = number;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public double getTime() {
        return time;
    }
}
