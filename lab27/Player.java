package lab27;

import java.awt.Color;
import java.util.ArrayList;

public class Player 
{
    
    boolean isHuman;
    boolean skipTurn;
    String name;
    int coins;
    int position;
    Color color;
    boolean isEliminated;
    ArrayList<PropertyCell> properties;
    
    public Player(String name, Color color, boolean isHuman) {
        properties =new ArrayList<PropertyCell>();
        this.name=name;
        this.color=color;
        this.isHuman =isHuman;
        this.coins=10;
        this.position=0;
        this.skipTurn=false;
        this.isEliminated=false;
        
    }
}
