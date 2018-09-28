package jeu.jonathan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetUpPane extends JPanel {

    private JComboBox comboSize;
    private static JComboBox comboVS;
    private JRadioButton difficulty1;
    private JRadioButton difficulty2;
    private ButtonGroup difficultyGroup = new ButtonGroup();
    private static int difficulty = 1;
    private static int nbPlayer = 1;
    private static int sizeBoard = 7;
    private static int nbIA = 1;

    SetUpPane(){

        JLabel title = new JLabel("Paramètres de jeu");
        Font font = new Font("Calibri",Font.TRUETYPE_FONT,20);
        title.setFont(font);
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.add(title);
        titlePanel.setBorder(new EmptyBorder(20,350,0,0));
        titlePanel.setMaximumSize(new Dimension(850,100));
        //title.setBorder(new EmptyBorder(20,0,20,0));

        JPanel sizePanel = new JPanel(new GridLayout(1,2));
        sizePanel.setMaximumSize(new Dimension(850,100));
        JLabel choiceSize = new JLabel("Choisissez la taille du plateau :");
        String[] tabSize = new String[14];
        for (int i = 7; i < 21; i++) {
            tabSize[i - 7] = Integer.toString(i) + " cases de coté";
        }
        comboSize = new JComboBox(tabSize);
        comboSize.setSelectedIndex(0);
        comboSize.setMaximumSize(new Dimension(300,30));
        comboSize.setBorder(new EmptyBorder(0,0,0,50));
        comboSize.addActionListener(new SizeListener());
        sizePanel.add(choiceSize);
        sizePanel.add(comboSize);
        sizePanel.setBorder(new EmptyBorder(40,0,0,0));

        JPanel vsPanel = new JPanel(new GridLayout(1,2));
        vsPanel.setMaximumSize(new Dimension(850,100));
        JLabel choiceNbPlayer = new JLabel("Choisissez le nombre et le type des joueurs qui vont s'affronter:");
        //choiceNbPlayer.setBorder(new EmptyBorder(20,20,20,20));
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
        comboVS.setMaximumSize(new Dimension(300,30));
        comboVS.setBorder(new EmptyBorder(0,0,0,50));
        comboVS.addActionListener(new VSListener());
        vsPanel.add(choiceNbPlayer);
        vsPanel.add(comboVS);
        vsPanel.setBorder(new EmptyBorder(40,0,0,0));

        JPanel diffPanel = new JPanel(new GridLayout(1,2));
        diffPanel.setMaximumSize(new Dimension(850,100));
        JLabel choiceDifficulty = new JLabel(
                "<html><p>Choisissez le niveau de difficulté :<br>(Ignorez cette étape si tous les joueurs sont humains)</p></html>)"
        );
        //choiceDifficulty.setBorder(new EmptyBorder(20,20,20,20));
        difficulty1 = new JRadioButton("Niveau 1 (choix aléatoire)");
        difficulty2 = new JRadioButton("Niveau 2");
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
        difficultyGroup.add(difficulty1);
        difficultyGroup.add(difficulty2);
        diffPanel.add(choiceDifficulty);
        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.add(difficulty1);
        buttonGroupPanel.add(difficulty2);
        diffPanel.add(buttonGroupPanel);
        diffPanel.setBorder(new EmptyBorder(40,0,0,0));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(titlePanel);
        this.add(sizePanel);
        this.add(vsPanel);
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
