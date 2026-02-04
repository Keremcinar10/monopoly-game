package lab05;

import java.util.Scanner;

public class Lab05_Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        boolean validInput;
        int maxNumber=0;
        String inputNo;
        do 
        {
            validInput= true;
            System.out.print("Please enter the chart data as string: ");
            inputNo= sc.nextLine();
            for(int i=0; i<inputNo.length();i++)
            {
                if(!Character.isDigit(inputNo.charAt(i)))
                {
                    validInput=false;
                }
            }
            if(!validInput)
            {
                System.err.println("Invalid input. Please only enter digits(0-9)");
            }

        }while(!validInput);

        for(int i=0; i<inputNo.length();i++)
        {
            if(Integer.parseInt(inputNo.substring(i, i+1))>maxNumber)
            {
                maxNumber= Integer.parseInt(inputNo.substring(i, i+1));
            }
        }
        for(int a=maxNumber; a>=0;a--)
        {
            System.out.print(a+"|" );
            for(int k=0;k<inputNo.length();k++)
            {
                if(a==0)
                {
                    System.out.print("__");
                }
                
                else if(Integer.parseInt(inputNo.substring(k,k+1))>=a)
                {
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }
    }
}
