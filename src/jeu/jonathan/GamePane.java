package jeu.jonathan;

import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel {

    private JPanel stddrawPanel;


    GamePane(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        stddrawPanel = StdDraw.getNewPanel();
        this.add(stddrawPanel,BorderLayout.CENTER);
        stddrawPanel.setSize(this.getWidth(),this.getHeight());
        stddrawPanel.setBackground(Color.WHITE);
    }


}
