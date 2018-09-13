package jeu.jonathan;

import javax.swing.*;

public class Game {

    private boolean playing = false;

    public Game() {
        this.playing = true;
        Board board = new Board(10);
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                new Frame();
             }
         });
    }
}
