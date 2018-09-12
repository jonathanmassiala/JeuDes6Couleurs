package jeu.jonathan;

import java.util.Random;

public class Board {

    private Color[] colors = Color.values();

    /**
     * @return String containing random Color for the creation of the board
     */
    public Color getRandomColor() {
        int random = new Random().nextInt((colors.length)/2);
        return colors[random];
    }
    /**
     * Creates and display the board for the game
     * @param size height and width of the board
     */
    public void displayFirstBoard(int size){
        Color[][] matrix = new Color[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                matrix[i][j]= getRandomColor();
                //System.out.print(matrix[i][j] + " | ");
            }
            //System.out.println();
        }
        System.out.println(Color.convertUpperColor(matrix[0][0].toString()));
        //while (matrix[0][0] == matrix[size - 1][size - 1]) {
        //    matrix[size - 1][size - 1] = getRandomColor();
       // }
        //matrix[0][0] = Color.convertUpperColor(matrix[0][0].toString());
        //matrix[size - 1][size - 1] = matrix[size - 1][size - 1].taken;

    }
}
