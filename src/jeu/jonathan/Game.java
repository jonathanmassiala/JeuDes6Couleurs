package jeu.jonathan;

import javax.swing.*;

public class Game {

    private boolean playing = false;
    public static JFrame frame;


    public Game() {
        this.playing = true;
    }

    public static void start() {
        //JFrame frame = new Frame();
        /*int size = NewGamePane.getSizeBoard();
        int nbPlayer = NewGamePane.getNbPlayer();
        Board board = new HexagonGrid(size);
        board.jeu(nbPlayer);*/
    }

    public static JFrame getFrame() {
        return frame;
    }

}
