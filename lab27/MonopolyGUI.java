package lab27;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MonopolyGUI {
    public static void main(String[] args) {
         new WelcomePage();
        }
}
class WelcomePage extends JFrame
{
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JTextField player3TextField;
    private JTextField player4TextField;

    public WelcomePage()
    {
        this.setSize(new Dimension(500,500));
        this.setTitle("Monopoly Game");
        setLayout(new GridLayout(5, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLabel player1Label= new JLabel("Blue Player:");
        player1Label.setForeground(Color.BLUE);
        this.add(player1Label);
        player1TextField= new JTextField("Player 1");
        this.add(player1TextField);

        JLabel player2Label= new JLabel("Red Player;");
        player2Label.setForeground(Color.RED);
        this.add(player2Label);
        player2TextField= new JTextField("Player 2");
        this.add(player2TextField);

        JLabel player3Label= new JLabel("Green Player;");
        player3Label.setForeground(Color.GREEN);
        this.add(player3Label);
        player3TextField= new JTextField("Player 3");
        this.add(player3TextField);

        JLabel player4Label= new JLabel("Yellow Player;");
        player4Label.setForeground(Color.YELLOW);
        this.add(player4Label);
        player4TextField= new JTextField("Player 4");
        this.add(player4TextField);

        JButton startButon = new JButton("Start the Game!");
        
        startButon.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                ArrayList<String> playerNames = new ArrayList<String>();
                playerNames.add(player1TextField.getText());
                playerNames.add(player2TextField.getText());
                playerNames.add(player3TextField.getText());
                playerNames.add(player4TextField.getText());
                    
                new MainScreen(playerNames);
                dispose();
            }
        });
        this.add(startButon);
       
        this.add(new JLabel(""));
        setVisible(true);
    }
    
}

class MainScreen extends JFrame{
    
    private JButton rollButton;
    private JButton buyButton;
    private Game game;
    private JButton buildButton;
    private BoardPanel boardPanel;
    private JButton sellButton;
    
    private JButton endTurnButton;
    private JTextArea gameLogArea;

    public MainScreen(ArrayList<String>playerNames)
    {
        this.game = new Game(playerNames, this);
        
        this.setSize(new Dimension(1200,500));
        this.setTitle("Monopoly Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,3));


        boardPanel=new BoardPanel(game);
        boardPanel.setSize(new Dimension(400, 400));
        this.add(boardPanel);

        gameLogArea = new JTextArea();
        gameLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLogArea);
        this.add(scrollPane);

        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        
        sellButton=new JButton("Sell");
        buildButton=new JButton("Build");
        endTurnButton=new JButton("End Turn");
        rollButton= new JButton("Roll");
        buyButton=new JButton("Buy");
        
        buttonPanel.add(rollButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(buildButton);
        buttonPanel.add(endTurnButton);
        this.add(buttonPanel);

        rollButton.setEnabled(false);
        buyButton.setEnabled(false);
        sellButton.setEnabled(false);
        buildButton.setEnabled(false);
        endTurnButton.setEnabled(false);

        rollButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) 
            {
                game.Roll();
            }
        });
        buyButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) 
            {
                game.Buy();
            }
        });
        sellButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) 
            {
                game.Sell();
            }
        });
        buildButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) 
            {
                game.Build();
            }
        });
        endTurnButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent eevent) 
            {
                game.endTurn();
            }
        });
        
        
        setVisible(true);

        game.playGame();
    }
    
    
    public void appendLog(String text) 
    {
        gameLogArea.append(text + "\n");
    }
    public void updateBoard() {
        boardPanel.repaint();
    }

    
    public void EnableBuy(boolean enabled) 
    { 
        buyButton.setEnabled(enabled); 
    }
    public void EnableEndTurn(boolean enabled) 
    { 
        endTurnButton.setEnabled(enabled); 
    }
    public void EnableSell(boolean enabled) 
    { 
        sellButton.setEnabled(enabled); 
    }
    public void EnableBuild(boolean enabled) 
    { 
        buildButton.setEnabled(enabled); 
    }
    public void EnableRoll(boolean enabled) 
    { 
        rollButton.setEnabled(enabled); 
    }
    

}
class SellingFrame extends JFrame 
{
    public SellingFrame(MainScreen aFrame, Player aPlayer, Game game) {
        this.setTitle("Sell House");
        this.setSize(400,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(aPlayer.properties.size()+2,1));
    
        JPanel buttonPanel = new JPanel(new GridLayout(aPlayer.properties.size()+1,1));
        JLabel label = new JLabel("You can sell the following properties!");
        this.add(label);
        
        for (PropertyCell prp : aPlayer.properties) {
            JButton nButton = new JButton("Sell "+prp.name+" for "+prp.price);
            nButton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent evvent) {
                    prp.owner = null;
                    prp.houses = 0;
                    aPlayer.coins += prp.price;
                    aPlayer.properties.remove(prp);
                    aFrame.appendLog(aPlayer.name+" sold property "+prp.name+" for "+prp.price +" coins!");
                    JOptionPane.showMessageDialog(aFrame, "Sold "+prp.name+" for "+prp.price);
                    aFrame.updateBoard();
                    dispose(); 
                }
            });
            buttonPanel.add(nButton);
        }
        
        JButton returnButton = new JButton("return");
        returnButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
        
        this.add(buttonPanel);
        this.add(returnButton);
        setVisible(true);
    }
}

class BuildingFrame extends JFrame
 {
    public BuildingFrame(MainScreen aFrame, Player aPlayer, Game game, ArrayList<PropertyCell> buildableProperties) 
    {
        this.setTitle("Build House");
        this.setSize(new Dimension(400,500));
        this.setLayout(new GridLayout(buildableProperties.size()+2,1));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        JPanel buttonPanel = new JPanel(new GridLayout(buildableProperties.size()+1,1));
        JLabel label = new JLabel("You can build on the following properties: ");
        this.add(label);
        
        for (PropertyCell prp :buildableProperties) 
        {
            JButton nButton = new JButton("Build on "+prp.name+" price:"+prp.houseCost);
            nButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evvent) {

                    aPlayer.coins -= prp.houseCost;
                    prp.houses++;
                    aFrame.appendLog(aPlayer.name + " built a house on property "+prp.name+" for "+prp.houseCost+" coins!");
                    JOptionPane.showMessageDialog(aFrame, "You built on "+prp.name);
                    aFrame.updateBoard();
                    dispose();
                }
            });
            buttonPanel.add(nButton);
        }
        
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
        
        add(buttonPanel);
        add(returnButton);
        setVisible(true);
    }
}

class BoardPanel extends JPanel{

    private Game game;

    public BoardPanel(Game game) {
        this.game = game;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int width =getWidth();
        int heigth =getHeight();
        int cellWidth=width/5;
        int cellHeigth=heigth/5;

        Rectangle[] cells= new Rectangle[16];

        for (int i=0;i<5;i++)
        {
            cells[i]=new Rectangle(i*cellWidth,0,cellWidth,cellHeigth);
        }
        for (int i = 0; i < 5; i++) {
            cells[8+i]=new Rectangle((4-i)*cellWidth,4*cellHeigth,cellWidth,cellHeigth);
        }
        for (int i = 0; i < 3; i++) {
            cells[5+i] =new Rectangle(4*cellWidth,(i +1)*cellHeigth,cellWidth,cellHeigth);
        }
        for (int i = 0; i < 3; i++) {
            cells[13+i] =new Rectangle(0,(3 - i)*cellHeigth,cellWidth,cellHeigth);
        }

        for(int i=0;i<16;i++)
        {
            Rectangle rect = cells[i];
            if(i==0 || i==4|| i==8 || i==12)
            {
                g.setColor(Color.GRAY);
                g.fillRect(rect.x, rect.y, rect.width, rect.height);
                g.setColor(Color.black);
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
            }
            else 
            {
                g.setColor(Color.WHITE);
                g.fillRect(rect.x, rect.y,rect.width, rect.height);
                g.setColor(Color.black);
                g.drawRect(rect.x, rect.y,rect.width, rect.height);
            }
            
            if(game.board[i] instanceof PropertyCell)
            {
                String ownerString;
                PropertyCell a=(PropertyCell)game.board[i];
                if(a.owner!=null)
                {
                    g.setColor(a.owner.color);
                    g.fillRect(rect.x, rect.y, rect.width, rect.height);
                }
                if(a.owner== null)
                {
                    ownerString="***";
                }
                else
                {
                    ownerString= a.owner.name.substring(0, 1);
                }
                g.setColor(Color.BLACK);
                g.drawString("Owner: " +ownerString, rect.x +5,rect.y +40);
                g.drawString("Houses: " +a.houses, rect.x + 5,rect.y +60);
            }

            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString(game.board[i].name,rect.x + 5,rect.y +20);
            g.setFont(new Font("Arial", Font.PLAIN, 12));

            int playerCount=0;
            for (Player ply : game.players) {
                if (ply.position==i && !ply.isEliminated) {
                    g.setColor(ply.color);
                    g.fillOval(rect.x + 1 +(playerCount*20),rect.y + rect.height-20, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawOval(rect.x + 1 + (playerCount*20),rect.y +rect.height -20,20,20);
                    playerCount++;
                }
            }
        }
        g.setColor(game.players.get(0).color);
        g.drawString(game.players.get(0).name+": "+game.players.get(0).coins+" coins",150 ,150);
        g.setColor(game.players.get(1).color);
        g.drawString(game.players.get(1).name+": "+game.players.get(1).coins+" coins", 150 ,200);
        g.setColor(game.players.get(2).color);
        g.drawString(game.players.get(2).name+": "+game.players.get(2).coins+" coins",150 ,250);
        g.setColor(game.players.get(3).color);
        g.drawString(game.players.get(3).name+": "+game.players.get(3).coins+" coins",150 ,300);

    }
}
class Game {
    ArrayList<Player> players;
    int currentPlayerIndex;
    int turnCount;
    MainScreen frame;
    
    Cell[] board; 

    public Game(ArrayList<String> playerNames, MainScreen frame)
    {
        Random random = new Random();
        players= new ArrayList<Player>();
        this.frame= frame;
        this.turnCount=0;
        

        Color[] colors = {Color.blue,Color.red,Color.green,Color.yellow};

        for (int i = 0; i < 4; i++) 
        {
            if(i==0)
            {
                players.add(new Player(playerNames.get(i),colors[i],true));
            }
           else
            {
                players.add(new Player(playerNames.get(i),colors[i],false));
            }
        }
        Collections.shuffle(players,random);
        this.currentPlayerIndex=0;

        board = new Cell[16];

        board[0] = new EventCell("0", 0);
        board[4] = new EventCell("1", 1);
        board[8] = new EventCell("2", 2);
        board[12] = new EventCell("3", 3);

        board[1] = new PropertyCell("A", "A", 2, 1, new int[]{1,2,3,4,6});
        board[2] = new PropertyCell("B", "B", 2, 1, new int[]{1,2,3,4,6});
        board[3] = new PropertyCell("C", "C", 2, 1, new int[]{1,2,3,4,6});
        board[5] = new PropertyCell("D", "D", 4, 1, new int[]{2,2,3,3,7});
        board[6] = new PropertyCell("E", "E", 4, 1, new int[]{2,2,3,3,7});
        board[7] = new PropertyCell("F", "F", 4, 1, new int[]{2,2,3,3,7});
        board[9] = new PropertyCell("G", "G", 6, 2, new int[]{1,3,4,6,7});
        board[10] = new PropertyCell("H", "H", 6, 2, new int[]{1,3,4,6,7});
        board[11] = new PropertyCell("I", "I", 6, 2, new int[]{1,3,4,6,7});
        board[13] = new PropertyCell("J", "J", 8, 3, new int[]{3,3,6,6,9});
        board[14] = new PropertyCell("K", "K", 8, 3, new int[]{3,3,6,6,9});
        board[15] = new PropertyCell("L", "L", 8, 3, new int[]{3,3,6,6,9});
    } 

    public void playGame()
{
    if(turnCount==0) frame.appendLog("Game started.");
    
    if (isEveryoneEliminated()|| turnCount >= 100) {
        EndTheGame();
        return;
    }
    frame.appendLog("\n");
    currentPlayerIndex=(currentPlayerIndex +1) %players.size();
    Player aPLayer=players.get(currentPlayerIndex);
    if (aPLayer.isEliminated) 
    {
        playGame();
        return;
    }

    if (aPLayer.skipTurn) 
    {
        frame.appendLog(aPLayer.name +" skips turn.");
        aPLayer.skipTurn = false;
        playGame();
        return;
    }

    turnCount++;
    frame.appendLog("Turn " + turnCount + ": " + aPLayer.name + "'s turn.");

    if(aPLayer.isHuman)
    {
        frame.EnableRoll(true);
        frame.EnableBuy(false);
        frame.EnableSell(false);
        frame.EnableBuild(false);
        frame.EnableEndTurn(false);
    }
    else {
        
        computerTurn(aPLayer);
        playGame();
    }
}
    private boolean isEveryoneEliminated()
    {
        int count=0;
        for(Player aPlayer:players)
        {
            if(aPlayer.isEliminated==false)
            {
                count++;
            }
        }
        return count<2;
    }

    public void Roll()
    {
        Random random = new Random();
        Player playerToPlay= this.players.get(currentPlayerIndex);
        int rolledDice= random.nextInt(6)+1;

        frame.appendLog(playerToPlay.name+" has rolled "+rolledDice+"!");

        int oldPosition= playerToPlay.position;
        int newPosition= (playerToPlay.position+rolledDice)%16;

        if(oldPosition+newPosition>16)
        {
            if(newPosition==0)
            {
                playerToPlay.coins+=6;
                frame.appendLog("Player " +playerToPlay.name+" landed on the start point and received 6 coins!");
            }
            else if(newPosition>0)
            {
                playerToPlay.coins+=3;
                frame.appendLog("Player " +playerToPlay.name+" passed the starting point and received 3 coins!");
            }
        }
        playerToPlay.position= newPosition;
        frame.appendLog("Player " +playerToPlay.name+" moved to cell "+ this.board[playerToPlay.position].name);

        frame.EnableRoll(false);

        handleEvent(playerToPlay);

        frame.EnableEndTurn(true);

        Cell cll= board[playerToPlay.position];
        if(cll instanceof PropertyCell)
        {
            PropertyCell a= (PropertyCell)cll;
            if(playerToPlay.coins>=a.price && a.owner==null)
            {
                frame.EnableBuy(true);
            }
        }

        if(!playerToPlay.properties.isEmpty())
        {
            frame.EnableBuild(true);
            frame.EnableSell(true);
        }
        frame.updateBoard();
    }

    public void endTurn()
    {
        frame.EnableBuy(false);
        frame.EnableSell(false);
        frame.EnableBuild(false);
        frame.EnableEndTurn(false);
        playGame();
    }

    public void Buy()
    {
        
        Player playerToPlay = players.get(currentPlayerIndex);
        Cell cll= board[playerToPlay.position];

        if( cll instanceof PropertyCell)
        {
            PropertyCell b= (PropertyCell)cll;

            if(b.owner==null && playerToPlay.coins>=b.price)
            {
                b.owner=playerToPlay;
                playerToPlay.coins-= b.price;
                playerToPlay.properties.add(b);
                frame.appendLog("Player "+ playerToPlay.name+" has bought property "+b.name+" for "+b.price+" coins!");
            }
            frame.EnableBuild(false);
            frame.updateBoard();
        }
    }

    public void Build()
    {
        ArrayList<PropertyCell> buildableProperties= new ArrayList<PropertyCell>();
        Player playerToPlay = players.get(currentPlayerIndex);

        for(PropertyCell cl: playerToPlay.properties)
        {
            if(cl.houses<4 && playerToPlay.coins>=cl.houseCost)
            {
                buildableProperties.add(cl);
            }
        }
        
        if(!buildableProperties.isEmpty())
        {
           new BuildingFrame(frame, playerToPlay, null, buildableProperties);
        }
        else 
        {
            frame.appendLog("Player "+playerToPlay+" has no properties available to build house!");
        }

    }

   
        public void Sell() {
            Player aPlayer = players.get(currentPlayerIndex);
            if (aPlayer.properties.isEmpty()) {
                frame.appendLog("No properties to sell.");
            } else {
                
                new SellingFrame(frame, aPlayer, this);
            }
        }
    

    

    public void computerTurn(Player aPlayer )
    {
        Random random = new Random();
        Player playerToPlay= players.get(currentPlayerIndex);
        int rolledDice = random.nextInt(6)+1;
        frame.appendLog("Player "+aPlayer.name+" has rolled "+rolledDice);
        int oldPosition= playerToPlay.position;
        int newPosition= (playerToPlay.position+rolledDice)%16;

        if(oldPosition+newPosition>16)
        {
            if(newPosition==0)
            {
                playerToPlay.coins+=6;
                frame.appendLog("Player " +playerToPlay.name+" landed on the start point and received 6 coins!");
            }
            else if(newPosition>0)
            {
                playerToPlay.coins+=3;
                frame.appendLog("Player " +playerToPlay.name+" passed the starting point and received 3 coins!");
            }
        }
        playerToPlay.position= newPosition;
        frame.appendLog("Player " +playerToPlay.name+" moved to cell "+ this.board[playerToPlay.position].name);
        handleEvent(aPlayer);

        Cell cll= board[aPlayer.position];
        if(cll instanceof PropertyCell)
        {
            PropertyCell a= (PropertyCell) cll;
            if(a.owner==null && playerToPlay.coins>=a.price)
            {
                boolean letsBuy= random.nextBoolean();
                if(letsBuy)
                {
                    a.owner= aPlayer;
                    aPlayer.properties.add(a);
                    playerToPlay.coins-=a.price;
                    frame.appendLog("Player "+aPlayer.name+" has bought the property "+a.name);
                }
            }
        }
        for(PropertyCell prpcll: aPlayer.properties)
        {
            if(prpcll.houses<4 && aPlayer.coins>=prpcll.houseCost)
            {
                boolean letsBuild= random.nextBoolean();
                if(letsBuild)
                {
                    aPlayer.coins-= prpcll.houseCost;
                    prpcll.houses++;
                    frame.appendLog(aPlayer.name + " built a house on property " + prpcll.name + "!");
                    break;
                }
            }
        }

        if(aPlayer.coins<4 && !aPlayer.properties.isEmpty())
        {
            PropertyCell PrpToSell = aPlayer.properties.get(0);
            for(PropertyCell a: aPlayer.properties)
            {
                if(a!=PrpToSell && a.price>PrpToSell.price)
                {
                    PrpToSell=a;
                }
            }
            PrpToSell.owner=null;
            aPlayer.coins-=PrpToSell.price;
            aPlayer.properties.remove(PrpToSell);
            frame.appendLog(playerToPlay.name + " sold property " + PrpToSell.name);
        }
        frame.updateBoard();
    }

    public void EndTheGame()
    {
        String winnerPlayer="";
        for (Player aPlayer: players)
        {
            if(!aPlayer.isEliminated)
            {
                winnerPlayer=aPlayer.name;
            }
        }
        if(winnerPlayer.equals(""))
        {
            
            JOptionPane.showMessageDialog(frame,"Game ended in a tie");
        }
        else 
        {
            
            JOptionPane.showMessageDialog(frame,"Player "+winnerPlayer+" won");
        }
    }

    public void handleEvent(Player aPlayer)
    {
        Random random = new Random();
        Cell currentCell= board[aPlayer.position];

        if(currentCell instanceof PropertyCell)
        {
            PropertyCell prpcll= (PropertyCell)currentCell;

            if(prpcll.owner!=null && prpcll.owner!=aPlayer)
            {
                if(aPlayer.coins>=prpcll.getRent())
                {
                    aPlayer.coins-=prpcll.getRent();
                    prpcll.owner.coins+=prpcll.getRent();
                    frame.appendLog(aPlayer.name +" pays "+prpcll.getRent()+" coins rent to"+prpcll.owner.name+" for landing on "+prpcll.name+".");
                }
                else 
                {
                    aPlayer.isEliminated= true;
                    JOptionPane.showMessageDialog(frame, "Player "+ aPlayer.name+" is eliminated!");
                }
            }
        }
        else if (currentCell instanceof EventCell)
        {
            EventCell spcCll= (EventCell)currentCell;

            switch(spcCll.eventType)
            {
                case 0 :
                System.out.print("");
                break;

                case 1:
                frame.appendLog("Player "+aPlayer.name+" is rolling for special event!");
                int rollDice= random.nextInt(6)+1;
                frame.appendLog("Player "+aPlayer.name+" rolled "+rollDice+" for special event!");
                    switch(rollDice)
                    {
                        case 1:
                        aPlayer.coins-=2;
                        frame.appendLog(aPlayer.name + " lost 2 coins.");
                        break;
                        case 2:
                        aPlayer.coins-=1;
                        frame.appendLog(aPlayer.name + " lost 1 coin.");
                        break;
                        case 3:
                        frame.appendLog("Player "+aPlayer.name+ " moves 1 space forward!");
                        int oldPosition= aPlayer.position;
                        int newPosition= (aPlayer.position+1)%16;

                        if(oldPosition+newPosition>16)
                        {
                            if(newPosition==0)
                            {
                                aPlayer.coins+=6;
                                frame.appendLog("Player " +aPlayer.name+" landed on the start point and received 6 coins!");
                            }
                            else if(newPosition>0)
                            {
                                aPlayer.coins+=3;
                                frame.appendLog("Player " +aPlayer.name+" passed the starting point and received 3 coins!");
                            }
                        }
                        aPlayer.position= newPosition;
                        frame.appendLog("Player " +aPlayer.name+" moved to cell "+ this.board[aPlayer.position].name);
                        handleEvent(aPlayer);
                        break;
                        case 4:
                        frame.appendLog(aPlayer.name + " moves 2 spaces forward.");
                        int oldPosition2= aPlayer.position;
                        int newPosition2= (aPlayer.position+2)%16;

                        if(oldPosition2+newPosition2>16)
                        {
                            if(newPosition2==0)
                            {
                                aPlayer.coins+=6;
                                frame.appendLog("Player " +aPlayer.name+" landed on the start point and received 6 coins!");
                            }
                            else if(newPosition2>0)
                            {
                                aPlayer.coins+=3;
                                frame.appendLog("Player " +aPlayer.name+" passed the starting point and received 3 coins!");
                            }
                        }
                        aPlayer.position= newPosition2;
                        frame.appendLog("Player " +aPlayer.name+" moved to cell "+ this.board[aPlayer.position].name);
                        handleEvent(aPlayer);
                        break;
                        case 5 :
                        aPlayer.coins+=1;
                        frame.appendLog(aPlayer.name + " earned 1 coin.");
                        frame.appendLog("Player "+aPlayer.name+ " moves 1 space forward!");
                        int oldPosition3= aPlayer.position;
                        int newPosition3= (aPlayer.position+1)%16;

                        if(oldPosition3+newPosition3>16)
                        {
                            if(newPosition3==0)
                            {
                                aPlayer.coins+=6;
                                frame.appendLog("Player " +aPlayer.name+" landed on the start point and received 6 coins!");
                            }
                            else if(newPosition3>0)
                            {
                                aPlayer.coins+=3;
                                frame.appendLog("Player " +aPlayer.name+" passed the starting point and received 3 coins!");
                            }
                        }
                        aPlayer.position= newPosition3;
                        frame.appendLog("Player " +aPlayer.name+" moved to cell "+ this.board[aPlayer.position].name);
                    handleEvent(aPlayer);
                        break;
                        case 6:
                        aPlayer.coins+=2;
                        frame.appendLog(aPlayer.name + " earned 2 coins.");
                        frame.appendLog("Player "+aPlayer.name+ " moves 2 space forward!");
                        int oldPosition4= aPlayer.position;
                        int newPosition4= (aPlayer.position+2)%16;

                        if(oldPosition4+newPosition4>16)
                        {
                            if(newPosition4==0)
                            {
                                aPlayer.coins+=6;
                                frame.appendLog("Player " +aPlayer.name+" landed on the start point and received 6 coins!");
                            }
                            else if(newPosition4>0)
                            {
                                aPlayer.coins+=3;
                                frame.appendLog("Player " +aPlayer.name+" passed the starting point and received 3 coins!");
                            }
                        }
                        aPlayer.position= newPosition4;
                        frame.appendLog("Player " +aPlayer.name+" moved to cell "+ this.board[aPlayer.position].name);
                        handleEvent(aPlayer);
                        break;

                    }

                break;

                case 2:
                for(Player ply: players)
                {
                    if(ply != aPlayer && !ply.isEliminated)
                    {
                        if(ply.coins>=1)
                        {
                            ply.coins-=1;
                            aPlayer.coins+=1;
                        }
                        else
                        {
                            ply.isEliminated=true;
                        }
                    }
                }
                frame.appendLog(aPlayer.name +" receives 1 coin from each player.");
                break;

                case 3:
                aPlayer.skipTurn=true;
                frame.appendLog("Player "+aPlayer.name+" will skip next");
                break;
            }
        }
    }

}





