package lab09;

public class Movie {
    private String title;
    private String genre;
    private int duration;
    private double price;

    public Movie(String title, String genre, int duration, double Price) {
        this.title=title;
        this.genre=genre;
        this.duration=duration;
        this.price=Price;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }


    public double getPrice() {
        return price;
    }

    public String toString() {
        return "''"+title + "'' , Genre: " + genre + " , Duration: " + duration + " hours)";
    }
}
