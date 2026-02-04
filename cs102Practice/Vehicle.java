package cs102Practice;

public abstract class Vehicle implements Drivable
{
    String brand;
    String model;
    int year;
    
    public Vehicle(String brand, String model, int year)
    {
        this.brand= brand;
        this.model= model;
        this.year= year;
    }
    public void displayInfo()
    {
        System.out.println("Vehicle: " + brand + " " + model + " (" + year + ")");
    }
    public abstract void service();
}
