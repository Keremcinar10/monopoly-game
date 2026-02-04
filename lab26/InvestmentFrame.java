package lab26;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvestmentFrame extends JFrame{
    
    private JLabel label;
    private JButton button;
    private double balance;

    private static final int FRAME_WIDTH= 300;
    private static final int FRAME_LENGTH= 400;

    private static final int INTEREST_RATE= 5;
    private static final int INITIAL_BALANCE= 1000;

    public InvestmentFrame()
    {
        this.balance= INITIAL_BALANCE;
        createComponents();
        setSize(FRAME_WIDTH, FRAME_LENGTH);
    }
    private void createComponents()
    {
        this.button = new JButton("Add interest");
        ActionListener listener = new InvestmentListener();
        button.addActionListener(listener);

        this.label= new JLabel("Balance: "+ balance);

        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(label);
        this.add(panel);
    }
    class InvestmentListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            double interest = balance * INTEREST_RATE / 100;
            balance+=interest;
            label.setText("Balance:"+balance);
        }
    }
}
