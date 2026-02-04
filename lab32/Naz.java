package lab32;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Naz extends JFrame {
    private JButton yesButton;
    private JButton noButton;
    private JLabel topText;
    private JLabel iconLabel;
    private JLabel questionText;
    private Random rand = new Random();

    public Naz() {
        super("Naz APP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        
        topText = new JLabel("BENİ AFFEDER MİSİNNN ????", SwingConstants.CENTER);
        topText.setForeground(Color.WHITE);
        topText.setFont(new Font("SansSerif", Font.BOLD, 20));
        topText.setBounds(20, 20, 560, 30);
        add(topText);

        
        ImageIcon icon = new ImageIcon("initial_icon.png");
        iconLabel = new JLabel(icon);
        int iconW = icon.getIconWidth();
        int iconH = icon.getIconHeight();
        iconLabel.setBounds((600 - iconW) / 2, 70, iconW, iconH);
        add(iconLabel);

        
        questionText = new JLabel("BENJE EVETE BAS :)", SwingConstants.CENTER);
        questionText.setForeground(Color.WHITE);
        questionText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        questionText.setBounds(20, 240, 560, 25);
        add(questionText);

        
        yesButton = new JButton("EVET");
        yesButton.setBackground(Color.RED);
        yesButton.setForeground(Color.WHITE);
        yesButton.setFocusPainted(false);
        yesButton.setBounds(150, 300, 120, 40);
        yesButton.addActionListener(e -> showFinalScreen());
        add(yesButton);

        
        noButton = new JButton("HAYIR");
        noButton.setBackground(Color.RED);
        noButton.setForeground(Color.WHITE);
        noButton.setFocusPainted(false);
        noButton.setBounds(330, 300, 120, 40);
        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                teleportNoButton();
            }
        });
        add(noButton);
    }

    private void teleportNoButton() {
        int frameW = getContentPane().getWidth();
        int frameH = getContentPane().getHeight();
        int btnW = noButton.getWidth();
        int btnH = noButton.getHeight();
        int x = rand.nextInt(frameW - btnW - 20) + 10;
        int y = rand.nextInt(frameH - btnH - 20) + 60;
        noButton.setLocation(x, y);
    }

    private void showFinalScreen() {
        JFrame finalFrame = new JFrame("LETSGO! 💀");
        finalFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        finalFrame.getContentPane().setBackground(Color.BLACK);
        finalFrame.setLayout(new BorderLayout(0, 10));

       
        JLabel finalText = new JLabel("AFFEDİCEĞİNİ BİLİYORDUMMMM !!!", SwingConstants.CENTER);
        finalText.setForeground(Color.WHITE);
        finalText.setFont(new Font("SansSerif", Font.BOLD, 20));
        finalFrame.add(finalText, BorderLayout.NORTH);

       
        ImageIcon rawIcon = new ImageIcon("lab32/nFoto.jpeg");
        int imgW = rawIcon.getIconWidth();
        int imgH = rawIcon.getIconHeight();
        int maxW = 550;
        int maxH = 350;
        double scale = Math.min((double) maxW / imgW, (double) maxH / imgH);
        Image scaledImg = rawIcon.getImage().getScaledInstance((int)(imgW * scale), (int)(imgH * scale), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel motherLabel = new JLabel(scaledIcon, SwingConstants.CENTER);
        finalFrame.add(motherLabel, BorderLayout.CENTER);

        finalFrame.pack();
        finalFrame.setResizable(false);
        finalFrame.setLocationRelativeTo(this);
        finalFrame.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Naz app = new Naz();
            app.setVisible(true);
        });
    }
}