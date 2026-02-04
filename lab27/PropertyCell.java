package lab27;

class PropertyCell extends Cell {
    
    
    int houses;
    String letter;
    int price;
    int houseCost;
    Player owner;
    int[] rents;
    
    public PropertyCell(String name, String letter, int price, int houseCost, int[] rents) {
        super(name);
        this.letter=letter;
        this.price=price;
        this.houseCost=houseCost;
        this.rents=rents;
        this.houses=0;
        this.owner=null;
    }
    public int getRent()
     {
        return rents[houses];
    }
}
