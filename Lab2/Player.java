package Lab2;

import java.util.ArrayList;


public class Player {
    
    private String name;
    private int position;
    private boolean canPlay;
    private boolean isEliminated;
    private ArrayList<Property>properties;
    private char symbol;
    private int coins;

    public Player(String aName)
    {
        this.name=aName;
        this.position=0;
        this.canPlay=true;
        this.isEliminated= false;
        properties= new ArrayList<Property>();
        symbol=this.name.charAt(0);
        this.coins=10;
    }

    // getters

    public String getName() 
    { 
        return name;
    }
    public int getPosition()
    {
        return this.position;
    }
    public int getCoins()
    {
        return this.coins;
    }
    public char getSymbol()
    {
        return this.symbol;
    }

    public boolean CanPlayerPlay()
    {
        return this.canPlay;
    }

    public boolean IsPlayerEliminated()
    {
        return this.isEliminated;
    }
    
    public ArrayList<Property> getProperties()
    {
        return this.properties;
    }

    //setters

    public void setPosition(int aPosition)
    {
        this.position= aPosition;
    }

    public void setCanPlayStatus(boolean canPlay)
    {
        this.canPlay=canPlay;
    }

    public void setEliminatedStatus(boolean isEliminated)
    {
        this.isEliminated= isEliminated;
    }

    //other methods

    public void addCoins(int amount)
    {
        this.coins+=amount;
    }
    public boolean removeCoins(int amount)
    {
        if(this.coins>=amount)
        {
            this.coins-=amount;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void earnProperty(Property aProperty)
    {
        properties.add(aProperty);
        aProperty.setOwner(this);
    }

    public void removeProperty(Property aProperty)
    {
        properties.remove(aProperty);
        aProperty.backToDefault();
    }   

}
