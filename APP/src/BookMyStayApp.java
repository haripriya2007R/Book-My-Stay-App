import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {

    public static void validateRoomType(String roomType) throws InvalidBookingException {

        if (!(roomType.equals("Single") || roomType.equals("Double") || roomType.equals("Suite"))) {
            throw new InvalidBookingException("Booking failed: Invalid room type selected.");
        }
    }
}

public class BookMyStayApp{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Booking Validation");

            System.out.print("Enter guest name: ");
            String name = sc.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = sc.nextLine();

            BookingValidator.validateRoomType(roomType);


            System.out.println("Booking successful for " + name);

        } catch (InvalidBookingException e) {

            System.out.println(e.getMessage());
        }
    }
}