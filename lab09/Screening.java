package lab09;

public class Screening {
     private Seat[][]seats;
    private Movie movie;
    private Auditorium auditorium;
    private double revenue;
    private int startTime;
   
    public Screening(Movie movie, Auditorium auditorium, int startTime) {
        this.movie=movie;
        this.auditorium=auditorium;
        this.startTime=startTime;
        seats=new Seat[auditorium.getRows()][auditorium.getColumns()];
        for (int i =0; i<seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i + 1, j + 1);
            }
        }
    }
    public int getEndTime() {
        return startTime + movie.getDuration();
    }

    public Movie getMovie() {
        return movie;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public int getStartTime() {
        return startTime;
    }

    
    public double getRevenue() {
        return revenue;
    }

    public String toString() {
        return movie.getTitle() + " is playing in " + auditorium.getName() + " from " + startTime + ":00 to " + getEndTime() + ":00";
    }

    public String getlayout() {
        String layout = " ";
        for (int j=1; j <= auditorium.getColumns(); j++) layout += j;
        layout += "\n";
        for (int i=0; i<seats.length; i++) {
            layout += (i + 1);
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked()) 
                    layout += "#";
                else 
                    layout += "_";
            }
            layout += "\n";
        }
        return layout;
    }

    public boolean available(int row, int column) {
        if (row<1 || row>seats.length || column<1 || column>seats[0].length) 
            return false;
        return !seats[row - 1][column - 1].isBooked();
    }

    public Ticket buyTicketForRange(int row, int startColumn, int endColumn) {
        for (int k = startColumn; k <= endColumn; k++) {
            if (!available(row, k)) return null;
        }
        Seat[] reservedSeats = new Seat[endColumn-startColumn + 1];
        for (int k = startColumn; k <= endColumn; k++) {
            seats[row-1][k-1].book();
            reservedSeats[k-startColumn] = seats[row-1][k-1];
        }
        double price = movie.getPrice()  * reservedSeats.length  * auditorium.getMultiplier();
        revenue += price;

        return new Ticket(this, reservedSeats, price);
    }

    
}
