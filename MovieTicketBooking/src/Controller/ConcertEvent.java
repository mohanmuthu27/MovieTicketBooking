package Controller;

public class ConcertEvent extends Event {
    String artist;
    boolean vipAvailable;

    ConcertEvent(int id, String title, String city, double basePrice, int totalSeats, String artist, boolean vipAvailable) {
        super(id, title, city, basePrice, totalSeats);
        this.artist = artist;
        this.vipAvailable = vipAvailable;
    }

    @Override
    public void show() {
        String vip = vipAvailable ? "VIP" : "Normal";
        System.out.println(id + ". [CONCERT] " + title + " - " + artist + " (" + vip + ") | " + city +
                " | Seats: " + availableSeats + "/" + totalSeats + " | Price: " + basePrice);
    }

    @Override
    double calculateAmount(int count) {
        double price = basePrice;
        if (vipAvailable && count >= 3) {
            price = basePrice * 1.2;
        }
        return price * count;
    }
}

