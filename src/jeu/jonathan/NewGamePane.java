package jeu.jonathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class NewGamePane extends JPanel {

    private static int nbPlayer = 2;
    private SetUpPane setUpPane = new SetUpPane();
    private static GamePane gamePane = new GamePane();
    private Font gameFont;
    private Thread t;

    public NewGamePane() throws IOException, FontFormatException {
        this.setBackground(Color.orange);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("NOUVELLE PARTIE");



        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t = new Thread(new Game());
                t.start();
                Frame.changePanel(1);
            }
        });


        this.add(title, BorderLayout.NORTH);
        this.add(setUpPane,BorderLayout.CENTER);
        this.add(playButton,BorderLayout.SOUTH);

    }




    public static GamePane getGamePane(){
        return gamePane;
    }



    public static int getNbPlayer(){
        return nbPlayer;
    }


}
