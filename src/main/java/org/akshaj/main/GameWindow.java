package org.akshaj.main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow( GamePanel gamePanel){
        jFrame=new JFrame();

//        jFrame.setSize(400,400);   we will be setting the panel size in GamePanel as  the frame  sets the dimension for whole window
                            //        as we need to have exact, so we will set the panel size
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);  //we want a fixed size
        jFrame.pack();  //causes window to sized to fit the preferred size and layouts of its subcomponents  .We are only adding gamePanel
        jFrame.setVisible(true);
        jFrame.addWindowFocusListener(new WindowFocusListener() {  //this is used to solve a bug where when any other window is focused some valueslike boolean
                                                                    // are true and need to be set false
            @Override
            public void windowGainedFocus(WindowEvent windowEvent) {
                gamePanel.getGame().windowsFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent windowEvent) {

            }
        });

    }
}
