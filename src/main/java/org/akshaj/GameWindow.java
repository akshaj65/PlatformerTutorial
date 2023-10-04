package org.akshaj;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow( GamePanel gamePanel){
        jFrame=new JFrame();

        jFrame.setSize(400,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);// this should be at the bottom

    }
}