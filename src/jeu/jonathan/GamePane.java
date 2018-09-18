package jeu.jonathan;

import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel {

    private String color = "BLEU";


    GamePane(){
        setBackground(color);

        this.add(StdDraw.getPanel());


    }


    private void setBackground(String color) {
        if(this.color.equals("BLEU")){
            this.setBackground(Color.BLUE);
        }
        if(this.color.equals("VERT")) {
            this.setBackground(Color.GREEN);
        }

        if(this.color.equals("ROUGE")) {
            this.setBackground(Color.red);
        }
        if(this.color.equals("NOIR")){
            setBackground(Color.BLACK);
        }

    }

    public void setColor(String color){
        this.color = color;
        setBackground(color);
    }

    public void startGame(){
        Game.start();
    }



}
