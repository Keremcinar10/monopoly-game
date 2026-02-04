package cs102Practice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;

public class Rebound {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ReboundPanel panel = new ReboundPanel();

        frame.add(panel);
        frame.setTitle("Rebound");
        frame.pack();
        frame.setVisible(true);
    }   
}

class ReboundPanel extends JPanel{

    private final int WIDTH=300;
    private final int LENGTH=100;
    private final int DELAY= 20;
    private final int CIRCLE_SIZE=35;

    private Timer timer;

    private int x,y,moveX,moveY;

    public ReboundPanel()
    {   
        timer = new Timer(DELAY,new ReboundListener());
        x=0;
        y=40;
        moveX=moveY=3;

        this.setPreferredSize(new Dimension(WIDTH,LENGTH));
        this.setBackground(Color.YELLOW);
        timer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
    }

    class ReboundListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            x+=moveX;
            y+=moveY;
            
            if(x<=0 || x>=WIDTH-CIRCLE_SIZE) moveX=moveX*-1;
            if(y<=0 || y>=LENGTH-CIRCLE_SIZE) moveY=moveY*-1;

            repaint();
        }
    }
}
