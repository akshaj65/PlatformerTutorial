package org.akshaj.utils;

import org.akshaj.main.Game;

public class HelperMethods {
//    public static boolean canMoveHere(float x ,float y, int width,int height,int[][] lvlData){
    public static boolean canMoveHere(float x ,float y, float width,float height,int[][] lvlData){
       if (!isSolid(x,y,lvlData))
           if (!isSolid(x+width,y+height,lvlData))
               if (!isSolid(x+width,y,lvlData))
                   if (!isSolid(x,y+height,lvlData))
                        return true;
        return false;
    }
    
    private static boolean  isSolid(float x, float y, int[][] lvlData){
        if (x<0 || x>= Game.GAME_WIDTH){
            return true;
        }
        if (y<0 || y>= Game.GAME_HEIGHT){
            return true;
        }
        float xIndex = x /Game.TILES_SIZE;
        float yIndex = y /Game.TILES_SIZE;

        int value =lvlData[(int) yIndex][(int) xIndex];
        //11 pos is the sprite id which is transparent
        return value != 11;
    }
}
