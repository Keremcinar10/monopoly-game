import java.util.ArrayList;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

class Order {
    private Customer customer;
    private ArrayList<Product> products;

    public Order(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order by " + customer.getName() + " | Total: $" + getTotalPrice();
    }
}

class OnlineStore {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;

    public OnlineStore() {
        products = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void registerCustomer(Customer c) {
        customers.add(c);
    }

    public void placeOrder(Order o) {
        orders.add(o);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}

public class MainApp {
    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();

        // Ürün ve müşteri oluştur
        Product p1 = new Product("Laptop", 1500);
        Product p2 = new Product("Mouse", 25);
        Product p3 = new Product("Keyboard", 50);

        Customer c1 = new Customer("Elif", 101);
        Customer c2 = new Customer("Bora", 102);

        store.addProduct(p1);
        store.addProduct(p2);
        store.addProduct(p3);

        store.registerCustomer(c1);
        store.registerCustomer(c2);

        // TODO #1: Elif'in siparişine Laptop ve Mouse ekleyip siparişi oluştur
        createAndPlaceOrder(store, 101, new String[]{"Laptop", "Mouse"});

        // TODO #2: Bora'nın siparişine Keyboard ekleyip siparişi oluştur
        createAndPlaceOrder(store, 102, new String[]{"Keyboard"});

        // TODO #3: Tüm siparişleri ve detaylarını yazdır
        printAllOrders(store);

        // TODO #4: Belirli bir müşteriye ait toplam harcamayı yazdır (örneğin Elif)
        printCustomerTotalSpending(store, 101);
    }

    // Müşteri ID'sine göre ürün isimlerini bul ve yeni sipariş oluştur
    public static void createAndPlaceOrder(OnlineStore store, int customerId, String[] productNames) {
        // TODO: Bu metodu doldur
        Customer ourCustomer = null;
        for(Customer next : store.getCustomers()) {
            if(next.getId()== customerId) {
                ourCustomer= next;
            }
        }
        if(ourCustomer==null) {
            System.out.println("There is no customer with given id!");
            return;
        }
        Order newOrder = new Order(ourCustomer);
        for(String name : productNames) {
            for(int i=0; i<store.getProducts().size();i++) {
                if(store.getProducts().get(i).getName().equals(name)) {
                    newOrder.addProduct(store.getProducts().get(i));
                }
            }
        }
        store.placeOrder(newOrder);
    }

    // Tüm siparişleri ve ürünlerini yazdır
    public static void printAllOrders(OnlineStore store) {
        // TODO: Bu metodu doldur
        for(Order ordr : store.getOrders()) {
            System.out.println(ordr.toString());
        }
    }

    // Belirli bir müşteri tüm siparişlerinde ne kadar harcamış onu yazdır
    public static void printCustomerTotalSpending(OnlineStore store, int customerId) {
        // TODO: Bu metodu doldur
        Customer ourCustomer = null;
        for(Customer next : store.getCustomers()) {
            if(next.getId()== customerId) {
                ourCustomer= next;
            }
        }
        if(ourCustomer==null) {
            System.out.println("There is no customer with given id!");
            return;
        }
        double total = 0;
        for(Order ordr : store.getOrders()) {
            if(ordr.getCustomer()== ourCustomer) {
                total += ordr.getTotalPrice();
            }
        }
        System.out.println("Customer "+ ourCustomer.getName()+" spent "+total+" dollars");
    }
}