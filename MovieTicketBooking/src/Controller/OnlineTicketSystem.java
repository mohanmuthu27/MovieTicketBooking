package Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class OnlineTicketSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    static int nextEventId = 1;
    static int nextCustomerId = 1;
    static int nextBookingId = 1;

    public static void main(String[] args) {

        seedData();

        while (true) {
            System.out.println("\n===== ONLINE TICKET SYSTEM =====");
            System.out.println("1. View All Events");
            System.out.println("2. Search Events by City");
            System.out.println("3. Register Customer");
            System.out.println("4. View Customers");
            System.out.println("5. Book Tickets");
            System.out.println("6. View All Bookings");
            System.out.println("7. View Bookings by Customer");
            System.out.println("8. Cancel Booking");
            System.out.println("9. Summary Report");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int ch = intInput();

            switch (ch) {
                case 1:
                    viewEvents();
                    break;
                case 2:
                    searchEventsByCity();
                    break;
                case 3:
                    registerCustomer();
                    break;
                case 4:
                    viewCustomers();
                    break;
                case 5:
                    bookTickets();
                    break;
                case 6:
                    viewAllBookings();
                    break;
                case 7:
                    viewBookingsByCustomer();
                    break;
                case 8:
                    cancelBooking();
                    break;
                case 9:
                    summaryReport();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void seedData() {
        events.add(new MovieEvent(nextEventId++, "Leo", "Chennai", 150, 50, "Tamil", "U/A"));
        events.add(new MovieEvent(nextEventId++, "Vikram", "Coimbatore", 180, 40, "Tamil", "U/A"));
        events.add(new MovieEvent(nextEventId++, "Jawan", "Chennai", 200, 60, "Hindi", "U/A"));
        events.add(new ConcertEvent(nextEventId++, "Rock Night", "Chennai", 500, 100, "Anirudh", true));
        events.add(new ConcertEvent(nextEventId++, "Classical Evening", "Madurai", 300, 80, "Ilayaraja", false));

        customers.add(new Customer(nextCustomerId++, "Mohan", "9000000011"));
        customers.add(new Customer(nextCustomerId++, "Kumar", "9000000022"));
    }

    static void viewEvents() {
        System.out.println("\n--- EVENTS ---");
        if (events.isEmpty()) {
            System.out.println("No events available");
            return;
        }
        for (Event e : events) {
            e.show();
        }
    }

    static void searchEventsByCity() {
        System.out.print("\nEnter city: ");
        String city = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Event e : events) {
            if (e.city.toLowerCase().contains(city)) {
                if (!found) {
                    System.out.println("\nEvents in " + city + ":");
                }
                e.show();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found in that city");
        }
    }

    static void registerCustomer() {
        System.out.print("\nEnter name: ");
        String name = sc.nextLine();
        System.out.print("Enter mobile: ");
        String mobile = sc.nextLine();
        Customer c = new Customer(nextCustomerId++, name, mobile);
        customers.add(c);
        System.out.println("Customer registered:");
        c.show();
    }

    static void viewCustomers() {
        System.out.println("\n--- CUSTOMERS ---");
        if (customers.isEmpty()) {
            System.out.println("No customers");
            return;
        }
        for (Customer c : customers) {
            c.show();
        }
    }

    static Event findEventById(int id) {
        for (Event e : events) {
            if (e.id == id) return e;
        }
        return null;
    }

    static Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.id == id) return c;
        }
        return null;
    }

    static void bookTickets() {
        if (events.isEmpty() || customers.isEmpty()) {
            System.out.println("Add events and customers first");
            return;
        }

        viewEvents();
        System.out.print("\nEnter Event ID: ");
        int eventId = intInput();
        Event event = findEventById(eventId);
        if (event == null) {
            System.out.println("Event not found");
            return;
        }

        viewCustomers();
        System.out.print("\nEnter Customer ID: ");
        int custId = intInput();
        Customer customer = findCustomerById(custId);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }

        System.out.print("Enter number of tickets: ");
        int count = intInput();
        if (count <= 0) {
            System.out.println("Invalid count");
            return;
        }
        if (count > event.availableSeats) {
            System.out.println("Only " + event.availableSeats + " seats available");
            return;
        }

        double amount = event.calculateAmount(count);
        event.availableSeats -= count;

        Booking b = new Booking(nextBookingId++, customer, event, count, amount);
        bookings.add(b);

        System.out.println("\nTicket Booked:");
        b.show();
    }

    static void viewAllBookings() {
        System.out.println("\n--- BOOKINGS ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings");
            return;
        }
        for (Booking b : bookings) {
            b.show();
        }
    }

    static void viewBookingsByCustomer() {
        if (customers.isEmpty() || bookings.isEmpty()) {
            System.out.println("No data");
            return;
        }
        viewCustomers();
        System.out.print("\nEnter Customer ID: ");
        int id = intInput();
        Customer c = findCustomerById(id);
        if (c == null) {
            System.out.println("Customer not found");
            return;
        }
        System.out.println("\nBookings for " + c.name + ":");
        boolean found = false;
        for (Booking b : bookings) {
            if (b.customer.id == id) {
                b.show();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings for this customer");
        }
    }

    static void cancelBooking() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings");
            return;
        }
        viewAllBookings();
        System.out.print("\nEnter Booking ID to cancel: ");
        int id = intInput();
        Booking toCancel = null;
        for (Booking b : bookings) {
            if (b.id == id) {
                toCancel = b;
                break;
            }
        }
        if (toCancel == null) {
            System.out.println("Booking not found");
            return;
        }
        if (!toCancel.status.equals("CONFIRMED")) {
            System.out.println("Already cancelled");
            return;
        }
        toCancel.status = "CANCELLED";
        toCancel.event.availableSeats += toCancel.count;
        System.out.println("Booking cancelled");
    }

    static void summaryReport() {
        System.out.println("\n--- SUMMARY REPORT ---");
        System.out.println("Total Events   : " + events.size());
        System.out.println("Total Customers: " + customers.size());
        System.out.println("Total Bookings : " + bookings.size());

        double totalAmount = 0;
        int activeBookings = 0;
        for (Booking b : bookings) {
            if (b.status.equals("CONFIRMED")) {
                totalAmount += b.amount;
                activeBookings++;
            }
        }
        System.out.println("Active Bookings: " + activeBookings);
        System.out.println("Total Revenue  : " + totalAmount);

        System.out.println("\nSeats filled per event:");
        for (Event e : events) {
            int filled = e.totalSeats - e.availableSeats;
            System.out.println(e.id + ". " + e.title + " | Filled: " + filled + " / " + e.totalSeats);
        }
    }

    static int intInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Enter number: ");
            }
        }
    }
}
