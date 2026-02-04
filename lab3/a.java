package lab3;

public class a {
    
}


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


for(int i=0;i<2;i++)
        {
            int anotherRandom=random.nextInt(selectedWord.getPossibleChains().size());
            Word randomChainWord= selectedWord.getPossibleChains().get(anotherRandom);
            toBeDisplayed.add(randomChainWord);
        }


        for(int i=0;i<2;i++)
        {
            Word otherWord= selectedWord.getChainWord(toBeDisplayed);
            if(otherWord==null)
            {
                i--;
                continue;
            }
            toBeDisplayed.add(otherWord);
            selectedWord=otherWord;
        }
