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



        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            t = new Thread(new Game());
            t.start();
            Frame.changePanel(1);
        });


        this.add(title, BorderLayout.NORTH);
        SetUpPane setUpPane = new SetUpPane();
        this.add(setUpPane,BorderLayout.CENTER);
        this.add(playButton,BorderLayout.SOUTH);

    }

    static GamePane getGamePane(){
        return gamePane;
    }

}
