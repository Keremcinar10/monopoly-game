package lab06;

import java.util.Scanner;

public class deneme {
    public static void main(String[] args) {
    Scanner sc= new Scanner (System.in);
    int currentSum=0;
    int longestSum=0;
    int currentCount=0;
    int longestCount=0;
    int prev=0;
    int num=0;

    System.out.print("Enter a set of positive integer values. Terminal with a non-integer value: ");
    while(sc.hasNextInt())
    {
        num= sc.nextInt();
        if(num>0)
        {
            if(num>prev)
            {
                currentCount++;
                currentSum+=num;
                if(currentCount>longestCount)
                {
                    longestCount=currentCount;
                    longestSum=currentSum;
                }
            }
            else 
            {
                currentCount=1;
                currentSum=num;
            }
            prev=num;
        }
        
    }
    System.out.println("The longest sum is "+longestSum);
    System.out.println("The total count is "+ longestCount);
    }

}

