package cs102Practice;

public class VehicleDemo {
    
    public static void main(String[] args) {
        
        Vehicle[] vehicles = new Vehicle[4];
        vehicles[0] = new Car("Toyota", "Camry", 2019);
        vehicles[1] = new Truck("Ford", "F-150", 2018);
        vehicles[2] = new ElectricCar("Tesla", "Model 3", 2021);
        vehicles[3] = new Car("Honda", "Accord", 2020); 
    
        System.out.println("Vehicle Behaviors:\n");

        for(Vehicle v: vehicles)
        {
            v.displayInfo();
            System.out.println(v.drive());
            v.service();

            if(v instanceof ElectricCar)
            {
                ElectricCar elk = (ElectricCar)v;
                elk.recharge();
            }
            System.out.println("-----");
        }
    }


}
