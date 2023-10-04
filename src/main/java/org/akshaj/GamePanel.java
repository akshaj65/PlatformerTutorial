package org.akshaj;

import org.akshaj.inputs.KeyBoardInputs;
import org.akshaj.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private  MouseInputs mouseInputs;
    private int xDelta=100,yDelta=100;
    public GamePanel(){
        this.mouseInputs=new MouseInputs(this);
        addKeyListener( new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value){
        this.xDelta+=value;
        repaint(); //whenever the x value change it repaints
    }
    public void changeYDelta(int value){
        this.yDelta+=value;
        repaint();

    }

    public void setRectPos(int x, int y){
        this.xDelta=x;
        this.yDelta=y;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //this should be called at start as  jobs of paintComponent is to prepare the Graphics context for painting, this is typically done by
                                // filling the Graphics context with the background color of the current component

        g.fillRect(xDelta,yDelta,200,50);

    }
}
