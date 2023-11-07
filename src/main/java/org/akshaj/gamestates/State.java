package org.akshaj.gamestates;

import org.akshaj.main.Game;
import org.akshaj.ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;

    public State(Game game){
     this.game=game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }

    public Game getGame() {
        return game;
    }
}
