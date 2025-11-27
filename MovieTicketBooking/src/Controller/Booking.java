package Controller;

public class Booking implements Displayable {
    int id;
    Customer customer;
    Event event;
    int count;
    double amount;
    String status;

    Booking(int id, Customer customer, Event event, int count, double amount) {
        this.id = id;
        this.customer = customer;
        this.event = event;
        this.count = count;
        this.amount = amount;
        this.status = "CONFIRMED";
    }

    public void show() {
        System.out.println("Booking ID: " + id +
                " | " + status +
                " | Customer: " + customer.name +
                " | Event: " + event.title +
                " | Tickets: " + count +
                " | Amount: " + amount);
    }
}