import java.io.*;
import java.util.*;

class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> rooms;

    public Inventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public Map<String, Integer> getRooms() {
        return rooms;
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String key : rooms.keySet()) {
            System.out.println(key + ": " + rooms.get(key));
        }
    }
}

class PersistenceService {

    private static final String FILE_NAME = "inventory.dat";

    public static void saveInventory(Inventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public static Inventory loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Inventory) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}


public class BookMyStayApp {
    public static void main(String[] args) {

        System.out.println("System Recovery");

        Inventory inventory = PersistenceService.loadInventory();

        if (inventory == null) {
            System.out.println("No valid inventory data found. Starting fresh.");
            inventory = new Inventory();
        } else {
            System.out.println("Inventory restored successfully.");
        }
        inventory.displayInventory();

        PersistenceService.saveInventory(inventory);
    }
}