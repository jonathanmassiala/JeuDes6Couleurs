package jeu.jonathan;

import javax.swing.*;

public class Game implements Runnable{

    @Override
    public void run() {
        int size = SetUpPane.getSizeBoard();
        int nbPlayer = NewGamePane.getNbPlayer();
        Board board = new HexagonGrid(size);
        board.jeu(nbPlayer);
    }

}
