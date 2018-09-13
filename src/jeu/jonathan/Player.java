package jeu.jonathan;

public class Player {

    private int playerId;
    private boolean isIA = false;
    private int nbCasesControlled = 1;
    private Color currentColorControlled;

    public Player(int playerId, boolean isIA, Color currentColorControlled) {
        this.playerId = playerId;
        this.isIA = isIA;
        this.currentColorControlled = currentColorControlled;
    }

    public int getPlayerId() {
        return this.playerId;
    }

}
