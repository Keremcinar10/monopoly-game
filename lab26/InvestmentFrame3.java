package lab26;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InvestmentFrame3 extends JFrame{
    
    private static final int FRAME_WIDTH= 400;
    private static final int FRAME_LENGTH= 250;

    private static final int AREA_ROWS= 10;
    private static final int AREA_COLUMNS= 30;

    private static final int DEFAULT_RATE= 5;
    private static final int INITIAL_BALANCE= 1000;

    private JLabel rateLabel;
    private JTextField rateField;
    private JButton button ;
    private JTextArea resultArea;
    private double balance;

    public InvestmentFrame3()
    {
        this.setSize(FRAME_WIDTH,FRAME_LENGTH);
        this.balance= INITIAL_BALANCE;
        resultArea= new JTextArea(AREA_ROWS,AREA_COLUMNS);
        resultArea.setText(balance+ "\n");
        resultArea.setEditable(false);

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
    private void createPanel()
    {
        JPanel panel = new JPanel();
        panel.add(rateLabel);
        panel.add(rateField);
        panel.add(button);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        this.add(panel);
    }
    private void createButton()
    {
        this.button = new JButton("Add interest:");
        ActionListener listener = new AddInterestListener();
        this.button.addActionListener(listener);
    }
    class AddInterestListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            double rate = Double.parseDouble(rateField.getText());
            double interest= balance* rate/ 100;
            balance += interest;
            resultArea.append(balance+ "\n");
        }

    }
}   
