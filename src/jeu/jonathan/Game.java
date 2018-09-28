package jeu.jonathan;


public class Game implements Runnable{

    @Override
    public void run() {
        Board board = new HexagonGrid(SetUpPane.getSizeBoard());
        board.jeu(SetUpPane.getNbPlayer(), SetUpPane.getNbIA(), SetUpPane.getDifficulty());
    }

}
