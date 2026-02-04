package lab21;

import java.util.Scanner;

public class PlayGame {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to the HangMan game. Your secret word is a food! You can guess the word or letter wrong 6 times! Otherwise you loose!");
        Hangman hangman=new Hangman();
        String [] word= new String [hangman.getSecretWord().length()+1];
        cencorWord(word);
        displayManu(hangman, word);
        while (!hangman.isGameOver())
        {
            System.out.print(" Enter a letter: ");
            String response=sc.nextLine();
            handlePlayTurn(hangman, response, word);
            displayManu(hangman, word);
        }
        if(hangman.hasWon())
        {
            System.out.println("congradulations you won !");
        }
        else{
            System.out.println("Game over. You lost!");
        }
        sc.close();
    }
    public static void handlePlayTurn(Hangman aHangman, String aResponse, String [] aWord)
    {
        int count =0;
        for(int i=0;i<aHangman.getSecretWord().length();i++)
        {
            if(aHangman.getSecretWord().substring(i, i+1).equals(aResponse))
            {
                aWord[i]=aResponse;
                count ++;
                aHangman.incrementKnownSoFar();
            }
        }
        if(count ==0)
        {
            System.out.println("This word does not have letter "+aResponse);
            aHangman.incrementNumOfIncorrectTries();
        }

    }

    

    public static void displayManu(Hangman aHangman, String[] aWord)
    {
        int letterCount=1;
       
        for (int i=0;i<aHangman.getSecretWord().length();i++)
        {
            System.out.println(letterCount+": "+aWord[i]);
            letterCount++;
        }

        System.out.println("Remaining number of wrong tries: "+(aHangman.getMaxAllowedIncorrectTries()-aHangman.getNumOfIncorrectTries()));
    } 

    public static void cencorWord(String[] aWord)
    {
        for(int i=0;i<aWord.length;i++)
        {
            aWord[i]="*";
        }
    }
    
    
    

}
