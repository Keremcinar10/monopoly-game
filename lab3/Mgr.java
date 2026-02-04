package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mgr {
    
    private ArrayList<Word>allWords;

    public Mgr()
    {
        this.allWords= new ArrayList<Word>();
    }
    public ArrayList<Word> getAllWords()
    {

        return this.allWords;
    }
    public void readFromText() throws FileNotFoundException
    {
        String filename = "words.txt"; 
        Scanner sc = new Scanner(new File("lab3/"+filename)); 
        while(sc.hasNextLine()) 
        { 
            String line = sc.nextLine(); 
            line =line.toUpperCase();
            Word newWord = new Word(line);
            allWords.add(newWord);
        }   
    }
    public Word findTheWord(String a)
    {
        for(Word wanted:allWords)
        {
            if(wanted.getName().equals(a))
            {
                return wanted;
            }
        }
        return null;
    }

    public void FormPossibleChainsForAllWords()
    {
        for(int i=0;i<allWords.size();i++)
        {
            Word a= allWords.get(i);
            for(int j=i+1;j<allWords.size();j++)
            {
                Word b= allWords.get(j);
                if(Math.abs(a.getName().length()-b.getName().length())<2 && a.canChain(b))
                {
                    a.addToPossibleChains(b);
                    b.addToPossibleChains(a);
                }
            }

        }
    }

    public void removeUnchainedWords()
    {
        for(int i=0;i<allWords.size();i++)
        {
            if(allWords.get(i).getPossibleChains().size()<1)
            {
                allWords.remove(allWords.get(i));
            }
        }
    }

    public void generateChainsLengthTen()
    {
        Scanner sc= new Scanner(System.in);
        Random random= new Random();
        ArrayList<Word>formedChain= new ArrayList<>();
        String enteredWord;
        Word selectedWord;
        System.out.print("Enter a word to be generated similar to: ");
        while(true)
        {
             enteredWord=sc.next();
             selectedWord=findTheWord(enteredWord);
            if(selectedWord==null)
            {
                System.out.println("Please enter a valid word: ");
            }
            else{break;}
        }
        formedChain.add(selectedWord);

        for(int i=0;i<9;i++)
        {
            Word otherWord= selectedWord.getChainWord(formedChain);
            if(otherWord==null)
            {
                break;
            }
            formedChain.add(otherWord);
            selectedWord=otherWord;
        }
        String formedString="";
        for(int h=0;h<formedChain.size();h++)
        {
           formedString=formedString + formedChain.get(h).getName()+"-";
        }
        System.out.println(formedString.substring(0,formedString.length()-1));
    }

    public void guessingGame(ArrayList<Word>toBeDisplayed)
    {
        System.out.println("Welcome to guessing game:");
        Random random= new Random();
        Scanner sc= new Scanner(System.in);
        int randomWord; 
        Word selectedWord;
        while(true)
        {
            randomWord= random.nextInt(allWords.size());
            selectedWord=allWords.get(randomWord);
            if(selectedWord.getPossibleChains().size()>=3){break;}
        }
        toBeDisplayed.add(selectedWord);
        while(true)
        {
            Word otherWord= selectedWord.getChainWord(toBeDisplayed);
            Word anotherWord= otherWord.getChainWord(toBeDisplayed);
            if(otherWord!=null && anotherWord!=null)
            {
                toBeDisplayed.add(otherWord);
                toBeDisplayed.add(anotherWord);
                break;
            }
        }
        String question=toBeDisplayed.get(0).getName() +"- ? -"+toBeDisplayed.get(2).getName();
        System.out.println("Try to guess the word: ");
        System.out.println(question);
        for(int k=0;k<3;k++)
        {
           String answer= sc.next();
           if(answer.equals(toBeDisplayed.get(1).getName()))
           {
            System.out.println("You guessed correctly! ");
            return;
           } 
           else
           {
            System.out.print("Try again: ");
           }
        }
        System.out.println("true answer was "+toBeDisplayed.get(1).getName()+". You lost!");
       
    }

    public void displayMenu() throws FileNotFoundException
    { Scanner sc= new Scanner(System.in);
        readFromText();
        FormPossibleChainsForAllWords();
        removeUnchainedWords();
        boolean keepLooping=true;
        do
        {
        System.out.println("1- Play the guessing game:\n2- Generate chains with the user typed words:");
        int answer= sc.nextInt();
        
            switch(answer)
            {
                case 1:
                ArrayList<Word>toBeDisplayed= new ArrayList<Word>();
                guessingGame(toBeDisplayed);
                System.out.println("Do you want to return to menu: ");
                String answer2 = sc.next();
                if(answer2.charAt(0)=='n')
                {
                    keepLooping=false;
                }
                break;
                case 2:
                generateChainsLengthTen();
                System.out.println("Do you want to return to menu: ");
                String answer3 = sc.next();
                if(answer3.charAt(0)=='n')
                {
                    keepLooping=false;
                }
                break;
            }
        }while(keepLooping);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Mgr a= new Mgr();
        a.displayMenu();
    }
}
