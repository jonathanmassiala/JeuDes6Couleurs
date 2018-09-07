package jeu.jonathan;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        /**SwingUtilities.invokeLater(new Runnable() {
         public void run() {
         new Frame("Jeu des 6 couleurs");
         }
         });*/
        Board board = new Board();
        board.displayBoard(13);

    }
}