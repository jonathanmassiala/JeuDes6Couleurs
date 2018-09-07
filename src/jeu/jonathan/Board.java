package jeu.jonathan;

public class Board {

    /**
     * Creates and display the board for the game
     * @param size height and width of the board
     */
    public void displayBoard(int size){
        String[][] matrix = new String[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                matrix[i][j]= "R";
                System.out.print(matrix[i][j] + " | ");
            }
        System.out.println();
        }
    }
}
