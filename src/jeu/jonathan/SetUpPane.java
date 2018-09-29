package jeu.jonathan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetUpPane extends JPanel {

    private JComboBox comboSize;
    private static JComboBox comboVS;
    private static int difficulty = 1;
    private static int nbPlayer = 1;
    private static int sizeBoard = 7;
    private static int nbIA = 1;

    SetUpPane(){
        this.setBackground(new Color(23,126,137));
        JLabel title = new JLabel("<html><font color='white'>Paramètres de jeu</font></html>");
        Font font = new Font("Calibri",Font.TRUETYPE_FONT,20);
        title.setFont(font);
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBackground(new Color(23,126,137));
        titlePanel.add(title);
        titlePanel.setBorder(new EmptyBorder(20,350,0,0));
        titlePanel.setMaximumSize(new Dimension(850,100));
        //title.setBorder(new EmptyBorder(20,0,20,0));


        Font fontSetUp = new Font("Calibri",Font.TRUETYPE_FONT,16);
        JPanel sizePanel = new JPanel(new GridLayout(1,2));
        sizePanel.setMaximumSize(new Dimension(850,100));
        JLabel choiceSize = new JLabel("<html><font color='white'>Choisissez la taille du plateau :</font></html>");
        choiceSize.setFont(fontSetUp);
        String[] tabSize = new String[14];
        for (int i = 7; i < 21; i++) {
            tabSize[i - 7] = Integer.toString(i) + " cases de coté";
        }
        comboSize = new JComboBox(tabSize);
        comboSize.setSelectedIndex(0);
        comboSize.setMaximumSize(new Dimension(300,30));
        comboSize.setBorder(new EmptyBorder(0,0,0,50));
        comboSize.addActionListener(new SizeListener());
        sizePanel.setBackground(new Color(23,126,137));
        sizePanel.add(choiceSize);
        sizePanel.add(comboSize);
        sizePanel.setBorder(new EmptyBorder(40,0,0,0));

        JPanel vsPanel = new JPanel(new GridLayout(1,2));
        vsPanel.setMaximumSize(new Dimension(850,100));
        JLabel choiceNbPlayer = new JLabel("<html><font color='white'>Choisissez le nombre et le type des joueurs qui vont s'affronter :</font></html>");
        choiceNbPlayer.setFont(fontSetUp);
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
        vsPanel.setBackground(new Color(23,126,137));
        vsPanel.add(choiceNbPlayer);
        vsPanel.add(comboVS);
        vsPanel.setBorder(new EmptyBorder(40,0,0,0));

        JPanel diffPanel = new JPanel(new GridLayout(1,2));
        diffPanel.setMaximumSize(new Dimension(850,100));
        JLabel choiceDifficulty = new JLabel(
                "<html><p><font color='white'>Choisissez le niveau de difficulté :<br>(Ignorez cette étape si tous les joueurs sont humains)</font></p></html>)"
        );
        choiceDifficulty.setFont(fontSetUp);
        JRadioButton difficulty1 = new JRadioButton("<html><font color='white'>Niveau 1 (choix aléatoire)</font></html>");
        JRadioButton difficulty2 = new JRadioButton("<html><font color='white'>Niveau 2</font></html>");
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
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(difficulty1);
        difficultyGroup.add(difficulty2);
        diffPanel.add(choiceDifficulty);
        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setBackground(new Color(23,126,137));
        buttonGroupPanel.add(difficulty1);
        buttonGroupPanel.add(difficulty2);
        diffPanel.setBackground(new Color(23,126,137));
        diffPanel.add(buttonGroupPanel);
        diffPanel.setBorder(new EmptyBorder(40,0,20,0));

        JPanel testPanel = new JPanel(new GridLayout(1,2));
        testPanel.setMaximumSize(new Dimension(850,100));
        testPanel.setBackground(new Color(255,200,87));
        JLabel test = new JLabel(
                "<html><p><font color=323031>Quick test :<br>pour lancer une partie avec 4 IAs de niveau 2 <br>sur un plateau de 10 cases de cotés<br>" +
                        "cliquez sur TEST puis cliquez sur PLAY</font></p></html>"
        );
        test.setBorder(new EmptyBorder(0,10,0,0));
        JButton playTest = new JButton("<html><font color='white'>TEST</font></html>");
        playTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizeBoard = 10;
                nbIA = 4;
                nbPlayer = 0;
                difficulty = 2;
            }
        });
        playTest.setBackground(new Color(8,76,97));
        playTest.setOpaque(true);
        playTest.setBorderPainted(false);
        testPanel.add(test);
        testPanel.add(playTest);
        //testPanel.setBorder(new EmptyBorder(40,0,0,0));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(titlePanel);
        this.add(testPanel);
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
                    nbIA = 2;
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
