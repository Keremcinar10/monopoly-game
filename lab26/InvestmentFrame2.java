package lab26;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InvestmentFrame2 extends JFrame{
    
    private static final int FRAME_WIDTH= 300;
    private static final int FRAME_LENGTH= 400;

    private static final int DEFAULT_RATE= 5;
    private static final int INITIAL_BALANCE= 1000;

    private JLabel rateLabel;
    private JTextField rateField;
    private JButton button ;
    private JLabel resultLabel;
    private double balance;

    public InvestmentFrame2()
    {
        balance = INITIAL_BALANCE;
        this.resultLabel = new JLabel("Balance: "+ balance);
        this.setSize(FRAME_WIDTH, FRAME_LENGTH);    

        createButton();
        createTextField();
        createPanel();
    }
    public void createTextField()
    {
        this.rateLabel= new JLabel("Interest rate: ");

        final int FIELD_WIDTH = 10;
        this.rateField = new JTextField(FIELD_WIDTH);
        this. rateField.setText(""+ DEFAULT_RATE);
    }
    class AddInterestListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            double rate = Double.parseDouble(rateField.getText());
            double interest= balance* rate/ 100;
            balance += interest;
            resultLabel.setText("Balance: "+ balance);
        }
    }
    private void createButton()
    {
        this.button = new JButton("Add interest:");
        ActionListener listener = new AddInterestListener();
        this.button.addActionListener(listener);
    }
    private void createPanel()
    {
        JPanel panel = new JPanel();
        panel.add(rateLabel);
        panel.add(rateField);
        panel.add(button);
        panel.add(resultLabel);
        this.add(panel);
    }
    
}
