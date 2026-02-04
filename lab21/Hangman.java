package lab21;

import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    
    
    private int maxAllowedIncorrectTries;
    private int currentIncorrectTries;
    private int knownSoFar;
    private String secretWord;
    ArrayList<String>fixList;



public Hangman()
{
   
    this.maxAllowedIncorrectTries=6;
    this.currentIncorrectTries=0;
    this.knownSoFar=0;
    this.fixList= new ArrayList<String>();
    this.secretWord= chooseSecretWord();

}

public String chooseSecretWord()
{
    Random random = new Random();
    this.fixList.add("chocolate");
    this.fixList.add("banana");
    this.fixList.add("apple");
    this.fixList.add("orange");
    this.fixList.add("hamburger");
    this.fixList.add("pizza");
    this.fixList.add("lahmacun");
    this.fixList.add("olive");
    this.fixList.add("pasta");
    this.fixList.add("peanut");

    String wordToUse= this.fixList.get(random.nextInt(this.fixList.size()-1));
    return wordToUse;
}


public String getSecretWord()
{
    return this.secretWord;
}

public int getNumOfIncorrectTries()
{
    return this.currentIncorrectTries;
}

public int getMaxAllowedIncorrectTries()
{
    return this.maxAllowedIncorrectTries;
}

public int getKnownSoFar()
{
    return this.knownSoFar;
}


public void incrementNumOfIncorrectTries()
{
    this.currentIncorrectTries++;
}
public void incrementKnownSoFar()
{
    this.knownSoFar++;
}




public boolean isGameOver()
{
    return (hasLost() || hasWon()); 
}

public boolean hasLost()
{
    return this.currentIncorrectTries==this.maxAllowedIncorrectTries;
}

public boolean hasWon()
{
    return knownSoFar==secretWord.length();
}




}
