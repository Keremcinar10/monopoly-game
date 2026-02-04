package lab26;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrame1 extends JFrame{
    
    private static final int FRAME_WIDTH=500;
    private static final int FRAME_LENGTH=600;  

    public ButtonFrame1()
    {
        createComponents();
        setSize(FRAME_WIDTH,FRAME_LENGTH);
    }
    private void createComponents()
    {
        JButton button = new JButton("Click me!");
        JPanel panel = new JPanel();
        panel.add(button);
        this.add(panel);

        ActionListener listener = new ClickListener();
        button.addActionListener(listener);

    }
}
