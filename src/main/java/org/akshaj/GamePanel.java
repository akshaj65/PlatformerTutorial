package org.akshaj;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(){

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g); //this should be called at start as  jobs of paintComponent is to prepare the Graphics context for painting, this is typically done by
                                // filling the Graphics context with the background color of the current component

        g.fillRect(100,100,200,50);

    }
}
