package lab26;

import java.util.ArrayList;

import javax.swing.JComponent;

public class CharComponent extends JComponent{
    
    private ArrayList<Double> values;
    private double maxValue;

    public CharComponent(double max)
    {
        values = new ArrayList<Double>();
        this.maxValue= max;
    }

    public void append (double value)
    {
        values.add(value);
        repaint();  
    }
    public void paintComponent()
    {
        
    }
}
