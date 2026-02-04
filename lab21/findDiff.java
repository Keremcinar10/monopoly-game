package lab21;

import java.util.Scanner;

public class findDiff {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int prev;
        int num=0;
        int currentDiff=0;
        int maxDiff=0;

        System.out.println("This algorithm finds the largest difference between 2 consecutive positive numbers. And terminates if you enter 0! ");
        int count =0;
        do
        {
            if(sc.hasNextInt())
            {
                if(count==0)
                {
                    num=sc.nextInt();
                    prev=num;
                }
                else
                {
                    prev=num;
                    num =sc.nextInt();
                }

                if(num>0)
                {
                    currentDiff=Math.abs(num-prev);
                    if(currentDiff>maxDiff)
                    {
                        maxDiff=currentDiff;
                    }
                }
                else if(num<0)
                {
                    System.out.println("Please enter a positive number!");
                }
            }
            else
            {
                System.out.println("Please enter an integer!");
            }
            count ++;

        } while(num!=0);

        System.out.println("The biggest difference between consecutive numbers is "+maxDiff);
        sc.close();
    }
}
