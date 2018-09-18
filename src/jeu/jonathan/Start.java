package jeu.jonathan;

import javax.swing.*;

public class Start {

    public static void main(String[] args) {

        JFrame frame = new Frame();
        //Game game = new Game();
        //game.start();
        int size = NewGamePane.getSizeBoard();
        int nbPlayer = NewGamePane.getNbPlayer();
        Board board = new HexagonGrid(size);
        board.jeu(nbPlayer);
        /*StdDraw.setCanvasSize(1000, 700);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);*/

    }
}