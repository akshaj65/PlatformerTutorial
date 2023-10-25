package org.akshaj.levels;

import org.akshaj.main.Game;
import org.akshaj.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.akshaj.main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game){
        importOutsideSprites();
        this.game=game;
        levelOne= new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite= new BufferedImage[48];  //12 * 4
        for (int i=0;i<4;i++){
            for (int j=0;j<12;j++){
                int index = i*12 + j;
                levelSprite[index] = img.getSubimage(j*32,i*32,32,32);
            }
        }
    }

    public void draw(Graphics g){
        for (int i=0;i<Game.TILES_IN_HEIGHT;i++){
            for (int j=0;j<Game.TILES_IN_WIDTH;j++){
                int index = levelOne.getSpriteIndex(i,j);
                g.drawImage(levelSprite[index],( j * TILES_SIZE ),( i * TILES_SIZE ),TILES_SIZE,TILES_SIZE,null);
            }
        }


    }

    public void update() {

    }

    public  Level getCurrentLevel() {
        return levelOne;
    }
}
