package Controller;

public class MovieEvent extends Event {
    String language;
    String rating;

    MovieEvent(int id, String title, String city, double basePrice, int totalSeats, String language, String rating) {
        super(id, title, city, basePrice, totalSeats);
        this.language = language;
        this.rating = rating;
    }

    @Override
    public void show() {
        System.out.println(id + ". [MOVIE] " + title + " (" + language + ", " + rating + ") | " + city +
                " | Seats: " + availableSeats + "/" + totalSeats + " | Price: " + basePrice);
    }

    @Override
    double calculateAmount(int count) {
        return basePrice * count;
    }
}


