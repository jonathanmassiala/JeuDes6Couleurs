package jeu.jonathan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class NewGamePane extends JPanel {

    private static GamePane gamePane = new GamePane();
    private Thread t;

    NewGamePane() {
        this.setBackground(new Color(8,76,97));
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><font color='white'>NOUVELLE PARTIE</font></html>");
        Font font = new Font("Calibri",Font.ITALIC,30);
        title.setFont(font);
        title.setBorder(new EmptyBorder(20,20,20,20));


        JButton playButton = new JButton("<html><font color='white'>   PLAY   </font></html>");
        playButton.addActionListener(e -> {
            t = new Thread(new Game());
            if (t.isAlive()){
                t.interrupt();
            }
            t.start();
            Frame.changePanel(1);
        });
        playButton.setBackground(new Color(219,58,52));
        playButton.setOpaque(true);
        playButton.setBorderPainted(false);
        JPanel playPane = new JPanel();
        playPane.setBackground(new Color(8,76,97));
        playPane.add(playButton);
        playPane.setPreferredSize(new Dimension(350,150));

        this.add(title, BorderLayout.NORTH);
        SetUpPane setUpPane = new SetUpPane();
        this.add(setUpPane,BorderLayout.CENTER);
        this.add(playPane,BorderLayout.SOUTH);

    }

    static GamePane getGamePane(){
        return gamePane;
    }

}
