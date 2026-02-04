package lab04;

import java.util.Scanner;

public class Lab04_Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int entered = sc.nextInt();

        while(entered >= 2)
        {   
            String output="Prime numbers less than or equal to "+entered+": ";
            int primeCount = 1;
            int numberToCheck = 2;
            
            while(numberToCheck <= entered)
            {
                int numberToCheck2=2;
                boolean isPrime = true;

                while(numberToCheck2 < numberToCheck)
                {
                    if(numberToCheck % numberToCheck2 == 0)
                    {
                        isPrime = false;
                    }
                    numberToCheck2++;
                }
                if(isPrime)
                {
                    boolean isTheCountPrime= true;
                    int numberToCheck3=2;
                    while(numberToCheck3<primeCount)
                    {
                        if(primeCount % numberToCheck3 == 0 )
                        {
                            isTheCountPrime = false;
                        }
                        numberToCheck3++;
                    }
                    if(isTheCountPrime && primeCount != 1)
                    {
                        output += "("+numberToCheck+"), ";
                    }

                    else 
                    {
                        output += numberToCheck+", ";
                    }
                    primeCount++;
                }
                numberToCheck++;
            }
            if(entered != 2)
            {
                System.out.println(output.substring(0,output.length()-2));
            }
            else
            {
                System.out.println("Prime numbers less than or equal to "+entered+": "+2);
            }
            System.out.println();
            System.out.print("Enter a number: ");
            entered = sc.nextInt();
        }
    }
}
