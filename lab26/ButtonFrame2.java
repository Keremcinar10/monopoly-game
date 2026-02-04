package lab26;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonFrame2 extends JFrame{
    
    private JLabel label;
    private JButton button;

    private static final int FRAME_WIDTH=300;
    private static final int FRAME_LENGTH=300;

    class ClickListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
           label.setText("I was clicked!");
            }
    }

    public ButtonFrame2()
    {
        createComponents();
        this.setSize(FRAME_WIDTH ,FRAME_LENGTH);
    }
    private void createComponents()
    {
        this.button= new JButton("CLick me!");
        ActionListener listener = new ClickListener();
        this.button.addActionListener(listener);

        this.label= new JLabel("Hello world!");

        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(label);
        this.add(panel);
    }
}
