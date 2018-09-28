package jeu.jonathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetUpPane extends JPanel {

    private JComboBox comboSize;
    private static JComboBox comboVS;
    private JRadioButton difficulty1;
    private JRadioButton difficulty2;
    private ButtonGroup diffiultyGroup = new ButtonGroup();
    private static int difficulty = 1;
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

        JLabel choiceDifficulty = new JLabel(
                "Choisissez le niveau de difficulté : (Ignorez cette étape si tous les joueurs qui s'affrontent sont humais)"
        );
        JPanel diffPanel = new JPanel();
        difficulty1 = new JRadioButton("1 (choix de case aléatoire)");
        difficulty2 = new JRadioButton("2");
        difficulty1.setSelected(true);
        difficulty1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 1;
            }
        });
        difficulty2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 2;
            }
        });
        diffiultyGroup.add(difficulty1);
        diffiultyGroup.add(difficulty2);
        diffPanel.add(difficulty1);
        diffPanel.add(difficulty2);


        this.add(choiceSize);
        this.add(comboSize);
        this.add(choiceNbPlayer);
        this.add(comboVS);
        this.add(choiceDifficulty);
        this.add(diffPanel);
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
            System.out.println("case :" + comboVS.getSelectedIndex());
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

    public static int getDifficulty() {
        return difficulty;
    }
}
