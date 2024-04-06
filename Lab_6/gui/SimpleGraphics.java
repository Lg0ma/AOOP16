package gui;

import javax.swing.*;
import java.awt.*;

public class SimpleGraphics extends JFrame{


    JPanel panel;
    JLabel labelWelcome;


    public SimpleGraphics(){

        super();
        setTitle("Chess Game");
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        labelWelcome = new JLabel("Welcome to Chessgame!");

        panel.add(labelWelcome);

        add(panel);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String args[]){
        new SimpleGraphics();
    }

    
}

