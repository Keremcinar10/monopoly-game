package cs101MidtermPractice;

import java.util.Scanner;

public class longestAcending {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        int longestCount=0;
        int currentCount =0;
        int longestTotal=0;
        int currentTotal=0;

        int num=0;
        int prev=0;

        System.out.print("Enter a set of positive integer values: ");

        while (sc.hasNextInt())
        {
            num= sc.nextInt();
            if(num>prev)
            {
                currentCount++;
                currentTotal+=num;
                if(currentCount>longestCount)
                {
                    longestCount=currentCount;
                    longestTotal=currentTotal;
                }
            }
            else 
            {
                currentCount=1;
                currentTotal=num;
            }
            prev= num;
        }
        System.out.print("Longest sequence of ascending values: "+longestCount);
        System.out.println();
        System.out.print("Sum of longest sequence: "+longestTotal);
    }
}
