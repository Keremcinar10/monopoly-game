package lab28;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Main class to start the game

    public class MonopolyGame {
        public static void main(String[] args) {
            // Always launch Swing GUIs on the event-dispatching thread.
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    new WelcomeScreen();
                }
            });
        }
    }


/* **************************
   * Welcome Screen Class *
   ************************** */
class WelcomeScreen extends JFrame {
    // Text fields to enter the four player names.
    private JTextField player1Field, player2Field, player3Field, player4Field;

    public WelcomeScreen() {
        setTitle("Welcome to Monopoly!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        
        // Player 1 (Human)
        add(new JLabel("Player 1 (You):"));
        player1Field = new JTextField("Red");
        add(player1Field);
        
        // Player 2 (Computer)
        add(new JLabel("Player 2 (Computer):"));
        player2Field = new JTextField("Blue");
        add(player2Field);
        
        // Player 3 (Computer)
        add(new JLabel("Player 3 (Computer):"));
        player3Field = new JTextField("Green");
        add(player3Field);
        
        // Player 4 (Computer)
        add(new JLabel("Player 4 (Computer):"));
        player4Field = new JTextField("Yellow");
        add(player4Field);
        
        // Start button to launch the game
        JButton startButton = new JButton("Start Game");
        add(startButton);
        // Filler label for GridLayout symmetry
        add(new JLabel(""));
        
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // Read player names and store in a list
                ArrayList<String> names = new ArrayList<String>();
                names.add(player1Field.getText().trim());
                names.add(player2Field.getText().trim());
                names.add(player3Field.getText().trim());
                names.add(player4Field.getText().trim());
                
                // Open the main game frame and close the welcome screen
                new MainGameFrame(names);
                dispose();
            }
        });
        
        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }
}

/* **************************
   * Main Game Frame Class *
   ************************** */
class MainGameFrame extends JFrame {
    private Game game;
    private BoardPanel boardPanel;
    private JTextArea logArea;
    private JButton rollButton, buyButton, sellButton, buildButton, endTurnButton;
    
    public MainGameFrame(ArrayList<String> playerNames) {
        setTitle("Monopoly Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,3));
        
        // Create game logic passing this frame (for log and updates)
        game = new Game(playerNames, this);
        
        // Board panel in the center (for drawing the board and players)
        boardPanel = new BoardPanel(game);
        boardPanel.setPreferredSize(new Dimension(500, 500));
        add(boardPanel);
        
        // Log area at the bottom (shows game messages)
        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        
        // Panel for action buttons (roll, buy, sell, build, end turn)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        rollButton = new JButton("Roll");
        buyButton = new JButton("Buy");
        sellButton = new JButton("Sell");
        buildButton = new JButton("Build");
        endTurnButton = new JButton("End Turn");
        
        buttonPanel.add(rollButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(buildButton);
        buttonPanel.add(endTurnButton);
        add(buttonPanel);
        
        // Initially disable all buttons; enable them when a human turn starts.
        rollButton.setEnabled(false);
        buyButton.setEnabled(false);
        sellButton.setEnabled(false);
        buildButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        
        // Action listeners for the buttons:
        rollButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.humanRoll();
            }
        });
        
        buyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.humanBuy();
            }
        });
        
        sellButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.humanSell();
            }
        });
        
        buildButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.humanBuild();
            }
        });
        
        endTurnButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.endHumanTurn();
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Start the game (sets up the first turn)
        game.startGame();
    }
    
    // Called by Game to refresh the board view.
    public void updateBoard() {
        boardPanel.repaint();
    }
    
    // Append text to the log area.
    public void appendLog(String text) {
        logArea.append(text + "\n");
    }
    
    // Clear the log area.
    public void clearLog() {
        logArea.setText("");
    }
    
    // Methods to enable or disable action buttons.
    public void setRollEnabled(boolean enabled) { rollButton.setEnabled(enabled); }
    public void setBuyEnabled(boolean enabled) { buyButton.setEnabled(enabled); }
    public void setSellEnabled(boolean enabled) { sellButton.setEnabled(enabled); }
    public void setBuildEnabled(boolean enabled) { buildButton.setEnabled(enabled); }
    public void setEndTurnEnabled(boolean enabled) { endTurnButton.setEnabled(enabled); }

   
}

/* **************************
   * Board Panel Class *
   ************************** */
// This panel draws the board, cells, property details, and player tokens.
class BoardPanel extends JPanel {
    private Game game;
    
    public BoardPanel(Game game) {
        this.game = game;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Determine cell sizes based on panel dimensions.
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / 5;
        int cellHeight = height / 5;
        
        // Create an array of rectangles representing the 16 cells.
        // The board is drawn as a perimeter:
        // - Top row: 5 cells (indices 0 to 4)
        // - Right column: next 3 cells (indices 5 to 7)
        // - Bottom row: 5 cells (indices 8 to 12) drawn right-to-left
        // - Left column: 3 cells (indices 13 to 15) drawn bottom-to-top
        Rectangle[] cellRects = new Rectangle[16];
        
        // Top row
        for (int i = 0; i < 5; i++) {
            cellRects[i] = new Rectangle(i * cellWidth, 0, cellWidth, cellHeight);
        }
        
        // Right column (excluding top and bottom corners)
        for (int i = 0; i < 3; i++) {
            cellRects[5 + i] = new Rectangle(4 * cellWidth, (i + 1) * cellHeight, cellWidth, cellHeight);
        }
        
        // Bottom row (5 cells, drawn right-to-left)
        for (int i = 0; i < 5; i++) {
            cellRects[8 + i] = new Rectangle((4 - i) * cellWidth, 4 * cellHeight, cellWidth, cellHeight);
        }
        
        // Left column (3 cells, drawn bottom-to-top)
        for (int i = 0; i < 3; i++) {
            cellRects[13 + i] = new Rectangle(0, (3 - i) * cellHeight, cellWidth, cellHeight);
        }
        
        // Draw each cell.
        for (int i = 0; i < 16; i++) {
            Rectangle r = cellRects[i];
            g.setColor(Color.white);
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.black);
            g.drawRect(r.x, r.y, r.width, r.height);
            
            // Draw cell name at top left.
            g.drawString(game.board[i].name, r.x + 5, r.y + 15);


            
            // If a property cell, show its owner and number of houses.
            if (game.board[i] instanceof PropertyCell) {
                PropertyCell pc = (PropertyCell) game.board[i];
                if(pc.owner != null)
                {
                    g.setColor(pc.owner.color);
                    g.fillRect(r.x, r.y, r.width, r.height);
                }
                String ownerStr = (pc.owner == null) ? "-" : pc.owner.name.substring(0, 1);
                g.setColor(Color.BLACK);
                g.drawString("Owner: " + ownerStr, r.x + 5, r.y + 30);
                g.drawString("Houses: " + pc.houses, r.x + 5, r.y + 45);
            }
            
            // Draw players as small colored circles in the cell.
            int playerCount = 0;
            for (Player p : game.players) {
                if (p.position == i && !p.isOut) {
                    g.setColor(p.color);
                    g.fillOval(r.x + 5 + (playerCount * 15), r.y + r.height - 20, 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawOval(r.x + 5 + (playerCount * 15), r.y + r.height - 20, 10, 10);
                    playerCount++;
                }
            }
        }
            g.setColor(game.players.get(0).color);
            g.drawString(game.players.get(0).name+" "+game.players.get(0).coins,150 ,150);
            g.setColor(game.players.get(1).color);
            g.drawString(game.players.get(1).name+" "+game.players.get(1).coins, 150 ,200);
            g.setColor(game.players.get(2).color);
            g.drawString(game.players.get(2).name+" "+game.players.get(2).coins,150 ,250);
            g.setColor(game.players.get(3).color);
            g.drawString(game.players.get(3).name+" "+game.players.get(3).coins,150 ,300);
    }
}

/* **************************
   * Game Logic Class *
   ************************** */
// This class holds the players, board, turn logic, and rules processing.
class Game {
    ArrayList<Player> players;
    Cell[] board; // 16 board cells
    int currentPlayerIndex;
    int turnCount;
    MainGameFrame frame;
    Random rand;
    
    public Game(ArrayList<String> playerNames, MainGameFrame frame) {
        this.frame = frame;
        rand = new Random();
        turnCount = 0;
        
        // Initialize players. Assume playerNames.get(0) is human.
        players = new ArrayList<Player>();
        Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow};
        for (int i = 0; i < playerNames.size(); i++) {
            boolean isHuman = (i == 0);
            players.add(new Player(playerNames.get(i), colors[i], isHuman));
        }
        // Randomize the turn order.
        Collections.shuffle(players, rand);
        currentPlayerIndex = 0;
        
        // Initialize board cells.
        // Order: 0 (Start, special), then A, B, C (properties), then 1 (special),
        // then D, E, F (properties), then 2 (special), then G, H, I (properties),
        // then 3 (special), then J, K, L (properties).
        board = new Cell[16];
        board[0] = new SpecialCell("0", 0);
        board[1] = new PropertyCell("A", "A", 2, 1, new int[]{1,2,3,4,6});
        board[2] = new PropertyCell("B", "B", 2, 1, new int[]{1,2,3,4,6});
        board[3] = new PropertyCell("C", "C", 2, 1, new int[]{1,2,3,4,6});
        board[4] = new SpecialCell("1", 1);
        board[5] = new PropertyCell("D", "D", 4, 1, new int[]{2,2,3,3,7});
        board[6] = new PropertyCell("E", "E", 4, 1, new int[]{2,2,3,3,7});
        board[7] = new PropertyCell("F", "F", 4, 1, new int[]{2,2,3,3,7});
        board[8] = new SpecialCell("2", 2);
        board[9] = new PropertyCell("G", "G", 6, 2, new int[]{1,3,4,6,7});
        board[10] = new PropertyCell("H", "H", 6, 2, new int[]{1,3,4,6,7});
        board[11] = new PropertyCell("I", "I", 6, 2, new int[]{1,3,4,6,7});
        board[12] = new SpecialCell("3", 3);
        board[13] = new PropertyCell("J", "J", 8, 3, new int[]{3,3,6,6,9});
        board[14] = new PropertyCell("K", "K", 8, 3, new int[]{3,3,6,6,9});
        board[15] = new PropertyCell("L", "L", 8, 3, new int[]{3,3,6,6,9});
    }
    
    // Starts the game by announcing the turn order and beginning the first turn.
    public void startGame() {
        frame.appendLog("Game started. Turn order:");
        for (Player p : players) {
            frame.appendLog(p.name);
        }
        nextTurn();
    }
    
    // Advances to the next active player's turn.
    public void nextTurn() {
        // Check for game over (only one player left or turn limit reached).
        int activePlayers = 0;
        for (Player p : players) {
            if (!p.isOut) activePlayers++;
        }
        if (activePlayers <= 1 || turnCount >= 100) {
            gameOver();
            return;
        }
        
        // Move to the next player in the list.
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        Player current = players.get(currentPlayerIndex);
        if (current.isOut) {
            nextTurn();
            return;
        }
        
        // If the player must skip this turn, do so.    
        if (current.skipTurn) {
            frame.appendLog(current.name + " skips turn.");
            current.skipTurn = false;
            nextTurn();
            return;
        }
        
        turnCount++;
        frame.appendLog("Turn " + turnCount + ": " + current.name + "'s turn.");
        
        if (current.isHuman) {
            // Enable only the roll button for the human player.
            frame.setRollEnabled(true);
            frame.setBuyEnabled(false);
            frame.setSellEnabled(false);
            frame.setBuildEnabled(false);
            frame.setEndTurnEnabled(false);
        } else {
            // Process computer turn automatically.
            computerTurn(current);
            nextTurn();
        }
    }
    
    /* **************************
       * Human Player Actions *
       ************************** */
    
    // Called when the human player clicks "Roll".
    public void humanRoll() {
        Player current = players.get(currentPlayerIndex);
        int die = rollDie();
        frame.appendLog(current.name + " rolled a " + die + ".");
        movePlayer(current, die);
        frame.setRollEnabled(false);
        // Process the effects of landing on the new cell.
        processCell(current);
        // Enable end-turn button; if on an unowned property that can be bought, enable buy.
        frame.setEndTurnEnabled(true);
        Cell cell = board[current.position];
        if (cell instanceof PropertyCell) {
            PropertyCell pc = (PropertyCell) cell;
            if (pc.owner == null && current.coins >= pc.price) {
                frame.setBuyEnabled(true);
            }
        }
        // If the player owns any property, allow build or sell.
        if (!current.properties.isEmpty()) {
            frame.setBuildEnabled(true);
            frame.setSellEnabled(true);
        }
        frame.updateBoard();
    }
    
    // Called when the human player clicks "Buy".
    public void humanBuy() {
        Player current = players.get(currentPlayerIndex);
        Cell cell = board[current.position];
        if (cell instanceof PropertyCell) {
            PropertyCell pc = (PropertyCell) cell;
            if (pc.owner == null && current.coins >= pc.price) {
                current.coins -= pc.price;
                pc.owner = current;
                current.properties.add(pc);
                frame.appendLog(current.name + " bought property " + pc.name + " for " + pc.price + " coins.");
            }
        }
        frame.setBuyEnabled(false);
        frame.updateBoard();
    }
    
    // Called when the human player clicks "Sell".
    // For simplicity, the first property owned is sold.
    public void humanSell() {
        Player current = players.get(currentPlayerIndex);
        if (!current.properties.isEmpty()) {
            PropertyCell pc = current.properties.get(0);
            current.coins += pc.price;
            pc.owner = null;
            pc.houses = 0;
            current.properties.remove(pc);
            frame.appendLog(current.name + " sold property " + pc.name + " for " + pc.price + " coins.");
        }
        frame.setSellEnabled(false);
        frame.updateBoard();
    }
    
    // Called when the human player clicks "Build".
    // For simplicity, build on the first eligible property.
    public void humanBuild() {
        Player current = players.get(currentPlayerIndex);
        // Filter properties eligible for building a house:
        // Must have fewer than 4 houses and enough coins to cover the house cost.
        ArrayList<PropertyCell> buildable = new ArrayList<>();
        for (PropertyCell pc : current.properties) {
            if (pc.houses < 4 && current.coins >= pc.houseCost) {
                buildable.add(pc);
            }
        }
        if (!buildable.isEmpty()) {
            String[] propertyNames = new String[buildable.size()];
            for (int i = 0; i < buildable.size(); i++) {
                propertyNames[i] = buildable.get(i).name;
            }
            // Show a dialog with a drop-down list of eligible properties.
            String chosenProperty = (String) JOptionPane.showInputDialog(
                    frame,
                    "Choose a property to build a house on",
                    "Build House",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    propertyNames,
                    propertyNames[0]
            );
            if (chosenProperty != null) {
                PropertyCell selected = null;
                for (PropertyCell pc : buildable) {
                    if (pc.name.equals(chosenProperty)) {
                        selected = pc;
                        break;
                    }
                }
                if (selected != null) {
                    current.coins -= selected.houseCost;
                    selected.houses++;
                    frame.appendLog(current.name + " built a house on property " + selected.name + " for " + selected.houseCost + " coins.");
                }
            }
        } else {
            frame.appendLog("No property available to build a house.");
        }
        frame.setBuildEnabled(false);
        frame.updateBoard();
    }
    
    // Called when the human player clicks "End Turn".
    public void endHumanTurn() {
        frame.appendLog("Ending " + players.get(currentPlayerIndex).name + "'s turn.");
        frame.setBuyEnabled(false);
        frame.setSellEnabled(false);
        frame.setBuildEnabled(false);
        frame.setEndTurnEnabled(false);
        nextTurn();
    }
    
    /* **************************
       * Computer Turn Simulation *
       ************************** */
    public void computerTurn(Player current) {
        frame.appendLog(current.name + "'s turn (Computer).");
        int die = rollDie();
        frame.appendLog(current.name + " rolled a " + die + ".");
        movePlayer(current, die);
        processCell(current);
        // Decide to buy the property (50% chance) if available.
        Cell cell = board[current.position];
        if (cell instanceof PropertyCell) {
            PropertyCell pc = (PropertyCell) cell;
            if (pc.owner == null && current.coins >= pc.price && rand.nextBoolean()) {
                current.coins -= pc.price;
                pc.owner = current;
                current.properties.add(pc);
                frame.appendLog(current.name + " bought property " + pc.name + " for " + pc.price + " coins.");
            }
        }
        // Randomly decide to build on one property.
        for (PropertyCell pc : current.properties) {
            if (pc.houses < 4 && current.coins >= pc.houseCost && rand.nextBoolean()) {
                current.coins -= pc.houseCost;
                pc.houses++;
                frame.appendLog(current.name + " built a house on property " + pc.name + ".");
                break;
            }
        }
        // If coins are low, decide to sell a property.
        if (current.coins < 2 && !current.properties.isEmpty()) {
            PropertyCell pc = current.properties.get(0);
            current.coins += pc.price;
            pc.owner = null;
            pc.houses = 0;
            current.properties.remove(pc);
            frame.appendLog(current.name + " sold property " + pc.name + " to get more coins.");
        }
        frame.updateBoard();
    }
    
    /* **************************
       * Player Movement and Events *
       ************************** */
    
    // Moves the player forward by a number of steps. Also handles passing the start cell.
    public void movePlayer(Player player, int steps) {
        int oldPos = player.position;
        int newPos = (oldPos + steps) % 16;
        if (oldPos + steps >= 16) {
            // Player has completed a lap.
            if (newPos == 0) {
                player.coins += 6;
                frame.appendLog(player.name + " landed exactly on Start and gets 6 coins.");
            } else {
                player.coins += 3;
                frame.appendLog(player.name + " passed Start and gets 3 coins.");
            }
        }
        player.position = newPos;
        frame.appendLog(player.name + " moved to cell " + board[newPos].name + ".");
    }
    
    // Processes the effect of landing on a cell.
    public void processCell(Player player) {
        Cell cell = board[player.position];
        if (cell instanceof SpecialCell) {
            SpecialCell sc = (SpecialCell) cell;
            switch(sc.eventType) {
                case 0:
                    // Start cell – already handled on passing.
                    frame.appendLog(player.name + " is on Start.");
                    break;
                case 1:
                    frame.appendLog(player.name + " landed on Special 1. Rolling for event...");
                    int eventDie = rollDie();
                    frame.appendLog("Event roll: " + eventDie);
                    if (eventDie == 1) {
                        player.coins -= 2;
                        frame.appendLog(player.name + " loses 2 coins.");
                    } else if (eventDie == 2) {
                        player.coins -= 1;
                        frame.appendLog(player.name + " loses 1 coin.");
                    } else if (eventDie == 3) {
                        movePlayer(player, 1);
                        frame.appendLog(player.name + " moves 1 space forward.");
                    } else if (eventDie == 4) {
                        movePlayer(player, 2);
                        frame.appendLog(player.name + " moves 2 spaces forward.");
                    } else if (eventDie == 5) {
                        player.coins += 1;
                        movePlayer(player, 1);
                        frame.appendLog(player.name + " receives 1 coin and moves 1 space forward.");
                    } else if (eventDie == 6) {
                        player.coins += 2;
                        movePlayer(player, 2);
                        frame.appendLog(player.name + " receives 2 coins and moves 2 spaces forward.");
                    }
                    break;
                case 2:
                    // Player receives 1 coin from each other active player.
                    int count = 0;
                    for (Player p : players) {
                        if (p != player && !p.isOut) {
                            p.coins -= 1;
                            count++;
                        }
                    }
                    player.coins += count;
                    frame.appendLog(player.name + " receives 1 coin from each of " + count + " players.");
                    break;
                case 3:
                    // Player will skip the next turn.
                    player.skipTurn = true;
                    frame.appendLog(player.name + " will skip their next turn.");
                    break;
            }
        } else if (cell instanceof PropertyCell) {
            // Landing on a property.
            PropertyCell pc = (PropertyCell) cell;
            if (pc.owner != null && pc.owner != player) {
                int rent = pc.getRent();
                player.coins -= rent;
                pc.owner.coins += rent;
                frame.appendLog(player.name + " pays " + rent + " coins rent to " + pc.owner.name + " for landing on " + pc.name + ".");
                // If the player goes bankrupt, mark them as out.
                if (player.coins < 0) {
                    player.isOut = true;
                    frame.appendLog(player.name + " is out of the game!");
                }
            }
        }
    }
    
    // Utility method to simulate a 6-sided die roll.
    public int rollDie() {
        return rand.nextInt(6) + 1;
    }
    
    // Ends the game by displaying the winner or a tie.
    public void gameOver() {
        String winner = "";
        for (Player p : players) {
            if (!p.isOut) {
                winner = p.name;
                break;
            }
        }
        if (winner.equals("")) {
            frame.appendLog("Game over. It's a tie!");
            JOptionPane.showMessageDialog(frame, "Game over. It's a tie! Turns: " + turnCount);
        } else {
            frame.appendLog("Game over. The winner is " + winner + " in " + turnCount + " turns!");
            JOptionPane.showMessageDialog(frame, "Game over. The winner is " + winner + " in " + turnCount + " turns!");
        }
        System.exit(0);
    }
}

/* **************************
   * Player Class *
   ************************** */
// Represents a player with name, coin count, current board position, color, and properties.
class Player {
    String name;
    int coins;
    int position;
    Color color;
    boolean isHuman;
    boolean skipTurn;
    boolean isOut;
    ArrayList<PropertyCell> properties;
    
    public Player(String name, Color color, boolean isHuman) {
        this.name = name;
        this.color = color;
        this.isHuman = isHuman;
        this.coins = 10;
        this.position = 0;
        this.skipTurn = false;
        this.isOut = false;
        properties = new ArrayList<PropertyCell>();
    }
}

/* **************************
   * Cell and Subclasses *
   ************************** */
// Base Cell class
abstract class Cell {
    String name;
    public Cell(String name) {
        this.name = name;
    }
}

// PropertyCell represents a purchasable property.
class PropertyCell extends Cell {
    String letter;
    int price;
    int houseCost;
    int[] rents; // Rent values: index 0 => no houses, 1 => one house, etc.
    int houses;
    Player owner;
    
    public PropertyCell(String name, String letter, int price, int houseCost, int[] rents) {
        super(name);
        this.letter = letter;
        this.price = price;
        this.houseCost = houseCost;
        this.rents = rents;
        this.houses = 0;
        this.owner = null;
    }
    
    // Returns the rent based on the number of houses.
    public int getRent() {
        return rents[houses];
    }
}

// SpecialCell represents one of the four special event cells.
class SpecialCell extends Cell {
    int eventType; // 0, 1, 2, or 3
    public SpecialCell(String name, int eventType) {
        super(name);
        this.eventType = eventType;
    }
}