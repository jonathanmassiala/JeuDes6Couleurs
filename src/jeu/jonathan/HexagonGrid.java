package jeu.jonathan;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class HexagonGrid extends Board {

    // attribute
    final private static double RACINE_3 = Math.sqrt(3);

    // constructor
    HexagonGrid(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Le rayon du plateau doit faire au moins 1 case.");
        }
        this.size = size;
        this.sizeBoard = 4 + 6 * size;
        //this.sizeBoard =  4 * size;
        this.tailleInterface = 0.3;
        this.nbCases = 3 * size * (size + 1) + 1;
        cases = new Case[2 * size + 1][2 * size + 1];
        Random random = new Random();
        int longueur = size;
        for (int i = 0; i < size + 1; i++) {
            longueur++;
            for (int j = 0; j < longueur; j++) {
                cases[i][j] = new Case();
                cases[i][j].setCouleurFirstTime(couleurs[random.nextInt(6) * 2]);
            }
        }
        for (int i = size + 1; i < 2 * size + 1; i++) {
            longueur--;
            for (int j = 0; j < longueur; j++) {
                cases[i][j] = new Case();
                cases[i][j].setCouleurFirstTime(couleurs[random.nextInt(6) * 2]);
            }
        }
    }

    @Override
    public void jeu(int nbreJoueursHumains, int nbreJoueursIA, int difficulty) {

        int nbreJoueurs = nbreJoueursHumains + nbreJoueursIA;

        if (nbreJoueurs < 2 || 4 < nbreJoueurs) {
            throw new IllegalArgumentException("Il doit y avoir entre 2 et 4 joueurs par partie.");
        }

        capturedCases = nbreJoueurs;
        for (int i = 0; i < nbreJoueurs; i++) {
            capturedCasesByPlayer[i] = 1;
        }

        colorTakenByAPlayer = new int[nbreJoueurs];

        firstCases(nbreJoueurs);

        int index;

        for (int color : colorTakenByAPlayer) {
            index = notControlledColors.indexOf(color);
            System.out.println("index :" + index);
            notControlledColors.remove(index);
        }

        displayBoard();
        for (int i : colorTakenByAPlayer) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(i * sizeBoard * 0.2 + sizeBoard * tailleInterface * 0.5, sizeBoard * 0.1, sizeBoard * 0.1);
        }
        displayGUIScores(nbreJoueurs, nbreJoueurs, sizeBoard, tailleInterface);

        int selectedButton;
        Couleur couleurUpper, couleurLower;

        while (game) {

            for (int i = 1; i <= nbreJoueurs; i++) {
                if (i <= nbreJoueursHumains) {
                    selectedButton = getMouseXY();
                    selectedButton = validerCouleur(selectedButton, i);
                }
                else {
                    switch (difficulty) {
                        case 1:
                            selectedButton = artificialIntelligence1(nbreJoueurs);
                            break;
                        case 2:
                            selectedButton = artificialIntelligence2(nbreJoueurs, i)[0];
                            break;
                        default:
                            selectedButton = artificialIntelligence1(nbreJoueurs);
                            break;
                    }
                }
                notControlledColors.add(colorTakenByAPlayer[i - 1]);
                index = notControlledColors.indexOf(selectedButton);
                notControlledColors.remove(index);
                colorTakenByAPlayer[i - 1] = selectedButton;
                couleurUpper = couleurs[2 * selectedButton + 1];
                couleurLower = couleurs[2 * selectedButton];
                capture(couleurUpper, couleurLower, i);
                displayChangedCases();
                for (int k = 0; k < 6; k++) {
                    StdDraw.setPenColor(colors[k]);
                    StdDraw.filledSquare(k * sizeBoard * 0.2 + sizeBoard * tailleInterface * 0.5, sizeBoard * 0.1, sizeBoard * 0.1);
                }
                for (int k : colorTakenByAPlayer) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledCircle(k * sizeBoard * 0.2 + sizeBoard * tailleInterface * 0.5, sizeBoard * 0.1, sizeBoard * 0.1);
                }
                noCaseRecentlyTaken();
                displayGUIScores(i, nbreJoueurs, sizeBoard, tailleInterface);

                if (endGame(nbreJoueurs, nbCases)) {
                    i = 5;
                }
            }
        }
    }

    @Override
    protected void displayBoard() {
        StdDraw.setXscale(0, sizeBoard * (1 + tailleInterface));
        StdDraw.setYscale(0, sizeBoard * (1 + tailleInterface));

        for (int i = 0; i < 6; i++) {
            StdDraw.setPenColor(colors[i]);
            StdDraw.filledSquare(i * sizeBoard * 0.2 + sizeBoard * tailleInterface * 0.5, sizeBoard * 0.1, sizeBoard * 0.1);
        }

        double[] x1 = {RACINE_3, 0, 0, RACINE_3, 2 * RACINE_3, 2 * RACINE_3};
        double[] y1 = {0, 1, 3, 4, 3, 1};

        for (int k = 0; k < 6; k++) {
            x1[k] += sizeBoard * (1 + tailleInterface) * 0.5 - (2 * size + 1) * RACINE_3;
            y1[k] += (sizeBoard - 4) * 0.5 + sizeBoard * tailleInterface;
        }

        drawCases(x1, y1, size, 0);

        int longueur = 2 * size;

        drawLine(x1, y1, longueur, 1);

        double[] x2 = new double[6];
        double[] y2 = new double[6];

        for (int k = 0; k < 6; k++) {
            x2[k] = x1[k];
            y2[k] = y1[k];
        }

        while (longueur > size) {
            longueur--;
            goBack(x1, y1, longueur, 1);
            drawLine(x1, y1, longueur, 1);
            goBack(x2, y2, longueur, -1);
            drawLine(x2, y2, longueur, -1);
        }
    }

    private void goBack(double[] x, double[] y, int longueur, int directionY) {
        for (int k = 0; k < 6; k++) {
            x[k] -= (2 * longueur + 1) * RACINE_3;
            y[k] += 3 * directionY;
        }

        StdDraw.filledPolygon(x, y);

        drawCases(x, y, size - directionY * (2 * size - longueur), 0);
    }

    private void drawLine(double[] x, double[] y, int longueur, int directionY) {
        for (int i = 0; i < longueur; i++) {
            for (int k = 0; k < 6; k++) {
                x[k] += 2 * RACINE_3;
            }
            drawCases(x, y, size - directionY * (2 * size - longueur), i + 1);
        }
    }

    private void displayChangedCases() {

        double[] x = {RACINE_3, 0, 0, RACINE_3, 2 * RACINE_3, 2 * RACINE_3};
        double[] y = {0, 1, 3, 4, 3, 1};

        for (int k = 0; k < 6; k++) {
            x[k] += (sizeBoard * (1 + tailleInterface)) / 2 - (2 * size + 1) * RACINE_3;
            y[k] += (sizeBoard - 4)/2 + sizeBoard * tailleInterface;
        }

        int longueur = size;
        for (int i = 0; i < size + 1; i++) {
            longueur++;
            for (int j = 0; j < longueur; j++) {
                if (cases[i][j].isRecentlyChanged() || cases[i][j].isOwned()) {
                    cases[i][j].notRecentlyChanged();
                    for (int k = 0; k < 6; k++) {
                        x[k] += RACINE_3 * (Math.abs(size - i) + 2 * j);
                        y[k] += 3 * (size - i);
                    }
                    drawCases(x, y , i, j);
                    for (int k = 0; k < 6; k++) {
                        x[k] -= RACINE_3 * (Math.abs(size - i) + 2 * j);
                        y[k] -= 3 * (size - i);
                    }
                }
            }
        }
        for (int i = size + 1; i < 2 * size + 1; i++) {
            longueur--;
            for (int j = 0; j < longueur; j++) {
                if (cases[i][j].isRecentlyChanged() || cases[i][j].isOwned()) {
                    cases[i][j].notRecentlyChanged();
                    for (int k = 0; k < 6; k++) {
                        x[k] += RACINE_3 * (Math.abs(size - i) + 2 * j);
                        y[k] += 3 * (size - i);
                    }
                    drawCases(x, y , i, j);
                    for (int k = 0; k < 6; k++) {
                        x[k] -= RACINE_3 * (Math.abs(size - i) + 2 * j);
                        y[k] -= 3 * (size - i);
                    }
                }
            }
        }
    }

    private void drawCases(double[] x, double[] y, int i, int j) {
        switch (cases[i][j].getCouleur()) {
            case r:
            case R:
                StdDraw.setPenColor(225,0,0);
                break;
            case o:
            case O:
                StdDraw.setPenColor(247,92,3);
                break;
            case j:
            case J:
                StdDraw.setPenColor(252,212,0);
                break;
            case v:
            case V:
                StdDraw.setPenColor(26,215,71);
                break;
            case b:
            case B:
                StdDraw.setPenColor(22,144,243);
                break;
            case i:
            case I:
                StdDraw.setPenColor(201,0,187);
                break;
            default:
                break;
        }
        StdDraw.filledPolygon(x, y);
        drawContour(x,y);
        StdDraw.setFont();
        StdDraw.setPenColor(StdDraw.BLACK);
        switch (cases[i][j].getOwner()) {
            case 1:
                StdDraw.text(x[0], y[0] + 2, "1");
                break;
            case 2:
                StdDraw.text(x[0], y[0] + 2, "2");
                break;
            case 3:
                StdDraw.text(x[0], y[0] + 2, "3");
                break;
            case 4:
                StdDraw.text(x[0], y[0] + 2, "4");
                break;
            default:
                break;
        }
    }

    private void drawContour(double[] x, double[] y){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.polygon(x,y);
    }

    @Override
    protected void firstCases(int nbreJoueurs) {
        ArrayList<Integer> notControlledColors = new ArrayList<>();
        for (int i = 0, len = couleurs.length / 2; i < len; i += 1) {
            notControlledColors.add(i);
        }
        Random random = new Random();
        int index = random.nextInt(notControlledColors.size());
        cases[2 * size][0].setCouleur(couleurs[2 * notControlledColors.get(index) + 1]);
        colorTakenByAPlayer[0] = notControlledColors.get(index);
        notControlledColors.remove(index);
        cases[2 * size][0].setOwner(1);
        cases[2 * size][0].notOwned();
        if (nbreJoueurs > 2) {
            index = random.nextInt(notControlledColors.size());
            cases[0][0].setCouleur(couleurs[2 * notControlledColors.get(index) + 1]);
            colorTakenByAPlayer[1] = notControlledColors.get(index);
            notControlledColors.remove(index);
            cases[0][0].setOwner(2);
            cases[0][0].notOwned();
            if (nbreJoueurs > 3) {
                index = random.nextInt(notControlledColors.size());
                cases[0][size].setCouleur(couleurs[2 * notControlledColors.get(index) + 1]);
                colorTakenByAPlayer[2] = notControlledColors.get(index);
                notControlledColors.remove(index);
                cases[0][size].setOwner(3);
                cases[0][size].notOwned();
                index = random.nextInt(notControlledColors.size());
                cases[2 * size][size].setCouleur(couleurs[2 * notControlledColors.get(index) + 1]);
                colorTakenByAPlayer[3] = notControlledColors.get(index);
                notControlledColors.remove(index);
                cases[2 * size][size].setOwner(4);
                cases[2 * size][size].notOwned();
            }
            else {
                index = random.nextInt(notControlledColors.size());
                cases[size][2 * size].setCouleur(couleurs[2 * notControlledColors.get(index) + 1]);
                colorTakenByAPlayer[2] = notControlledColors.get(index);
                notControlledColors.remove(index);
                cases[size][2 * size].setOwner(3);
                cases[size][2 * size].notOwned();
            }

        }
        else {
            index = random.nextInt(notControlledColors.size());
            cases[0][size].setCouleur(couleurs[2 * notControlledColors.get(index) + 1]);
            colorTakenByAPlayer[1] = notControlledColors.get(index);
            notControlledColors.remove(index);
            cases[0][size].setOwner(2);
            cases[0][size].notOwned();
        }
    }

    @Override
    protected void capture(Couleur couleurUpper, Couleur couleurLower, int owner) {
        int longueur = size;
        for (int i = 0; i < size; i++) {
            longueur++;
            for (int j = 0; j < longueur; j++) {
                if (!cases[i][j].isOwned() && cases[i][j].getOwner() == owner) {
                    cases[i][j].setCouleur(couleurUpper);
                    captureCase(couleurUpper, couleurLower, owner, i + 1, j);
                    captureCase(couleurUpper, couleurLower, owner, i + 1, j + 1);
                    if (j > 0) {
                        captureCase(couleurUpper, couleurLower, owner, i, j - 1);
                        if (i > 0) {
                            captureCase(couleurUpper, couleurLower, owner, i - 1, j - 1);
                        }
                    }
                    if (j < longueur - 1) {
                        captureCase(couleurUpper, couleurLower, owner, i, j + 1);
                        if (i > 0) {
                            captureCase(couleurUpper, couleurLower, owner, i - 1, j);
                        }
                    }
                }
            }
        }
        longueur++;
        for (int j = 0; j < longueur; j++) {
            if (!cases[size][j].isOwned() && cases[size][j].getOwner() == owner) {
                cases[size][j].setCouleur(couleurUpper);
                if (j > 0) {
                    captureCase(couleurUpper, couleurLower, owner, size, j - 1);
                    captureCase(couleurUpper, couleurLower, owner, size - 1, j - 1);
                    captureCase(couleurUpper, couleurLower, owner, size + 1, j - 1);
                }
                if (j < longueur - 1) {
                    captureCase(couleurUpper, couleurLower, owner, size, j + 1);
                    captureCase(couleurUpper, couleurLower, owner, size - 1, j);
                    captureCase(couleurUpper, couleurLower, owner, size + 1, j);
                }
            }
        }
        for (int i = size + 1; i < 2 * size + 1; i++) {
            longueur--;
            for (int j = 0; j < longueur; j++) {
                if (!cases[i][j].isOwned() && cases[i][j].getOwner() == owner) {
                    cases[i][j].setCouleur(couleurUpper);
                    captureCase(couleurUpper, couleurLower, owner, i - 1, j);
                    captureCase(couleurUpper, couleurLower, owner, i - 1, j + 1);
                    if (j > 0) {
                        captureCase(couleurUpper, couleurLower, owner, i, j - 1);
                        if (i < 2 * size) {
                            captureCase(couleurUpper, couleurLower, owner, i + 1, j - 1);
                        }
                    }
                    if (j < longueur - 1) {
                        captureCase(couleurUpper, couleurLower, owner, i, j + 1);
                        if (i < 2 * size) {
                            captureCase(couleurUpper, couleurLower, owner, i + 1, j);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int fausseCapture(Couleur couleurUpper, Couleur couleurLower, int owner) {
        int a = 0;
        int longueur = size;
        for (int i = 0; i < size; i++) {
            longueur++;
            for (int j = 0; j < longueur; j++) {
                if (!cases[i][j].isOwned() && cases[i][j].getOwner() == owner) {
                    if (testCapturedCases(couleurUpper, couleurLower, i + 1, j)) {
                        a++;
                    }
                    if (testCapturedCases(couleurUpper, couleurLower, i + 1, j + 1)) {
                        a++;
                    }
                    if (j > 0) {
                        if (testCapturedCases(couleurUpper, couleurLower, i, j - 1)) {
                            a++;
                        }
                        if (i > 0) {
                            if (testCapturedCases(couleurUpper, couleurLower, i - 1, j - 1)) {
                                a++;
                            }
                        }
                    }
                    if (j < longueur - 1) {
                        if (testCapturedCases(couleurUpper, couleurLower, i, j + 1)) {
                            a++;
                        }
                        if (i > 0) {
                            if (testCapturedCases(couleurUpper, couleurLower, i - 1, j)) {
                                a++;
                            }
                        }
                    }
                }
            }
        }
        longueur++;
        for (int j = 0; j < longueur; j++) {
            if (!cases[size][j].isOwned() && cases[size][j].getOwner() == owner) {
                if (j > 0) {
                    if (testCapturedCases(couleurUpper, couleurLower, size, j - 1)) {
                        a++;
                    }
                    if (testCapturedCases(couleurUpper, couleurLower, size - 1, j - 1)) {
                        a++;
                    }
                    if (testCapturedCases(couleurUpper, couleurLower, size + 1, j - 1)) {
                        a++;
                    }
                }
                if (j < longueur - 1) {
                    if (testCapturedCases(couleurUpper, couleurLower, size, j + 1)) {
                        a++;
                    }
                    if (testCapturedCases(couleurUpper, couleurLower, size - 1, j)) {
                        a++;
                    }
                    if (testCapturedCases(couleurUpper, couleurLower, size + 1, j)) {
                        a++;
                    }
                }
            }
        }
        for (int i = size + 1; i < 2 * size + 1; i++) {
            longueur--;
            for (int j = 0; j < longueur; j++) {
                if (!cases[i][j].isOwned() && cases[i][j].getOwner() == owner) {
                    if (testCapturedCases(couleurUpper, couleurLower, i - 1, j)) {
                        a++;
                    }
                    if (testCapturedCases(couleurUpper, couleurLower, i - 1, j + 1)) {
                        a++;
                    }
                    if (j > 0) {
                        if (testCapturedCases(couleurUpper, couleurLower, i, j - 1)) {
                            a++;
                        }
                        if (i < 2 * size) {
                            if (testCapturedCases(couleurUpper, couleurLower, i + 1, j - 1)) {
                                a++;
                            }
                        }
                    }
                    if (j < longueur - 1) {
                        if (testCapturedCases(couleurUpper, couleurLower, i, j + 1)) {
                            a++;
                        }
                        if (i < 2 * size) {
                            if (testCapturedCases(couleurUpper, couleurLower, i + 1, j)) {
                                a++;
                            }
                        }
                    }
                }
            }
        }
        return a;
    }

    protected void noCaseRecentlyTaken() {
        int longueur = size;
        for (int i = 0; i < size + 1; i++) {
            longueur++;
            for (int j = 0; j < longueur; j++) {
                cases[i][j].notOwned();
            }
        }
        for (int i = size + 1; i < 2 * size + 1; i++) {
            longueur--;
            for (int j = 0; j < longueur; j++) {
                cases[i][j].notOwned();
            }
        }
    }
}