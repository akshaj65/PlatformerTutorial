package org.akshaj.utils;

import org.akshaj.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class LoadSave {

//    An atlas or sprite sheet is a large image file that contains many smaller images, called sprites
     public static final String PLAYER_ATLAS="player_sprites.png";
    public static final String LEVEL_ATLAS="outside_sprites.png";
    public static final String LEVEL_ONE_DATA ="level_one_data.png";


    public static BufferedImage getSpriteAtlas(String filename){
        BufferedImage img= null;
        InputStream is = LoadSave.class.getResourceAsStream("/active-assets/"+filename);
        try {
            img= ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
              is.close();
        } catch (IOException e) {
              throw new RuntimeException(e);
        }
        return img;
    }

    //the level_one_data.png image's each tile's red value is set according to the desired position of levelSprite array
    public static int[][] getLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img =getSpriteAtlas(LEVEL_ONE_DATA);
        for(int i=0; i<img.getHeight();i++){
            for (int j=0;j<img.getWidth();j++){
                Color color = new Color( img.getRGB(j,i));  //j and i are like x and y coordinates
                int value =color.getRed();
                if(value>48){ // 48 here is the size (12 * 4) outside_sprite  so greater may cause out of bound
                    value=0;
                }

                lvlData[i][j]=value;
            }
        }
        for(int i=0;i<lvlData.length;i++){
            System.out.println(Arrays.toString(lvlData[i]));
        }
        return lvlData;
    }
}
