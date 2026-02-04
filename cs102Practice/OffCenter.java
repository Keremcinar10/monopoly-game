package cs102Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

public class OffCenter {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Off Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OffCenterPanel panel = new OffCenterPanel();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

class OffCenterPanel extends JPanel
{
    private final int WIDTH= 300;
    private final int LENGTH= 300;

    private double length;
    private int centerX, centerY;
    private DecimalFormat fmt;
    private Point current;

    public OffCenterPanel()
    {
        this.addMouseListener(new OffCenterListener());
        centerX= WIDTH/2;
        centerY= LENGTH/2;

        fmt = new DecimalFormat("0.##");

        this.setPreferredSize(new Dimension(WIDTH, LENGTH));
        this.setBackground(Color.YELLOW);

    }
  
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawOval(centerX, centerY, 6, 6);

        if(current!=null)
        {
            g.drawLine(centerX+3, centerY+3, current.x, current.y);
            g.drawString("Distance: "+fmt.format(length), 10, 15);
        }
    }

    class OffCenterListener implements MouseListener
    {
        public void mouseClicked(MouseEvent e) {
            
            current = e.getPoint();
            length = Math.sqrt(Math.pow((current.x-centerX), 2) + Math.pow((current.y-centerY), 2));
            repaint();
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        
    }
}
