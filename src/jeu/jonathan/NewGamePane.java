package jeu.jonathan;

import javax.swing.*;
import java.awt.*;

class NewGamePane extends JPanel {

    private static GamePane gamePane = new GamePane();
    private Thread t;

    NewGamePane() {
        this.setBackground(Color.orange);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("NOUVELLE PARTIE");
        Font font = new Font("Calibri",Font.CENTER_BASELINE,20);
        title.setFont(font);
        //title.setVerticalAlignment();


        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            t = new Thread(new Game());
            if (t.isAlive()){
                t.interrupt();
            }
            t.start();
            Frame.changePanel(1);
        });
        //playButton.

        this.add(title, BorderLayout.NORTH);
        SetUpPane setUpPane = new SetUpPane();
        this.add(setUpPane,BorderLayout.CENTER);
        this.add(playButton,BorderLayout.SOUTH);

    }

    static GamePane getGamePane(){
        return gamePane;
    }

}
