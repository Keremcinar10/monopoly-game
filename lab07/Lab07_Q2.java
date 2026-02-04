package lab07;

import java.util.ArrayList;

public class Lab07_Q2 {
    public static void main(String[] args) {
        
        ArrayList<Integer> reservations = new ArrayList<>();
        // make reservations for some books
        reserveBook(3, reservations);
        reserveBook(7, reservations);
        reserveBook(3, reservations); // Already reserved
        // display the current status of reservations
        displayBooks(reservations);
        // return books
        returnBook(3, reservations);
        returnBook(5, reservations); // try with not reserved
        // display the current status
        displayBooks(reservations);
        // do a batch of reservations
        int[] batch = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        processBatchReservations(batch, reservations);
        // display the final status
        displayBooks(reservations);
    }

    public static void reserveBook(int bookId, ArrayList<Integer> reservations)
    {
        if(!reservations.contains(bookId))
        {
            System.out.println("Book "+bookId+" reserved succesfully!");
            reservations.add(bookId);
        }
        else 
        {
            System.out.println("Book "+bookId+" is already reserved!");
        }
    }

    public static void returnBook(int bookId, ArrayList<Integer> reservations)
    {
        if(reservations.contains(bookId))
        {
            System.out.println("Book "+bookId+" returned succesfully!");
            reservations.remove(Integer.valueOf(bookId));
        }
        else
        {
            System.out.println("Book "+bookId+" was not reserved.");    
        }
    } 
    public static void displayBooks(ArrayList<Integer> reservations)
    {
        for (int i=1;i<=10;i++)
        {
            System.out.print("Book "+i+": ");
            if(reservations.contains(i))
            {
                System.out.println("Reserved");
            }
            else 
            {
                System.out.println("Available");
            }
        }
    }
    public static void processBatchReservations(int[] batch, ArrayList<Integer>reservations)
    {
        for(int a: batch)
        {
            if(!reservations.contains(a))
            {
                System.out.println("Book "+a+" reserved succesfully.");
                reservations.add(a);
            }
            else
            {
                System.out.println("Book "+a+" is already reserved.");
            }
            }
        }
    }


