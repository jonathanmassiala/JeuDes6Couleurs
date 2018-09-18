package jeu.jonathan;

import javax.swing.*;

public class Game {

    private boolean playing = false;
    public static JFrame frame;


    public Game() {
        this.playing = true;
    }

    public static void start() {
        Board board = new HexagonGrid(10);
        board.jeu(2);
    }

    public static JFrame getFrame() {
        return frame;
    }

}
