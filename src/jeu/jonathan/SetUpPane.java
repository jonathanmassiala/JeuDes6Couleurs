package jeu.jonathan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class SetUpPane extends JPanel {

    private JComboBox comboSize;
    private JCheckBox check2Players = new JCheckBox("2");
    private JCheckBox check3Players = new JCheckBox("3");
    private JCheckBox check4Players = new JCheckBox("4");
    private JComboBox comboVS;
    private JComboBox comboDifficulty;
    private static int nbPlayer = 2;
    private static int sizeBoard;

    SetUpPane(){

        this.setLayout(new GridLayout(4, 2));

        JLabel choiceSize = new JLabel("Choisissez la taille du plateau :");
        String[] tab0 = new String[14];
        for (int i = 7; i < 21; i++) {
            tab0[i - 7] = Integer.toString(i) + " cases de coté";
        }
        comboSize = new JComboBox(tab0);
        comboSize.setSelectedIndex(0);
        comboSize.setPreferredSize(new Dimension(50,20));
        comboSize.addActionListener(new SizeListener());

        JLabel choiceNbPlayer = new JLabel("Choisissez le nombre de joueurs :");
        JPanel checkboxes = new JPanel();
        check2Players.setSelected(true);
        check3Players.setSelected(false);
        check4Players.setSelected(false);
        checkboxes.add(check2Players);
        checkboxes.add(check3Players);
        checkboxes.add(check4Players);

        JLabel choiceTypePlayer = new JLabel("Maintenant choisissez le type de joueurs qui va s'affronter :");
        comboVS = new JComboBox(getVSList(nbPlayer).toArray(new String[getVSList(nbPlayer).size()]));


        this.add(choiceSize);
        this.add(comboSize);
        this.add(choiceNbPlayer);
        this.add(checkboxes);
        this.add(choiceTypePlayer);
        this.add(comboVS);
    }

    private ArrayList getVSList(int nbPlayer) {
        ArrayList<String> vsList = new ArrayList<String>();
        if (nbPlayer == 2) {
            vsList.add("Humain VS Ordinateur");
            vsList.add("Humain VS Humain");
            vsList.add("Ordinateur VS Ordinateur");
        }
        if (nbPlayer == 3) {
            vsList.add("1 Joueur Humains & Deux Joueurs Ordinateurs");
            vsList.add("3 Joueurs Humains");
            vsList.add("2 Joueurs Humains & 1 Joueur Ordinateur");
            vsList.add( "2 Joueurs Ordinateurs");

        }
        if (nbPlayer == 4) {
            vsList.add("4 Joueurs Humains");
            vsList.add("1 Joueur Humains & 3 Joueurs Ordinateurs");
            vsList.add("2 Joueurs Humains & 2 Joueurs Ordinateurs");
            vsList.add("3 Joueurs Humains & 1 Joueur Ordinateur");
            vsList.add( "4 Joueurs Ordinateurs");
        }
        return vsList;
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

    public static int getSizeBoard(){
        return sizeBoard;
    }

    private class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
}
