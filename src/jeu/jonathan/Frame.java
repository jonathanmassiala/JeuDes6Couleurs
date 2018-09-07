package jeu.jonathan;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame(String title){
        setTitle(title);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
