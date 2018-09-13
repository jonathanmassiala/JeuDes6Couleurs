package jeu.jonathan;

import javax.swing.*;
import java.awt.Color;

public class Frame extends JFrame {

    public Frame() {
        this.setTitle("Jeu des 6 couleurs");
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        JPanel panel = new JPanel();
        panel.setBackground(Color.orange);
        this.setContentPane(new Pane());
    }
}
