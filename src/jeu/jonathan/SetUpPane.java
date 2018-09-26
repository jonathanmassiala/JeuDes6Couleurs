package jeu.jonathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetUpPane extends JPanel {

    private JComboBox comboSize;
    private static JComboBox comboVS;
    private static int nbPlayer = 1;
    private static int sizeBoard = 7;
    private static int nbIA = 1;

    SetUpPane(){

        this.setLayout(new GridLayout(4, 2));

        JLabel choiceSize = new JLabel("Choisissez la taille du plateau :");
        String[] tabSize = new String[14];
        for (int i = 7; i < 21; i++) {
            tabSize[i - 7] = Integer.toString(i) + " cases de coté";
        }
        comboSize = new JComboBox(tabSize);
        comboSize.setSelectedIndex(0);
        comboSize.setPreferredSize(new Dimension(50,20));
        comboSize.addActionListener(new SizeListener());

        JLabel choiceNbPlayer = new JLabel("Choisissez le nombre et le type des joueurs qui vont s'affronter:");
        String[] tabVS;
        tabVS = new String[]{
                "2 Joueurs : Humain VS Ordinateur",
                "2 Joueurs : Humain VS Humain",
                "2 Joueurs : Ordinateur VS Ordinateur",
                "3 Joueurs : Humains",
                "3 Joueurs : Ordinateurs",
                "3 Joueurs : 1 Humains VS 2 Ordinateurs",
                "3 Joueurs : 2 Humains VS 1 Ordinateur",
                "4 Joueurs : Humains",
                "4 Joueurs : Ordinateurs",
                "4 Joueurs : 1 Humains VS 3 Ordinateurs",
                "4 Joueurs : 2 Humains VS 2 Ordinateurs",
                "4 Joueurs : 3 Humains VS 1 Ordinateur"
        };
        comboVS = new JComboBox(tabVS);
        comboVS.setSelectedIndex(0);
        comboVS.setPreferredSize(new Dimension(50,30));
        comboVS.addActionListener(new VSListener());



        this.add(choiceSize);
        this.add(comboSize);
        this.add(choiceNbPlayer);
        this.add(comboVS);
    }

    private class SizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String string = String.valueOf(comboSize.getSelectedItem());
            int firstChar = Integer.parseInt(string.substring(0,1));
            if (firstChar == 7 || firstChar == 8 || firstChar == 9){
                sizeBoard = Integer.parseInt(string.substring(0,1));
            }
            else {
                sizeBoard = Integer.parseInt(string.substring(0,2));
            }
        }
    }

    static int getSizeBoard(){
        return sizeBoard;
    }

    private class VSListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(comboVS.getSelectedIndex());
            switch (comboVS.getSelectedIndex()) {
                case 0:
                    nbPlayer = 1;
                    nbIA = 1;
                    break;
                case 1:
                    nbPlayer = 2;
                    nbIA = 0;
                    break;
                case 2:
                    nbPlayer = 0;
                    nbIA = 1;
                    break;
                case 3:
                    nbPlayer = 3;
                    nbIA = 0;
                    break;
                case 4:
                    nbPlayer = 0;
                    nbIA = 3;
                    break;
                case 5:
                    nbPlayer = 1;
                    nbIA = 2;
                    break;
                case 6:
                    nbPlayer = 2;
                    nbIA = 1;
                    break;
                case 7:
                    nbPlayer = 4;
                    nbIA = 0;
                    break;
                case 8:
                    nbPlayer = 0;
                    nbIA = 4;
                    break;
                case 9:
                    nbPlayer = 1;
                    nbIA = 3;
                    break;
                case 10:
                    nbPlayer = 2;
                    nbIA = 2;
                    break;
                case 11:
                    nbPlayer = 3;
                    nbIA = 1;
                    break;
            }
        }
    }

    static int getNbPlayer(){
        return nbPlayer;
    }

    static int getNbIA(){
        return nbIA;
    }
}
