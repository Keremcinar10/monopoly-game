package lab29;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class UNO {
    

}
class MainPage extends JFrame
{
    private BoardPanel board;
    private JTextArea gameLogArea;
    private JPanel buttonPanel;
    private Game game;
    private ArrayList<JButton> buttons;
   

    public MainPage()
    {
        this.game = new Game();

        this.buttons= new ArrayList<JButton>();

        this.setSize(new Dimension(1200,500));
        this.setTitle("Monopoly Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        board= new BoardPanel(game);
        board.setSize(new Dimension(400, 400));
        this.add(board);

        gameLogArea = new JTextArea();
        gameLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLogArea);
        this.add(scrollPane);

        this.buttonPanel= new JPanel();

        this.setVisible(true);
        game.playGame();
    }
    public void appendLog(String text) 
    {
        gameLogArea.append(text + "\n");
    }
    public void updateBoard() 
    {
        board.repaint();
    }
    public void createButtonPanel(int noOfCards)
    {
      
    }
    public void createButtons()
    {
        
    }
    public void enableButtons(int noOfCards)
    {
        for(int i=0;i<buttons.size();i++)
        {

        }
    }

   

      
}


class BoardPanel extends JPanel
{
    private Game game;
   

    public BoardPanel(Game game) 
    {
        this.game= game;
    }

    public void paintComponent(Graphics g)
    {

    }
}
class Game 
{
    ArrayList <Player> players;
    ArrayList <Card> deck;
    private Card midCard;

    public void initializeCards()
    {
        for(int i=1;i<=9;i++)
        {
            deck.add(new Card(i, "blue"));
        }
        for(int i=1;i<=9;i++)
        {
            deck.add(new Card(i, "red"));
        }
        for(int i=1;i<=9;i++)
        {
            deck.add(new Card(i, "green"));
        }
        for(int i=1;i<=9;i++)
        {
            deck.add(new Card(i, "yellow"));
        }
        for(int i=1;i<=4;i++)
        {
            deck.add(new Card(0, "black"));
        }
        Collections.shuffle(deck);
    }
    public void initializePlayers()
    {
        players.add(new Player("You", true));
        for(int i=1;i<=3;i++)
        {
            players.add(new Player("Player"+i, false));
        }
        Collections.shuffle(players);
    }
    public void initializeHands()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<7;j++)
            {
                players.get(i).getCards().add(deck.get(0));
                deck.remove(0);
            }
        }
    }
    public void putMidCard()
    {
        this.midCard= deck.get(0);
        deck.remove(0);
    }
    public void playCard()
    {

    }



}

