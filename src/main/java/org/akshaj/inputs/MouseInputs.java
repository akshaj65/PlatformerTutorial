package org.akshaj.inputs;

import org.akshaj.entities.Player;
import org.akshaj.gamestates.Gamestate;
import org.akshaj.main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (Gamestate.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseClicked(mouseEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseClicked(mouseEvent);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
//        if(mouseEvent.getXOnScreen())
//            gamePanel.setRectPos(mouseEvent.getX(),mouseEvent.getY());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
//        System.out.println("Mouse Moved");
    }
}
