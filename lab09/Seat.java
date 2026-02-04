package lab09;

public class Seat {
    private int row;
    private int column;
    private boolean booked;

    public Seat(int row, int column) {
        this.row=row;
        this.column=column;
        this.booked=false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book() {
        booked = true;
    }

    public String toString() {
        return "(" + row + "," + column + ")";
    }
}