package lab29;

import java.util.ArrayList;

public class Player {
    
    private String name;
    private ArrayList<Card> cards;
    private boolean isComputer;

    public Player (String aName, boolean isComputer)
    {
        this.name= aName;
        this.cards= new ArrayList<Card>();
        this. isComputer= isComputer;
    }
    public String getName()
    {
        return this.name;
    }

    public ArrayList<Card> getCards()
    {
        return this.cards;
    }
    public boolean isComputer()
    {
        return this.isComputer;
    }


    public void drawCard(Card card)
    {
        this.cards.add(card);
    }
    public void removeCard(Card card)
    {
        this.cards.remove(card);
    }

}
