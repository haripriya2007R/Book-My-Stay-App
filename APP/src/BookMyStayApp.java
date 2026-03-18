import java.util.*;

class Reservation {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

class CancellationService {

    private Map<String, Integer> inventory = new HashMap<>();

    private Map<String, Reservation> bookings = new HashMap<>();

    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService() {
        inventory.put("single", 5);
        inventory.put("double", 3);
        inventory.put("suite", 2);

        bookings.put("single-1", new Reservation("single-1", "single"));
    }

    public void cancelBooking(String reservationId) {

        System.out.println("Booking Cancellation");

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Invalid reservation. Cannot cancel.");
            return;
        }

        Reservation res = bookings.get(reservationId);

        bookings.remove(reservationId);

        String roomType = res.roomType;
        inventory.put(roomType, inventory.get(roomType) + 1);

        rollbackStack.push(reservationId);

        System.out.println("Booking cancelled successfully.");
        System.out.println("Inventory restored for room type: " + roomType);

        System.out.println("Rollback History (most recent first):");
        System.out.println("Released Reservation ID: " + rollbackStack.peek());

        System.out.println("Updated " + roomType + " room availability: "
                + inventory.get(roomType));
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {

        CancellationService service = new CancellationService();

        // Cancel booking
        service.cancelBooking("single-1");
    }
}