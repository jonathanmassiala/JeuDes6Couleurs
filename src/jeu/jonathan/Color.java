package jeu.jonathan;

public enum Color {
    //r, R, o, O, j, J, v, V, b, B, i, I;
    r,
    o,
    j,
    v,
    b,
    i,
    R,
    O,
    J,
    V,
    B,
    I;


    //Methods
    public static Color convertUpperColor(char couleur) {
        switch (couleur) {
            case 'r':
            case 'R':
                return Color.R;
            case 'o':
            case 'O':
                return Color.O;
            case 'j':
            case 'J':
                return Color.J;
            case 'v':
            case 'V':
                return Color.V;
            case 'b':
            case 'B':
                return Color.B;
            case 'i':
            case 'I':
                return Color.I;
            default:
                return null;
        }
    }
}