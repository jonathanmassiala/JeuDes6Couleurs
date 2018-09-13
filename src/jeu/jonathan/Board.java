package jeu.jonathan;

import java.util.Random;

public class Board {
    //Attributes
    private Case[][] cases;
    private int size;
    private int nbCases;
    private Color[] colors = Color.values();

    /**
     * Constructor
     * Creates the grid for the game
     * @param size height and width of the board
     */
    public Board(int size){
        if (size < 2) {
            throw new IllegalArgumentException("Le plateau doit faire au moins 2 cases de côté.");
        }
        this.size = size;
        cases = new Case[size][size];
        int random = new Random().nextInt((colors.length)/2);
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                cases[i][j] = new Case();
                cases[i][j].setColor(colors[random]);
            }
        }
    }
}
