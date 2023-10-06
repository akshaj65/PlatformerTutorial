package org.akshaj;

import org.akshaj.inputs.KeyBoardInputs;
import org.akshaj.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {

    private  MouseInputs mouseInputs;
    private float xDelta=100,yDelta=100;

    private float xDir= 1f,yDir=1f;
    private  Random random;

    private  Color color = new Color(150,130,139);

    private ArrayList<Rect> rects = new ArrayList<>();

    public GamePanel(){
        random=new Random();
        this.mouseInputs=new MouseInputs(this);
        addKeyListener( new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value){
        this.xDelta+=value;
    }
    public void changeYDelta(int value){
        this.yDelta+=value;
    }

    public void setRectPos(int x, int y){
        this.xDelta=x;
        this.yDelta=y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //this should be called at start as  jobs of paintComponent is to prepare the Graphics context for painting, this is typically done by
                                // filling the Graphics context with the background color of the current component

        //Temp
        for (Rect rect :rects){
            rect.updateRect();
            rect.draw(g);
        }

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta,(int)yDelta,200,50);

    }

    private void updateRectangle() {
        xDelta+=xDir;
        if(xDelta>400 || xDelta<0){
            xDir*=-1;
            color=getRandColor();
        }

        yDelta+=yDir;
        if(yDelta>400 || yDelta<0){
            yDir*=-1;
            color=getRandColor();
        }


    }

    private Color getRandColor() {
        int red = random.nextInt(0,255);
        int green =random.nextInt(0,255);
        int blue = random.nextInt(0,255);

        return new Color(red,green,blue);
    }

    public void spawnRect(int x ,int y){
        rects.add(new Rect(x,y));
    }

    private class Rect{
        int x,y,w,h;
        int xDir=1,yDir=1;
        private Color color;
        Rect(int x,int y){
            this.x=x;
            this.y=y;
            w= random.nextInt(50);
            h=w;
            color=newColor();
        }

        private Color newColor() {
            return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        }

        public void updateRect() {
            this.x+= xDir;
            if(x+w>400 || x<0){
                xDir*=-1;
                color=newColor();
            }

            this.y+=yDir;
            if(y+h>400 || y<0){
                yDir*=-1;
                color=newColor();
            }

        }

        void draw(Graphics g){
            g.setColor(color);
            g.fillRect(x,y,w,h);
        }
    }
}
