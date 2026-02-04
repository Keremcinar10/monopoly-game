package cs102Practice;

public class ElectricCar extends ElectricVehicle{

    public ElectricCar(String brand, String model, int year) {
            super(brand, model, year);
           
        }
    
     public void recharge() {
        System.out.println(brand + " " + model + " is recharging its battery.");
    }

    public String drive() {
        return brand + " " + model + " glides silently.";
    }

  
    public void service() {
        System.out.println(brand + " " + model + " requires a battery check.");
    }
    
}
