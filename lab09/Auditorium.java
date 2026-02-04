package lab09;

public class Auditorium {
    private String name;
    private int columns;
    private int rows;
    private double multiplier;

    public Auditorium(String name, int rows, int columns, double multiplier) {
        this.name=name;
        this.rows=rows;
        this.columns=columns;
        this.multiplier=multiplier;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public String toString() {
        return name + ", " +  columns*rows  + " seats";
    }
}
