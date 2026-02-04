package Lab2;

public class Property {
    
    private String name;
    private int price;
    private int costOfHouse;
    private int[] rents;
    private Player owner;
    private int noOfHouses;

    public Property(String name, int price, int costOfHouse, int[] rents )
    {
        this.name= name;
        this.price= price;
        this.costOfHouse=costOfHouse;
        this.rents=rents;
        this.owner=null;
        this.noOfHouses=0;

    }
        // getters
    public String getName()
    {
        return this.name;
    }
    public int getPrice()
    {
        return this.price;
    }
    public int getCost()
    {
        return this.costOfHouse;
    }
    public int getRent()
    {
        return rents[noOfHouses];
    }

    public Player getOwner()
    {
        return this.owner;
    }
    public int getNoOfHouses()
    {
        return this.noOfHouses;
    }

    //setters

    public void setOwner(Player aPlayer)
    {
        this.owner= aPlayer;
    }

    public boolean incrementHouseCount()
    {
        if(noOfHouses<4)
        {
            noOfHouses++;
            return true;
        }
        return false;
    }

    public void backToDefault()
    {
        this.setOwner(null);
        this.noOfHouses=0;
    }






}
