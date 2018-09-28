package jeu.jonathan;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Frame extends JFrame {

    private static CardLayout cl = new CardLayout();
    static JPanel content = new JPanel();
    static String[] listContent = {"New Game Pane", "Game Pane"};
    int indice = 0;

    public Frame() throws IOException, FontFormatException {
        this.setTitle("Jeu des 6 couleurs");
        this.setSize(900, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        NewGamePane newGamePane = new NewGamePane();

        content.setLayout(cl);
        content.add(newGamePane, listContent[0]);


        this.getContentPane().add(content, BorderLayout.CENTER);

        this.setVisible(true);
    }


    public static void changePanel(int index) {
        content.add(NewGamePane.getGamePane(), listContent[1]);
        cl.show(content, listContent[index]);

    }




}
