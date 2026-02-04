import java.util.ArrayList;

class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

class Package {
    private Customer sender;
    private String destinationCity;
    private double weight; // in kg
    private boolean delivered;

    public Package(Customer sender, String destinationCity, double weight) {
        this.sender = sender;
        this.destinationCity = destinationCity;
        this.weight = weight;
        this.delivered = false;
    }

    public Customer getSender() {
        return sender;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void markDelivered() {
        delivered = true;
    }

    public String toString() {
        return "To " + destinationCity + " (" + weight + "kg) - " + (delivered ? "Delivered" : "Pending");
    }
}

class Courier {
    private String name;
    private int id;
    private ArrayList<Package> assignedPackages;

    public Courier(String name, int id) {
        this.name = name;
        this.id = id;
        this.assignedPackages = new ArrayList<>();
    }

    public void assignPackage(Package p) {
        assignedPackages.add(p);
    }

    public ArrayList<Package> getAssignedPackages() {
        return assignedPackages;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name + " (Courier ID: " + id + ")";
    }
}

class LogisticsCompany {
    private ArrayList<Customer> customers;
    private ArrayList<Courier> couriers;
    private ArrayList<Package> packages;

    public LogisticsCompany() {
        customers = new ArrayList<>();
        couriers = new ArrayList<>();
        packages = new ArrayList<>();
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public void addCourier(Courier c) {
        couriers.add(c);
    }

    public void addPackage(Package p) {
        packages.add(p);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Courier> getCouriers() {
        return couriers;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }
}

public class MainApp2 {
    public static void main(String[] args) {
        LogisticsCompany company = new LogisticsCompany();

        Customer c1 = new Customer("Zeynep", 1);
        Customer c2 = new Customer("Ali", 2);
        company.addCustomer(c1);
        company.addCustomer(c2);

        Courier courier1 = new Courier("Ayşe", 101);
        Courier courier2 = new Courier("Murat", 102);
        company.addCourier(courier1);
        company.addCourier(courier2);

        Package p1 = new Package(c1, "Ankara", 3.2);
        Package p2 = new Package(c2, "İzmir", 5.5);
        Package p3 = new Package(c1, "İstanbul", 2.0);
        Package p4 = new Package(c1, "İstanbul", 3.0);

        company.addPackage(p1);
        company.addPackage(p2);
        company.addPackage(p3);
        company.addPackage(p4);

        courier1.assignPackage(p1);
        courier2.assignPackage(p2);
        courier1.assignPackage(p3);
        courier1.assignPackage(p4);

        // TODO #1: Belirli bir müşterinin tüm gönderdiği kargoları ve durumlarını yazdır
        printCustomerPackages(company, 1);

        // TODO #2: Bir kuryenin taşıdığı kargoların toplam ağırlığını hesapla
        printTotalWeightByCourier(company, 101);

        // TODO #3: Tüm teslim edilmemiş kargoları yazdır
        printUndeliveredPackages(company);

        // TODO #4: Hedef şehre göre kargoları gruplandırarak yazdır (örnek: "İzmir → 2 kargo")
        printPackageCountByDestination(company);
    }

    // Bir müşterinin gönderdiği tüm kargoları yazdır
    public static void printCustomerPackages(LogisticsCompany company, int customerId) {
        // TODO
       
         for(Package next : company.getPackages()) {
            if(next.getSender().getId() == customerId) {
                System.out.println(next);
            }
         }


    }

    // Belirli bir kuryenin taşıdığı kargoların toplam ağırlığını yazdır
    public static void printTotalWeightByCourier(LogisticsCompany company, int courierId) {
        // TODO
        Courier ourCourier = null;
        for(Courier next : company.getCouriers()) {
            if(next.getId()== courierId) {
                ourCourier= next;
            }
        }
        if(ourCourier== null ) {
            System.out.println("There is no Courier with given id! ");
            return ;
        }
        double total =0;
        for(int i=0; i<ourCourier.getAssignedPackages().size();i++) {
            total += ourCourier.getAssignedPackages().get(i).getWeight();
        }
        System.out.println("Total weight: "+total);
    }

    // Teslim edilmemiş tüm kargoları listele
    public static void printUndeliveredPackages(LogisticsCompany company) {
        // TODO
        for(Package next : company.getPackages()) {
            if(!next.isDelivered()) {
                System.out.println("-"+ next.toString());
            }
        }
    }

    // Şehre göre kaç kargo gittiğini yazdır
    public static void printPackageCountByDestination(LogisticsCompany company) {
        // TODO
        
        ArrayList<String> cities = new ArrayList<String>(); 
        for(Package p : company.getPackages()) {
            String city = p.getDestinationCity();
            if(!cities.contains(city)) {
                cities.add(city);
                int count = 0;
                for(Package other : company.getPackages()) {
                    if(other.getDestinationCity().equals(city)) {
                        count ++;
                    }
                }
                System.out.println(city + " -> "+count+" destinations:");
            }
        }
    }
}