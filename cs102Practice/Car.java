package cs102Practice;

public class Car extends Vehicle{
    
    public Car( String brand, String model, int year)
    {
        super(brand, model, year);
    }

    public String drive()
    {
        return brand + " " + model + " is driving smoothly.";
    }

    
    public void service() {
        System.out.println(brand + " " + model + " requires an oil change.");    }
    
}
