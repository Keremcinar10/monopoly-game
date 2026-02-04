package lab28;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Calculator {
    public static void main(String[] args) {
        MainFrame a= new MainFrame();
    }
}

class MainFrame extends JFrame 
{
    JTextField resultField;
    JPanel buttonPanel;

    Font myFont = new Font("Ink Free", Font.BOLD,30);

    double num1=0, num2=0, result=0;
    char operator;
    

    public MainFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator");
        this.setSize(new Dimension(420,550));
        this.setLayout(null);
        
        this.resultField= new JTextField();
        this.resultField.setBounds(50,25,300,50);
        this.resultField.setFont(myFont);
        this.resultField.setEditable(false);
        this.add(resultField);

        buttonPanel= new JPanel();
        buttonPanel.setBounds(50,100,300,350);
        buttonPanel.setLayout(new GridLayout(4,4,10,10));
       
        JButton button1= new JButton("1");
        button1.setFont(myFont);
        JButton button2= new JButton("2");
        button2.setFont(myFont);
        JButton button3= new JButton("3");
        button3.setFont(myFont);
        JButton button4= new JButton("4");
        button4.setFont(myFont);
        JButton button5= new JButton("5");
        button5.setFont(myFont);
        JButton button6= new JButton("6");
        button6.setFont(myFont);
        JButton button7= new JButton("7");
        button7.setFont(myFont);
        JButton button8= new JButton("8");
        button8.setFont(myFont);
        JButton button9= new JButton("9");
        button9.setFont(myFont);
        JButton button0= new JButton("0");
        button0.setFont(myFont);
        JButton buttonAdd = new JButton("+");
        buttonAdd.setFont(myFont);
        JButton buttonSubtract = new JButton("-");
        buttonSubtract.setFont(myFont);
        JButton buttonMultiply = new JButton("*");
        buttonMultiply.setFont(myFont);
        JButton buttonDivide = new JButton("/");
        buttonDivide.setFont(myFont);
        JButton buttonClear = new JButton("C");
        buttonClear.setFont(myFont);
        JButton buttonEquals = new JButton("=");
        buttonClear.setFont(myFont);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(buttonAdd);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(buttonSubtract);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(buttonDivide);
        buttonPanel.add(button0);
        buttonPanel.add(buttonClear);
        buttonPanel.add(buttonEquals);
        buttonPanel.add(buttonMultiply);

        this.add(buttonPanel);

        this.setVisible(true);


        button1.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(1)));
            }
        });

        button2.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(2)));
            }
        });

        button3.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(3)));
            }
        });

        button4.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(4)));
            }
        });

        button5.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(5)));
            }
        });

        button6.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(6)));
            }
        });

        button7.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(7)));
            }
        });

        button8.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(8)));
            }
        });

        button9.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(9)));
            }
        });

        button0.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText(resultField.getText().concat(String.valueOf(0)));
            }
        });

        buttonAdd.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                num1= Double.parseDouble(resultField.getText());
                operator='+';
                resultField.setText("");
            }
        });

        buttonSubtract.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                num1= Double.parseDouble(resultField.getText());
                operator='-';
                resultField.setText("");
            }
        });

        buttonMultiply.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                num1= Double.parseDouble(resultField.getText());
                operator='*';
                resultField.setText("");
            }
        });

        buttonDivide.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                num1= Double.parseDouble(resultField.getText());
                operator='/';
                resultField.setText("");
            }
        });

        buttonClear.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                resultField.setText("");
            }
        });

        buttonEquals.addActionListener(

        new ActionListener() {
            
            public void actionPerformed(ActionEvent event)
            {
                num2= Double.parseDouble(resultField.getText());

                switch(operator)
                {
                    case '+':
                        result = num1+num2;
                    break;
                    case '-':
                        result = num1-num2;
                    break;
                    case '*':
                        result = num1*num2;
                    break;
                    case '/':
                        result = num1/num2;
                    break;
                }
                resultField.setText(String.valueOf(result));
                num1= result;
            }
        });
    }

    
}



