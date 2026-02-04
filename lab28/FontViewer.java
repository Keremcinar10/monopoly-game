package lab28;


import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FontViewer
{
    public static void main(String[] args) 
    {
        FontFrame a = new FontFrame();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setTitle("Font Viewer");
        a.setVisible(true);
    }
    
}

class FontFrame extends JFrame
{
    JPanel controlPanel;
    JLabel label;
    ActionListener listener;

    final int FRAME_WIDTH= 420;
    final int FRAME_LENGTH= 420;

   int style;
   int size;
   String faceName;

    public FontFrame()
    {
        label = new JLabel("Big Java");
        this.add(label, BorderLayout.CENTER);

        listener = new ChoiceListener();
        
        createControlPanel();
        setLabelFont();
        this.setSize(FRAME_WIDTH,FRAME_LENGTH);
    }

    class ChoiceListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            setLabelFont();
        }
    }

    public void createControlPanel()
    {
        controlPanel= new JPanel();
        controlPanel.setLayout(new GridLayout(3,1));
        controlPanel.setPreferredSize(new Dimension(400,200));
        

        JPanel faceNamePanel= createComboBox();
        faceNamePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JPanel styleGroupPanel= createCheckBoxes();
        styleGroupPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JPanel sizeGroupPanel= createRadioButtons(); 
        sizeGroupPanel.setAlignmentX(CENTER_ALIGNMENT);

        controlPanel.add(faceNamePanel);
        controlPanel.add(styleGroupPanel);
        controlPanel.add(sizeGroupPanel);

        this.add(controlPanel, BorderLayout.SOUTH);


    }
    public JPanel createRadioButtons()
    {
        JRadioButton smallButton = new JRadioButton("small");
        JRadioButton mediumButton = new JRadioButton("medium");
        JRadioButton largeButton = new JRadioButton("large"); 
        largeButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(smallButton);
        group.add(mediumButton);
        group.add(largeButton);
        
        JPanel pnl = new JPanel();
        pnl.add(smallButton);
        pnl.add(mediumButton);
        pnl.add(largeButton);
        pnl.setBorder(new TitledBorder(new EtchedBorder(),"Size"));

        smallButton.addActionListener(listener);
        mediumButton.addActionListener(listener);
        largeButton.addActionListener(listener);

         size =0;
        if(smallButton.isSelected()){size = 24;}
        else if(mediumButton.isSelected()){size = 36;}
        else if(largeButton.isSelected()){size = 48;}

        

       return pnl;
    }

    public JPanel createCheckBoxes()
    {
        JCheckBox  italicCheckBox= new JCheckBox("italic");
        JCheckBox  boldCheckBox= new JCheckBox( "bold");
       
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(2, 1));
        checkBoxPanel.add(italicCheckBox);
        checkBoxPanel.add(boldCheckBox);

        italicCheckBox.addActionListener(listener);
        boldCheckBox.addActionListener(listener);

        style =0;
        if(italicCheckBox.isSelected())
        {
            style = style + Font.ITALIC;
        }
        if(boldCheckBox.isSelected())
        {
            style = style+ Font.BOLD;
        }


        return checkBoxPanel;
    }

    public JPanel createComboBox()
    {
        JComboBox faceNameCombo= new JComboBox();
        faceNameCombo.setEditable(false);
        faceNameCombo.addItem("Serif");
        faceNameCombo.addItem("SansSerif");

        

        JPanel faceNamePanel= new JPanel(); 
        faceNamePanel.add(faceNameCombo);

        faceNameCombo.addActionListener(listener);

        this.faceName= (String) faceNameCombo.getSelectedItem();

       

        return faceNamePanel;
    }

    public void setLabelFont()
    {
        label.setFont(new Font( faceName,style,size));
        label.repaint();
    }
}

