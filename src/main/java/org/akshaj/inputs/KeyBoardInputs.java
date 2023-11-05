package org.akshaj.inputs;


import org.akshaj.gamestates.Gamestate;
import org.akshaj.main.GamePanel;
import org.akshaj.utils.Constants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.akshaj.utils.Constants.*;

public class KeyBoardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (Gamestate.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().keyPressed(keyEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().keyPressed(keyEvent);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (Gamestate.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().keyReleased(keyEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().keyReleased(keyEvent);
            }
        }

    }
}
