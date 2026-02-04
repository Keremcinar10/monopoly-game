package lab04;

import java.util.Scanner;

public class Lab04_Q3 {
    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);

        System.out.print("Enter a positive integer of at least 5 digits: ");
        int entered = sc.nextInt();
        while(entered > 0)
        {
            while(entered < 10000)
            {
                System.out.println("The number you entered is invalid, please try again… \n");
                System.out.print("Enter a positive integer of at least 5 digits: ");
                entered = sc.nextInt();
            }
            int result = 0;
            int count = 0;
            while(entered > 0)
            {
                int digit = entered % 10;
                if(digit % 2 ==0)
                {
                    result = result + (int)(Math.pow(10, count) * digit);
                    count++;
                }
                entered = entered / 10;
            }
            System.out.println("Here is the number without the odd digits: "+result);
            System.out.println();
            System.out.print("Enter a positive integer of at least 5 digits: ");
            entered = sc.nextInt();
        }
        System.out.println("Program finished! ");
    }

}
