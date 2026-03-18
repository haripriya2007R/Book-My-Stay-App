import java.util.*;

class Inventory {
    private Map<String, Integer> rooms;

    public Inventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public synchronized String bookRoom(String guest, String type) {
        if (rooms.get(type) > 0) {
            int count = rooms.get(type);
            rooms.put(type, count - 1);

            String roomId = type + "-" + (count);
            System.out.println("Booking confirmed for Guest: " + guest + ", Room ID: " + roomId);
            return roomId;
        } else {
            System.out.println("No rooms available for " + type);
            return null;
        }
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String key : rooms.keySet()) {
            System.out.println(key + ": " + rooms.get(key));
        }
    }
}

class BookingTask extends Thread {
    private Inventory inventory;
    private String guest;
    private String type;

    public BookingTask(Inventory inventory, String guest, String type) {
        this.inventory = inventory;
        this.guest = guest;
        this.type = type;
    }

    public void run() {
        inventory.bookRoom(guest, type);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        Inventory inventory = new Inventory();

        Thread t1 = new BookingTask(inventory, "Abhi", "Single");
        Thread t2 = new BookingTask(inventory, "Vanmathi", "Double");
        Thread t3 = new BookingTask(inventory, "Kunal", "Suite");
        Thread t4 = new BookingTask(inventory, "Subha", "Single");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        inventory.displayInventory();
    }
}