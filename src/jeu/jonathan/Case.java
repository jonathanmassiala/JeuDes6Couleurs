package jeu.jonathan;

public class Case {

    private Color color;
    private int owner;
    private boolean isOwned = false;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setOwner(int owner) {
        this.owner = owner;
        this.isOwned = true;
    }

    public int getOwner() {
        return this.owner;
    }
}
