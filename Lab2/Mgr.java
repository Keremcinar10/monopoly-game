package Lab2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mgr {
    Scanner sc= new Scanner (System.in);
    Random random = new Random ();
    private ArrayList<Player>players;
    private int userTurn=-1;
    private ArrayList<Property>properties;
    private final int MAX_TURNS=100;
    private final int SİZE_OF_BOARD=16;

    public Mgr()
    {
        players = new ArrayList<Player>();
        properties = new ArrayList<Property>();
    }

    private void initializePlayers()
    {
        ArrayList<Player>temp= new ArrayList<Player>();
        Scanner sc= new Scanner (System.in);
        Random random= new Random();
        System.out.print("Enter the number of players you want to play against: ");
        int noOfPlayers=sc.nextInt();

        for(int i=1;i<=noOfPlayers;i++)
        {
            System.out.print("Enter the name of player "+i+": ");
            String name= sc.next();

            temp.add(new Player(name));
        }
        System.out.print("Enter your name: ");
        String aName= sc.next();
        Player user= new Player(aName);
        temp.add(user);

        for(int j=0;j<=noOfPlayers;j++)
        {
            int playerToAdd=random.nextInt(temp.size());
            players.add(temp.get(playerToAdd));
            if(temp.get(playerToAdd).getName().equals(user.getName()))
            {
                userTurn=j;
            }
            temp.remove(playerToAdd);
        }

    }
    private void initializeProperties()
    {
        String propertyNames[]= {"A","B","C","D","E","F","G","H","I","J","K","L"};
       for(int i=0;i<3;i++)
       {
        properties.add(new Property(propertyNames[i],2 , 1,new int[]{1,2,3,4,6}));
       }
       for(int j=3;j<6;j++)
       {
        properties.add(new Property(propertyNames[j],4 , 1,new int[]{2,2,3,3,7}));
       }
       for(int a=6;a<9;a++)
       {
        properties.add(new Property(propertyNames[a],6 , 2,new int[]{1,3,4,6,7}));
       }
       for(int b=9;b<12;b++)
       {
        properties.add(new Property(propertyNames[b],8 , 3,new int[]{3,3,6,6,9}));
       }
    }
    private void handleEvent(Player aPlayer, int event)
    {
        Random random = new Random ();
        if(event==0)
        {
            if(aPlayer.getPosition()%SİZE_OF_BOARD==0)
            {
                aPlayer.addCoins(6);
                System.out.println("player "+aPlayer.getName()+" got 6 coins for standing ending up on start point!");
            }
            else
            {
                aPlayer.addCoins(3);
                System.out.println("player "+aPlayer.getName()+" got 3 coins for finishing a tour!");
            }
        }
        else if(event==1)
        {
            int rollResult= random.nextInt(6)+1;
            System.out.println( aPlayer.getName()+" rolled "+rollResult+" for special event!");
                switch(rollResult)
                {
                    case 1: 
                    aPlayer.removeCoins(2);
                    System.out.println(aPlayer.getName()+" has lost 2 coins!");
                    break;
                    case 2:
                    aPlayer.removeCoins(1);
                    System.out.println(aPlayer.getName()+" has lost 1 coins!");
                    break;
                    case 3:
                    aPlayer.setPosition((aPlayer.getPosition()+1));
                    System.out.println(aPlayer.getName()+" moved one place further!");
                    handleNormalRound(aPlayer,findTheProperty("D"));
                    break;
                    case 4:
                    aPlayer.setPosition((aPlayer.getPosition()+2));
                    System.out.println(aPlayer.getName()+" moved two places further!");
                    handleNormalRound(aPlayer, findTheProperty("E"));
                    break;
                    case 5 :
                    aPlayer.addCoins(1);
                    aPlayer.setPosition((aPlayer.getPosition()+1));
                    System.out.println(aPlayer.getName()+" gained one coin and moved one place further!");
                    handleNormalRound(aPlayer, findTheProperty("D"));
                    break;
                    case 6:
                    aPlayer.addCoins(2);
                    aPlayer.setPosition((aPlayer.getPosition()+2));
                    System.out.println(aPlayer.getName()+" gained two coins and moved two places further!");
                    handleNormalRound(aPlayer, findTheProperty("E"));
                    break;

                }
        }

        else if(event==2)
        {
            int count=0;
            for(Player a:players)
            {
                if(a!=aPlayer && !a.IsPlayerEliminated())
                {
                    if(a.removeCoins(1))
                    {
                        count++;
                        aPlayer.addCoins(1);
                    }
                }
            }
            System.out.println("Player "+aPlayer.getName()+" collected "+count+" points from other players!");
        }
        else if(event==3)
        {
            aPlayer.setCanPlayStatus(false);
            System.out.println(aPlayer.getName()+" will skip the next turn!");
        }
    }
    private void handleNormalRound(Player aPlayer, Property aProperty)
    {
            
            if(aProperty.getOwner()== null)
            {
                if(aPlayer.getCoins()>=aProperty.getPrice())
                {
                    boolean buy=false;
                    if(aPlayer!=players.get(userTurn))
                    {
                        System.out.println("Do you want to buy property "+aProperty.getName()+"(yes or no): ");
                        buy= random.nextBoolean();
                        String result= buy? "yes":"no";
                        System.out.println("Player chose "+ result);
                        
                    }
                    else
                    {
                        System.out.print("Do you want to buy property: "+aProperty.getName()+"(yes or no): ");
                        System.out.print("The price of the property is " +aProperty.getPrice()+": ");
                        String answer= sc.next();
                        if(answer.substring(0,1).toLowerCase().equals("y"))
                        {
                            buy= true;
                        }
                        else
                        {
                            buy=false; 
                        }
                    }
                
                    if(buy)
                    {
                        aPlayer.removeCoins(aProperty.getPrice());
                        aPlayer.earnProperty(aProperty);
                        System.out.println("Player "+aPlayer.getName()+" bought the property "+aProperty.getName());
                    }
                    else
                    {
                        System.out.println("Player "+aPlayer.getName()+" did not buy anything! ");
                    }
                }
                else
                {
                    System.out.println("Player does not have enough money to buy the property");
                }
                
            }
            else if(aProperty.getOwner()!=aPlayer)
            {
                if(!aPlayer.removeCoins(aProperty.getRent()))
                {
                    aPlayer.setEliminatedStatus(true);
                    aProperty.getOwner().addCoins(aProperty.getRent());
                    System.out.println("Player "+aPlayer.getName()+" lost the game! ");
                }
                else
                {
                    aProperty.getOwner().addCoins(aProperty.getRent());
                    System.out.println(aPlayer.getName()+" paid "+aProperty.getRent()+" coins to "+aProperty.getOwner().getName());
                }
            }
            else if(aProperty.getOwner()==aPlayer)
            {
                System.out.println("Player "+aPlayer.getName()+" already owns this property!");
            }
        
    }
    private void HandleEndRoundActions(Player aPlayer)
    {
        Random random= new Random();
        Scanner sc= new Scanner (System.in);
        System.out.println("Choose the number of action you want to do:");
        int chosen;
        if(aPlayer== players.get(userTurn))
        {
            System.out.print("1. Build a house:\n2. Sell a property:\n3. Skip: ");
             chosen=sc.nextInt();
        }
        else
        {
            System.out.print("1. Build a house:\n2. Sell a property:\n3. Skip: ");
            chosen= random.nextInt(3)+1;
            System.out.println("Player "+aPlayer.getName()+" picked the option "+chosen);
        }

        switch(chosen)
        {
            case 1:
            if(aPlayer==players.get(userTurn))
            {
            handleUserBuildHouse(aPlayer);
            }
            else
            {
            handleComputerBuildHouse(aPlayer);
            }
            break;

            case 2:
            if(aPlayer==players.get(userTurn))
                {
                    handleUserSellProperty(aPlayer);
                }
            else
            {
                if(aPlayer.getCoins()<=4)
                {
                    handleComputerSellProperty(aPlayer);
                }
                else
                {
                    System.out.println("player "+aPlayer.getName()+" did not sell any property!");
                }
                    
            }
            break;
            case 3 :
            System.out.println("Player "+aPlayer.getName()+ " did not sell a property and also did not built a house!");
            break;
        }
    }
    private void handleUserSellProperty(Player aPlayer)
    {

        //assuming no invalid input other than integers!

        Scanner sc= new Scanner (System.in);
        if(aPlayer.getProperties().size()>0)
        {
           for(int i=0;i<aPlayer.getProperties().size();i++)
           {
            Property a= aPlayer.getProperties().get(i);
            System.out.println(i+1+": "+a.getName()+" Houses: "+a.getNoOfHouses()+" Current value: "+a.getPrice());
           }
        while(true)
        {
        System.out.print("Enter the number of the property you want to sell: ");
        int choice = sc.nextInt();
            if(choice>0 && choice<aPlayer.getProperties().size())
            {
                Property picked = aPlayer.getProperties().get(choice-1);
                aPlayer.removeProperty(picked);  // also sets property to default 
                aPlayer.addCoins(picked.getPrice());
                System.out.println("Property "+picked.getName()+" is returned back to bank and now has no owner!");
                System.out.println("Player "+aPlayer.getName()+" has received "+picked.getPrice()+" coins!");
                break;
            } 
            else
            {
                System.out.println("Enter valid number!");
            }
        }
        }
    }
    private void handleComputerSellProperty(Player aPlayer)
    {
        //selling the most expensive property 

        if(aPlayer.getProperties().size()>0)
        {
            Property mostExpensive= aPlayer.getProperties().get(0);
            for(Property member:aPlayer.getProperties())
            {
                if(member.getPrice()>mostExpensive.getPrice())
                {
                    mostExpensive=member;
                }
            }

            aPlayer.removeProperty(mostExpensive);
            aPlayer.addCoins(mostExpensive.getPrice());
            System.out.println(aPlayer.getName()+" has sold the property "+mostExpensive.getName()+" and has received "+mostExpensive.getPrice()+" coins!");
            System.out.println("now the property "+mostExpensive.getName()+" has no owner!");
        }

        
    }
    private void handleUserBuildHouse(Player aPlayer)
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("Properties available for building house!");

        int count=0;
        ArrayList<Property> available= new ArrayList<Property>();
        for(int i=0;i<aPlayer.getProperties().size();i++)
        {
            Property tryOne= aPlayer.getProperties().get(i);
            if(tryOne.getNoOfHouses()<4 && aPlayer.getCoins()>=tryOne.getCost())
            {
                available.add(tryOne);
                System.out.println(count+1+": "+tryOne.getName()+" Cost: "+tryOne.getCost()+" Current House count: "+tryOne.getNoOfHouses());
                count++;
            }
        }
            // assuming no invalid input other than integers!
        if(count>0)
        {
            System.out.print("Enter the number of property to build house:");
            
            while (true)
            {
                int choice = sc.nextInt();
                if(choice>0 && choice<=count)
                {
                    Property tryOne = available.get(choice-1);
                    aPlayer.removeCoins(tryOne.getCost());
                    tryOne.incrementHouseCount();
                    System.out.println(aPlayer.getName()+" builded a house on property "+tryOne.getName()+" for "+tryOne.getCost()+" coins!");
                    break;
                }
                else
                {
                    System.out.println("Enter a valid number!");
                }
            }
        }
        else 
        {
            System.out.println("There is no available properties to build house on!");
        }
    }
    private void handleComputerBuildHouse(Player aPlayer)
    {
        Random random= new Random();
        int count =0;
        ArrayList<Property> available= new ArrayList<Property>();
        for(int i=0;i<aPlayer.getProperties().size();i++)
        {
            if(aPlayer.getProperties().get(i).getNoOfHouses()<4 && aPlayer.getCoins()>=aPlayer.getProperties().get(i).getCost())
            {
                available.add(aPlayer.getProperties().get(i));
                count++;
            }
        }
        boolean bool= random.nextBoolean();
        if(count>0 && bool)
        {
            int propertyToBuild= random.nextInt(count);
            Property picked= available.get(propertyToBuild);
            aPlayer.removeCoins(picked.getCost());
            picked.incrementHouseCount();
            System.out.println(aPlayer.getName()+" builded a house on property"+picked.getName()+" for "+picked.getCost()+" coins!");
        }
    }
    private void displayBoard()
    {
        String[][] gameBoard= new String[10][5];

        for (int i=0;i<10;i++) 
        {
            for (int j=0;j<5;j++) 
            {
                gameBoard[i][j] = "|....|";
            }
        }

        String[][]cellNames = 
        {
            {"0", "A", "B", "C", "1"},     
            {"L", "", "", "", "D"},         
            {"K", "", "", "", "E"},
            {"J", "", "", "", "F"},
            {"3", "I", "H", "G", "2"}      
        };

        for(int row=0;row<5;row++)
        {
            for(int col=0;col<5;col++)
            {
                if(!cellNames[row][col].isEmpty())
                {
                    String completeCell=FillTheCell(cellNames[row][col]);
                    gameBoard[2*row][col]=completeCell;
                }
            }
        }
        int possiblePlayerLocations[][]= {{1,0},{1,1},{1,2},{1,3},{1,4},{3,4},{5,4},{7,4},{9,4},{9,3},{9,2},{9,1},{9,0},{7,0},{5,0},{3,0}};
        for(Player aPlayer:this.players)
        {   
            if(!aPlayer.IsPlayerEliminated())
            {
            int slotInArray= aPlayer.getPosition()%SİZE_OF_BOARD;
            String currentCell=gameBoard[possiblePlayerLocations[slotInArray][0]][possiblePlayerLocations[slotInArray][1]];
            int dotPlace= currentCell.indexOf(".");
            if(dotPlace!=-1)
                {
                    String newCell = currentCell.substring(0, dotPlace) + aPlayer.getSymbol() + currentCell.substring(dotPlace + 1);
                    gameBoard[possiblePlayerLocations[slotInArray][0]][possiblePlayerLocations[slotInArray][1]]= newCell;
                }
            }
        }



        for (int i=0;i<10;i++) 
        {
            for (int j=0;j<5;j++) 
            {   if(i!=1 && j!=0 && i!=9 && j!=4 && gameBoard[i][j].equals("|....|"))
                {
                    System.out.print("      ");
                }
                else System.out.print(gameBoard[i][j]);
            }
            System.out.println("|");
        }

    }
    private Property getPropertyPlayerStandsOn(int position)
    {
        String cells[]= {"0","A","B","C","1","D","E","F","2","G","H","I","3","J","K","L"};
        int slotInArray=position%SİZE_OF_BOARD;
        Property result=findTheProperty(cells[slotInArray]);
        return result;

    }
    private String FillTheCell(String cellName)
        {
            if(cellName.equals("A")||cellName.equals("B")|| cellName.equals("C")|| cellName.equals("D")|| 
            cellName.equals("E")|| cellName.equals("F")|| cellName.equals("G")|| cellName.equals("H")|| 
            cellName.equals("I")|| cellName.equals("J")||cellName.equals("K") || cellName.equals("L") )
            {
                Property prp= findTheProperty(cellName);
                char ownerSymbol;
                //case1 
                if(prp==null )
                { 
                    return "|"+cellName+"...|";
                }
                if(prp.getOwner()!=null)
                {
                     ownerSymbol= prp.getOwner().getSymbol();
                }
                else
                {
                    ownerSymbol='.';
                }
                String a= prp.getOwner()==null? ".":String.valueOf(prp.getNoOfHouses());
                return "|"+cellName+"."+ownerSymbol+ a+"|";
            }
            else 
            {
                return "|" + cellName + "...|";
            }
        }
    private Property findTheProperty(String aName)
    {
        for(Property wanted:properties)
        {
            if(wanted.getName().equals(aName))
            {
                return wanted;
            }
        }
        return null;
    }
    private void playTheGame()
    {
        Scanner sc= new Scanner (System.in);
        Random random = new Random();
        initializeProperties();
        initializePlayers();
        int count=0;

        while(count<MAX_TURNS )
        {
            count++;
            System.out.println("*************************************************************************************************************");
            System.out.println("Round "+count +"\n");
            
            

            for(Player aPlayer :players)
            {
                displayBoard();
                if(aPlayer.IsPlayerEliminated())
                {
                    continue;
                }
                if(!aPlayer.CanPlayerPlay())
                {
                    aPlayer.setCanPlayStatus(true);
                    continue;
                }
                int roll= random.nextInt(6)+1;
                System.out.println("It is "+aPlayer.getName()+"'s turn.");
                System.out.println(aPlayer.getName()+" has rolled "+roll);
                int oldPosition=aPlayer.getPosition();
                int newPosition= (aPlayer.getPosition()+roll);
                aPlayer.setPosition(newPosition);

                if(newPosition%SİZE_OF_BOARD==0 || didFinishTour(oldPosition, newPosition))
                {
                    handleEvent(aPlayer, 0);
                }
                else if(newPosition%SİZE_OF_BOARD==4)
                {
                    handleEvent(aPlayer, 1);
                }
                else if(newPosition%SİZE_OF_BOARD==8)
                {
                    handleEvent(aPlayer, 2);
                }
                else if(newPosition%SİZE_OF_BOARD==12)
                {
                    handleEvent(aPlayer, 3);
                }
                
                //
                if(newPosition%SİZE_OF_BOARD!=0 && newPosition%SİZE_OF_BOARD!=4 && newPosition%SİZE_OF_BOARD!=8 && newPosition%SİZE_OF_BOARD!=12)
                {
                    Property prp= getPropertyPlayerStandsOn(newPosition);
                    handleNormalRound(aPlayer, prp);
                }
               

                if(!aPlayer.IsPlayerEliminated())
                {
                    HandleEndRoundActions(aPlayer);
                }

                if(aPlayer.IsPlayerEliminated())
                {
                    if(aPlayer.getProperties().size()>0)
                    {
                        for(Property a: aPlayer.getProperties())
                        {
                            a.backToDefault();
                        }
                    }
                    
                }

                if(isEveryoneEliminated())
                {
                    displayWinner();
                    return;
                }
                
                displayCoins();
                System.out.println();
                System.out.println("*************************************************************************************************************");
                System.out.println();
            }
            
        }
            System.out.println("the game has been played for the maximum number of rounds("+MAX_TURNS+")! ");
            displayWinner();
    }
    private boolean isEveryoneEliminated()
    {
        int count=0;
        for(Player aPlayer:players)
        {
            if(aPlayer.IsPlayerEliminated()==false)
            {
                count++;
            }
        }
        return count<2;
    }
    private void displayWinner()
    {
        ArrayList<Player>remainigPlayers= new ArrayList<Player>();
        for(Player a:players)
        {
            if(!a.IsPlayerEliminated())
            {
                remainigPlayers.add(a);
            }
        }

        if(remainigPlayers.size()==1)
        {
            System.out.print("the winner is "+remainigPlayers.get(0).getName());
        }
        else 
        {
            System.out.print("The game is tied. The winners are ");
            for(int i=0;i<remainigPlayers.size();i++)
            {
                System.out.println(remainigPlayers.get(i).getName()+" ");
            }
        }
    }
    private void displayCoins()
    {
        for(Player a:players)
        {
            if(!a.IsPlayerEliminated())
            {
            System.out.println("Player "+a.getName()+" has "+a.getCoins()+" coins remainig!");
            }
        }
    }
    private boolean didFinishTour(int oldPos, int newPos)
    {
        for(int i=1;i<38;i++)
        {
            if(oldPos<16*i && newPos>16*i)
            {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Mgr game = new Mgr();
        game.playTheGame();
    }
}
