package org.akshaj.gamestates;

import org.akshaj.main.Game;

public class State {
    protected Game game;

    public State(Game game){
     this.game=game;
    }

    public Game getGame() {
        return game;
    }
}
