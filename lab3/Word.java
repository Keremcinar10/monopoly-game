package lab3;

import java.util.ArrayList;
import java.util.Random;

public class Word {
    private String name;
    private ArrayList<Word> possibleChains;

    public Word(String aName)
    {
        this.name=aName;
        possibleChains= new ArrayList<Word>();
    }

    public boolean canChain(Word otherWord)
    {
        if(!this.getPossibleChains().contains(otherWord) && !otherWord.getPossibleChains().contains(this))
        {
            if(this.getName().length()==otherWord.getName().length())
            {
                int count=0;
                for(int i=0;i<this.getName().length();i++)
                {
                    if(this.getName().charAt(i)== otherWord.getName().charAt(i))
                    {
                        count++;
                    }
                }
                if(count>=this.getName().length()-1)
                {
                    return true;
                }
            }
            else if(Math.abs(this.getName().length()-otherWord.getName().length())==1)
            {
                Word longer;
                Word shorter;
               if(this.getName().length()-otherWord.getName().length()>0)
               {
                longer=this;
                shorter=otherWord;
               }
               else
               {
                longer=otherWord;
                shorter=this;
               }
               for(int k=0;k<longer.getName().length();k++)
               {
                    if(k==0 && longer.getName().substring(k+1).equals(shorter.getName())){return true;}
                    else if(k==longer.getName().length()-1 && longer.getName().substring(0, k).equals(shorter.getName())){return true;}
                    else if((longer.getName().substring(0, k)+longer.getName().substring(k+1)).equals(shorter.getName())){return true;}
               }
            }
            return false;
        }
        return false;
    }

    public Word getChainWord(ArrayList<Word> currentChain)
    {
        Random random= new Random();
        ArrayList<Word>temp= new ArrayList<Word>();
        for(int i=0;i<this.possibleChains.size();i++)
        {
            if(!currentChain.contains(this.possibleChains.get(i)))
            {
                temp.add(this.possibleChains.get(i));
            }
        }
        if(temp.size()>0)
        {
            int randomWordQueue= random.nextInt(temp.size());
            Word nextWord=temp.get(randomWordQueue); 
            return nextWord;
        }
        return null;
       
    }

    public void addToPossibleChains(Word chainWord) 
    {
        this.getPossibleChains().add(chainWord);
    }

    public ArrayList<Word> getPossibleChains()
    {
        return this.possibleChains;
    }
    public String getName()
    {
        return this.name;
    }


    

}
