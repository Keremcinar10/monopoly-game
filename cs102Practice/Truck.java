package cs102Practice;

public class Truck extends Vehicle{
    
    public Truck( String brand, String model, int year)
    {
        super(brand, model, year);
    }

    public String drive()
    {
        return brand + " " + model + " is hauling heavy loads.";
    }

    
    public void service() {
        System.out.println(brand + " " + model + " needs a tire rotation.");    }
}
