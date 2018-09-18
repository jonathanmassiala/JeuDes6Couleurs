package jeu.jonathan;

public class Case {

    private Couleur color;
    private int owner;
    private boolean owned = false;
    private boolean recentlyChanged = false;

    public void setCouleur(Couleur color) {
        this.color = color;
        recentlyChanged = true;
    }
    public void setCouleurFirstTime(Couleur color) {
        this.color = color;
    }

    public Couleur getCouleur() {
        return this.color;
    }

    public void setOwner(int owner) {
        this.owner = owner;
        this.owned = true;
    }

    public int getOwner() {
        return this.owner;
    }

    public boolean isOwned() {
        return owned;
    }
    public void notOwned() {
        this.owned = false;
    }
    public boolean isRecentlyChanged() {
        return recentlyChanged;
    }
    public void notRecentlyChanged() {
        this.recentlyChanged = false;
    }
}
