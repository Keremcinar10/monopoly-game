package lab07;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab07_Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        String [] items = {"Laptop", "Phone", "Headset", "Mouse", "Keyboard"};
        double [] prices = {1200, 800, 100, 50, 70};

        ArrayList<String> cart = new ArrayList<String>();
        int choice;
        do
        {
            System.out.println();
            displayMenu();
            choice= sc.nextInt(); sc.nextLine();
           
            switch(choice)
            {
                case 1:
                System.out.print("Enter the item to add: ");
                String itemToAdd= sc.nextLine();
                boolean found= false;
                for(String anItem:items)
                {
                    if( anItem.equals(itemToAdd)) 
                    {
                        found = true;
                    }
                }
                if(found)
                {
                    cart.add(itemToAdd);
                    System.out.println(itemToAdd+" added to the cart.");
                }
                else 
                {
                    System.out.println("There is no such item in the list!");
                }
                break;

                case 2:
                System.out.print("Enter the item to remove: ");
                String itemToRemove= sc.nextLine();
                if(cart.contains(itemToRemove))
                {
                    System.out.println(itemToRemove+" removed from the cart.");
                    cart.remove(itemToRemove);
                }
                else 
                {
                    System.out.println("Error: "+itemToRemove+" is not in the cart.");
                }
                break;

                case 3:
                for(int i=0;i<items.length;i++)
                {
                    int numberOfitem=0;
                    for(String cartItem: cart)
                    {
                        if(items[i].equals(cartItem))
                        {
                            numberOfitem++;
                        }
                    }
                    if(numberOfitem>0)
                    {
                        System.out.println("-"+items[i]+": "+numberOfitem);
                    }
                }
                System.out.println("Total Cost: $"+calculateTotalCost(items, cart, prices));
                break;

                case 4:
                if(cart.isEmpty()) System.out.println("Cart is empty");
                else 
                {
                    int maxCount=0;
                    ArrayList<String> maxItems= new ArrayList<String>();

                    for(int i=0;i<items.length;i++)
                    {
                        int numberOfitem=0;
                        for(String cartItem: cart)
                        {
                            if(items[i].equals(cartItem))
                            {
                                numberOfitem++;
                            }
                        }
                        if(numberOfitem>maxCount)
                        {
                            maxItems.clear();
                            maxItems.add(items[i]);
                            maxCount= numberOfitem;
                        }
                        else if(numberOfitem==maxCount)
                        {
                            maxItems.add(items[i]); 
                        }
                    }
                    System.out.println("Most frequently added item(s):");
                    for(int a=0;a<maxItems.size();a++)
                    {
                        System.out.println("-"+maxItems.get(a));
                    }

                }
                break;

                case 5:
                double total = calculateTotalCost(items, cart, prices);
                double newTotal = total*(9.0/10.0);
                System.out.println("Total Cost before discount: $"+ total);
                System.out.println("Total Cost after 10% discount: $"+ newTotal);
                break;

                case 6:
                System.out.println("Exiting the program. Goodbye!");
                break;
            }
        } while(choice!=6);
    }
    public static void displayMenu()
    {
        System.out.println("Welcome to the Online Shopping Cart System!");
        System.out.println("1. Add an item to the cart\n2. Remove an item from the cart\n3. View the cart\n4. Find the most frequently added item\n5. Apply discount and calculate total cost\n6. Exit\r");
        System.out.print("Enter your choice: ");
    }
    public static double calculateTotalCost(String []items, ArrayList<String >cart,double[] prices)
    {
        double totalCost =0;
                for(int i=0;i<items.length;i++)
                {
                    int numberOfitem=0;
                    for(String cartItem: cart)
                    {
                        if(items[i].equals(cartItem))
                        {
                            numberOfitem++;
                        }
                    }
                    if(numberOfitem>0)
                    {
                        totalCost= totalCost +(numberOfitem*prices[i]);
                    }
                }
                return totalCost;
    }
}
