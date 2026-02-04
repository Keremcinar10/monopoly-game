package lab29;

public class Card {
    
    private int number;
    private String color;

    public Card (int number, String color)
    {
        this.number= number;
        this.color= color;
    }
    public int getNumber()
    {
        return this.number;
    }
    public String getColor()
    {
        return this.color;
    }

    public boolean equals (Object obj)
    {
        Card cardToCheck= (Card)obj;
        
        return this.number== cardToCheck.number || this.color.equals(cardToCheck.color);
    }
}
