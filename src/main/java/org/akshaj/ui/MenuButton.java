package org.akshaj.ui;

import org.akshaj.gamestates.Gamestate;
import static org.akshaj.utils.Constants.Ui.Buttons.*;
import org.akshaj.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos, yPos, rowIndex,index;
    private int xOffsetCenter = B_WIDTH / 2;
    private Gamestate gamestate;
    private boolean mouseOver,mousePressed;
    private Rectangle bounds;
    BufferedImage[] buffImages;

    public MenuButton(int xPos, int yPos, int rowIndex,Gamestate gamestate){
        this.xPos=xPos;  //xpos is the center of thw window
        this.yPos=yPos;
        this.rowIndex=rowIndex;
        this.gamestate=gamestate;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter,yPos,B_WIDTH,B_HEIGHT); // we have the rectangle the same as button
    }

    private void loadImgs() {
        buffImages= new BufferedImage[3];
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTON);
        for(int i=0;i<3;i++){
            buffImages[i]= img.getSubimage(i * B_WIDTH_DEFAULT,rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g){
//        g.drawRect(xPos - xOffsetCenter,yPos,B_WIDTH,B_HEIGHT);
        g.drawImage(buffImages[index],xPos -xOffsetCenter  ,yPos,B_WIDTH,B_HEIGHT,null); // we don't need the button to start at center
    }

    public void update(){
        index=0;
        if(mouseOver)
            index=1;
        if(mousePressed)
            index=2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public  void applyGamestate(){
        Gamestate.state=gamestate;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void resetBools(){
        mouseOver=false;
        mousePressed=false;
    }
}
