package lab09;

public class Ticket {
    private Screening screening;
    private Seat[] seats;
    private double totalPrice;

    public Ticket(Screening screening, Seat[] seats, double totalPrice) {
        this.screening = screening;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String toString() {
        String result = "Ticket for " + screening.getMovie() + "\n";
        result += "Auditorium: " + screening.getAuditorium().getName() + this.seats.length +" seats"+ "\n";
        result += "Start Time: " + screening.getStartTime() + "\n";
        result += "Seats: ";
        for (int i =0;i< seats.length; i++) {
            result += seats[i].toString();
            if (i < seats.length-1)
                 result += ", ";
        }
        result += "\nTotal Price: $" + totalPrice;
        return result;
    }

    
}
