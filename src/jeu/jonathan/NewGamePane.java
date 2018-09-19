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

    private static int sizeBoard = 15;
    private static int nbPlayer = 2;

    private JComboBox combo;
    private static GamePane gamePane = new GamePane();
    private Font gameFont;

    public NewGamePane() throws IOException, FontFormatException {
        this.setBackground(Color.orange);
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("NOUVELLE PARTIE");

        try {
            Font gameFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/astron boy.ttf"));
            label.setFont(gameFont.deriveFont(Font.BOLD, 48f));
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }



        String[] tab = {"BLEU", "VERT", "ROUGE", "NOIR"};
        combo = new JComboBox(tab);
        combo.setSelectedIndex(0);
        combo.addActionListener(new ColorListener());
        combo.setPreferredSize(new Dimension(100, 20));

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                Frame.changePanel(1);
                //Game.start();


            }
        });
        this.add(label, BorderLayout.NORTH);
        this.add(combo, BorderLayout.CENTER);
        this.add(playButton, BorderLayout.SOUTH);

    }


    private class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gamePane.setColor(Objects.requireNonNull(combo.getSelectedItem()).toString());

        }
    }

    public static GamePane getGamePane(){
        return gamePane;
    }

    public static int getSizeBoard(){
        return sizeBoard;
    }

    public static int getNbPlayer(){
        return nbPlayer;
    }
}
