package jeu.jonathan;

public class Case {

    private Color color;
    private int owner = 0;
    private boolean taken = false;

    public void setColor(Color color) {
        this.color = color;
        taken = true;
    }

    public Color getColor() {
        return this.color;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
