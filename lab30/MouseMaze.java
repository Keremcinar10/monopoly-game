package lab30;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MouseMaze 
{
    public static void main(String[] args) 
    {
        MazeFrame a=new MazeFrame();
        ControlFrame b=new ControlFrame(a);

        a.setButtonFrame(b);

        a.setVisible(true);
        b.setVisible(true);
    }
}


class MazeFrame extends JFrame
{
    private MazePanel mazePanel;
    private ControlFrame buttonFrame;

    public MazeFrame()
    {
        super();
        
        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mazePanel = new MazePanel();
        this.add(mazePanel, BorderLayout.CENTER);

        this.setSize(new Dimension(419,448));
        this.setLocation(200, 100);
    }

    public void setButtonFrame(ControlFrame a)
    {
        this.buttonFrame=a;
        this.mazePanel.setControlFrame(a);
    }

    public void resetForFrame()
    {
        mazePanel.resetForPanel();
    }

    public void findPathForFrame()
    {
        this.mazePanel.findPathForPanel();
    }
}

class ControlFrame extends JFrame implements ActionListener
{
    public static final String NOTHING ="nothing";
    public static final String SET_START ="setStart";
    public static final String SET_END ="setEnd";
    public static final String ADD_WALL ="addWall";
    public static final String REMOVE_WALL ="removeWall";

    private JButton SetStartButton;
    private JButton SetEndBUtton;
    private JButton AddWallButton;
    private JButton RemoveWallButton;
    private JButton FindPathButton;
    private JButton ResetButton;

    private String currentMode=NOTHING;

    private MazeFrame frame;

    public ControlFrame(MazeFrame a)
    {
        super();

        this.frame = a;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,1));


        SetStartButton = new JButton("Set Start");
        SetEndBUtton = new JButton("Set End");
        AddWallButton = new JButton("Add Wall");
        RemoveWallButton = new JButton("Remove Wall");
        FindPathButton = new JButton("Find Path");
        ResetButton = new JButton("Reset");

        SetStartButton.addActionListener(this);
        SetEndBUtton.addActionListener(this);
        AddWallButton.addActionListener(this);
        RemoveWallButton.addActionListener(this);
        FindPathButton.addActionListener(this);
        ResetButton.addActionListener(this);

        this.add(SetStartButton);
        this.add(SetEndBUtton);
        this.add(AddWallButton);
        this.add(RemoveWallButton);
        this.add(FindPathButton);
        this.add(ResetButton);

        this.setSize(new Dimension(300,350));
        this.setLocation(610,200);
    }

    public String getCurrentMode()
    {
        return currentMode;
    }

    public void actionPerformed(ActionEvent event)
    {
        Object obj = event.getSource();

        if (obj==SetStartButton) 
        {
            currentMode=SET_START;
        } 
        else if (obj==SetEndBUtton) 
        {
            currentMode=SET_END;
        } 
        else if (obj==AddWallButton) 
        {
            currentMode=ADD_WALL;
        } 
        else if (obj==RemoveWallButton) 
        {
            currentMode=REMOVE_WALL;
        } 
        else if (obj==FindPathButton) 
        {
            frame.findPathForFrame();
            currentMode=NOTHING;
        } 
        else if (obj==ResetButton) 
        {
            frame.resetForFrame();
            currentMode=NOTHING;
        }
    }

    
}

class MazePanel extends JPanel implements MouseListener
{
    
    private int startRow;
    private int endRow;
    private int startColumn;
    private int endColumn;

    private BufferedImage wallImage;
    private BufferedImage mouseImage;
    private BufferedImage cheeseImage;

    private ControlFrame controlFrame;

    private ArrayList<Point> PathForCells= new ArrayList<Point>();

    private boolean[][]isWall;

    private int[][]distance;

    public MazePanel()
    {
        try 
        {
            String mouseName="mouse.png";
            mouseImage =ImageIO.read(new File("lab30/"+mouseName));

            String cheeseName="cheese.png";
            cheeseImage =ImageIO.read(new File("lab30/"+cheeseName));

            String wallName="wall.png";
            wallImage =ImageIO.read(new File("lab30/"+wallName));

        } 
        catch (IOException e) 
        {
            System.err.println(e);
        }

        this.startRow=0;
        this.endRow=4;
        this.startColumn=0;
        this.endColumn=4;

        distance=new int[5][5];
        isWall=new boolean[5][5];
        

        resetArrays();

        
        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(400,400));
        
    }

    public void resetForPanel() 
    {
        endRow=4;
        startRow=0;
        endColumn=4;
        startColumn=0;
        resetArrays();
        repaint();
    }

    public void setControlFrame(ControlFrame a) 
    {
        this.controlFrame=a;
    }

    private void resetArrays() {
        for(int i=0;i<5;i++) 
        {
            for(int j=0;j<5;j++) 
            {
                isWall[i][j]=false;
                distance[i][j]=-1;
            }
        }
        PathForCells.clear();
    }
    
    public void findPathForPanel()
    {
        for (int i=0;i<5;i++) 
        {
            for (int j=0;j<5;j++) 
            {
                distance[i][j]=-1;
            }
        }

        PathForCells.clear();

        checkCell(startRow, startColumn, 0);

        if (distance[endRow][endColumn]!=-1) 
        {
            createPath();
            repaint();
            return;
        }
        else 
        {
            JOptionPane.showMessageDialog(this, "No path found from the start to the end!");
            repaint();
            return;
        }
    }

    public void checkCell(int a, int b, int takenSteps)
    {
        if (a<0 || a>=5 || b<0 || b>=5)
            return;

        if (isWall[a][b])
            return;

        if (distance[a][b]!=-1 && distance[a][b]<=takenSteps)
            return;

        distance[a][b]=takenSteps;

        if (a==endRow && b==endColumn) return;
           
        
        checkCell(a,b-1,takenSteps+1);
        checkCell(a,b+1,takenSteps+1); 
        checkCell(a-1,b,takenSteps+1); 
        checkCell(a+1,b,takenSteps+1); 
    }

    public void createPath()
    {
        PathForCells.clear();

        int cColumn=endColumn;
        int cRow=endRow;
        
        PathForCells.add(new Point(cRow, cColumn));

        boolean stepFound=false;
        int count=1;

        while (!(cRow==startRow && cColumn==startColumn) && (count==1 || stepFound==true))
        {
            int currentDistance=distance[cRow][cColumn];

            if (cRow-1>=0 && distance[cRow-1][cColumn]==currentDistance-1) 
            {
                cRow=cRow-1;
                stepFound=true;
            }

            else if (cRow+1<5 && distance[cRow+1][cColumn]==currentDistance-1) 
            {
                cRow=cRow+1;
                stepFound=true;
            
            }

            else if (cColumn-1>=0 && distance[cRow][cColumn-1]==currentDistance-1) 
            {
                cColumn=cColumn- 1;
                stepFound= true;
            }

            else if (cColumn+ 1<5 && distance[cRow][cColumn+1]==currentDistance- 1) 
            {
                cColumn=cColumn+1;
                stepFound=true;
            }
            count++;

            PathForCells. add(new Point(cRow,cColumn));
        }
    } 


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (!PathForCells.isEmpty()) 
        {
            g.setColor(Color.GREEN); 

            for(Point p:PathForCells) 
            {
                int px=p.y*80;
                int py=p.x*80;
                g.fillRect(px,py,80,80);
            }
        }

        g.setColor(Color.BLACK);
        
        for (int i=0;i<5; i++) 
        {
            for (int j = 0; j<5; j++)
            {
                g.drawRect(j*80,i*80,80,80);
            }
        }

        for (int r=0;r<5;r++) 
        {
            for (int c=0;c<5;c++) 
            {
                int x= c*80;
                int y= r*80;

                if (isWall[r][c]) 
                {
                    g.drawImage(wallImage, x, y, 80, 80, null);
                }
            }
        }

       
        g.drawImage(mouseImage, startColumn*80, startRow*80, 80, 80, null);
        g.drawImage(cheeseImage, endColumn*80, endRow*80, 80, 80, null);
        
    }


    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }

    public void mousePressed(MouseEvent e)
    {

        String mode=controlFrame.getCurrentMode();

        int x=e.getX();
        int y=e.getY();

        int c=x/80;
        int r=y/80;

        if (r<0 || r>=5 || c<0 || c>=5) return;
        
        if (mode==ControlFrame.SET_START) 
        {
           
            if (r==endRow && c==endColumn) 
            {
                JOptionPane.showMessageDialog(this, "You cannot place the mouse on the cheese!");
                return;
            }
            startRow=r;
            startColumn=c;
            repaint();
        } 

        else if (mode==ControlFrame.SET_END) {
           
            if (r==startRow && c==startColumn) 
            {
                JOptionPane.showMessageDialog(this, "You cannot place the cheese on the mouse!");
                return;
            }
            endRow=r;
            endColumn=c;
            repaint();
        }

        else if(mode==ControlFrame.ADD_WALL) 
        {
          
            if ((r==startRow && c==startColumn) || (r==endRow && c==endColumn)) return;
               
            isWall[r][c]=true;
            repaint();
        }

        else if (mode==ControlFrame.REMOVE_WALL) 
        {
            isWall[r][c]=false;
            repaint();
        }
    }
}