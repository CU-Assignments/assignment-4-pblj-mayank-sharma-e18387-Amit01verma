import java.util.HashSet;
import java.util.Set;

class TicketManager {
    private final Set<Integer> bookedSeats = new HashSet<>();

    // Synchronized method to book a seat
    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (bookedSeats.contains(seatNumber)) {
            System.out.println(customerType + " booking failed: Seat " + seatNumber + " is already booked.");
            return false;
        }
        bookedSeats.add(seatNumber);
        System.out.println(customerType + " successfully booked Seat " + seatNumber);
        return true;
    }
}

class BookingThread extends Thread {
    private final TicketManager manager;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketManager manager, int seatNumber, String customerType, int priority) {
        this.manager = manager;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority);
    }

    @Override
    public void run() {
        manager.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketManager manager = new TicketManager();

        // Creating multiple threads for VIP and Regular bookings
        Thread vip1 = new BookingThread(manager, 1, "VIP", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(manager, 2, "VIP", Thread.MAX_PRIORITY);
        Thread regular1 = new BookingThread(manager, 1, "Regular", Thread.MIN_PRIORITY);
        Thread regular2 = new BookingThread(manager, 2, "Regular", Thread.MIN_PRIORITY);

        // Start booking threads
        vip1.start();
        regular1.start();
        vip2.start();
        regular2.start();
    }
}

