package Controller;

public abstract class Event implements Displayable {
    int id;
    String title;
    String city;
    double basePrice;
    int totalSeats;
    int availableSeats;

    Event(int id, String title, String city, double basePrice, int totalSeats) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.basePrice = basePrice;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public void show() {
        System.out.println(id + ". " + title + " | " + city + " | Seats: " + availableSeats + "/" + totalSeats + " | Price: " + basePrice);
    }

    abstract double calculateAmount(int count);
}
