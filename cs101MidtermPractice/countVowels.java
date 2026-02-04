package cs101MidtermPractice;

import java.util.Scanner;

public class countVowels {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        double sentenceCount=0;
        double vowelCount=0;

        System.out.print("Enter sentence: ");
        String entered = sc.nextLine();
        while (!entered.equals("exit"))
        {
            for(int i=0;i<entered.length();i++)
            {
                if(entered.charAt(i)=='a' || entered.charAt(i)=='e'|| entered.charAt(i)=='i'|| entered.charAt(i)=='o'|| entered.charAt(i)=='u')
                {
                    vowelCount++;
                }
            }
            sentenceCount++;
            System.out.print("Enter sentence: ");
            entered = sc.nextLine();
        }
        System.out.print("Average vowels in sentences: "+ (vowelCount/sentenceCount));

    }
   



}
