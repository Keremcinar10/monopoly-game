package lab04;

import java.util.Random;
import java.util.Scanner;

public class Lab04_Q1 {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner (System.in);

        // random number between 0-100
        int randomInt = random.nextInt(100);

        // getting the first input
        System.out.print("Guess the number: ");
        int guess = sc.nextInt();

        // loop until right guess
        while(guess != randomInt)
        {
            if(guess>randomInt)
            {
                if(guess-randomInt<10)
                {
                    System.out.println("close but high, try again…");
                }
                else
                {
                    System.out.println("too high, try again…");
                }
            }
            else 
            {
                if(randomInt-guess<10)
                {
                    System.out.println("close but low, try again…");
                }
                else
                {
                    System.out.println("too low, try again…");
                }
            }
            System.out.print("Guess the number: ");
            guess= sc.nextInt();
        }

        // after right guess
        System.out.println("Congratulations, you guessed it!");

    }

    
}
