package telran.multithreading;

import telran.view.*;

public class Main {
  public static void main(String[] args) {
    // Runs CLI game's menu with allowing the user to enter number of racers and a
    // distance (number of iterations)
    // Creates Race
    // Runs Racers (Threads)
    // Prints out the Racer-winner number
    InputOutput io = new StandardInputOutput();
    Item[] items = MenuItems.getItems();
    Menu menu = new Menu("MultiThreading Racing Application", items);
    menu.perform(io);
    io.writeLine("Application is finished");
  }
}