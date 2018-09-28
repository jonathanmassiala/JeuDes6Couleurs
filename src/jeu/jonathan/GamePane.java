package jeu.jonathan;

import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel {

    private JPanel stddrawPanel;


    GamePane(){
        this.setLayout(new BorderLayout());
        stddrawPanel = StdDraw.getNewPanel();
        this.add(stddrawPanel,BorderLayout.CENTER);
        stddrawPanel.setSize(this.getWidth(),this.getHeight());

        JButton newGameButton = new JButton("Nouvelle Partie");
        newGameButton.addActionListener(e -> {
            //stddrawPanel.repaint();
            Frame.changePanel(0);
        });

        this.add(newGameButton, BorderLayout.SOUTH);


    }


}
