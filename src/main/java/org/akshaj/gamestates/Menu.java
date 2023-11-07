package org.akshaj.gamestates;

import org.akshaj.main.Game;
import org.akshaj.ui.MenuButton;
import org.akshaj.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements IStatemethods{

    private MenuButton[] menuButton = new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth,menuHeight;
    public Menu(Game game) {
        super(game);
        loadMenuBackground();
        loadMenuButton();
    }

    private void loadMenuBackground() {
        backgroundImg= LoadSave.getSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth=(int)(backgroundImg.getWidth()*Game.SCALE);
        menuHeight=(int)(backgroundImg.getHeight()*Game.SCALE);
        menuX= Game.GAME_WIDTH/2 -menuWidth/2;
        menuY= (int)(45* Game.SCALE);
    }

    private void loadMenuButton() {
        menuButton[0]= new MenuButton(Game.GAME_WIDTH /2 ,(int)(150*Game.SCALE),0,Gamestate.PLAYING);
        menuButton[1]= new MenuButton(Game.GAME_WIDTH /2 ,(int)(220*Game.SCALE),1,Gamestate.OPTIONS);
        menuButton[2]= new MenuButton(Game.GAME_WIDTH /2 ,(int)(290*Game.SCALE),2,Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : menuButton){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,menuX,menuY,menuWidth,menuHeight,null);
        for(MenuButton mb : menuButton){
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for( MenuButton mb : menuButton){
            if(isIn(e,mb)){
               mb.setMousePressed(true);
               break;  //only the first pressed it then breaks
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for( MenuButton mb : menuButton){
            if(isIn(e,mb)){
                if(mb.isMousePressed())
                    mb.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for( MenuButton mb : menuButton){ //first set all false
            mb.setMouseOver(false);
        }
        for( MenuButton mb : menuButton){ //then check if it's still moving
            if(isIn(e,mb)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    private void resetButtons() {
        for( MenuButton mb : menuButton){
            mb.resetBools();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state=Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
