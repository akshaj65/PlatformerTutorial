package org.akshaj.utils;

import org.akshaj.main.Game;

import java.awt.geom.Rectangle2D;

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

    // gets the x pos next to wall
    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x /Game.TILES_SIZE); //calculating which tile we are on     ex: (764/64)= 11.93..
        int tileXPos = currentTile * Game.TILES_SIZE; //this will give starting x pos of the tile in px.  ex: (11*64)= 704px

        if(xSpeed>0){ //if it's colliding right
            int offsetX =(int) (Game.TILES_SIZE - hitbox.width);  // player's hitbox width is smaller than the tile_Size  (64-40)=24
            return  tileXPos + offsetX - 1 ; //(704+24-1)  -1 is to have no gap b/w player and object

        }else{
            //it's colliding left
            //then we just need the current tile's start index  in pixels
            return tileXPos;
        }
    }

    //gets yPos of roof/floor of the tile
    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        int tileYPos = currentTile * Game.TILES_SIZE;
        if(airSpeed > 0){
            //falling - touching floor
            int yOffset = (int)( Game.TILES_SIZE - hitbox.height) ;
            return tileYPos + yOffset - 1;
        }else {
            //jumping
            return  tileYPos;
        }
    }
    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        //check the pixel below bottom left and bottom right
        if (!isSolid(hitbox.x, hitbox.y+ hitbox.height + 1, lvlData)) //+1 makes the difference
            if(!isSolid(hitbox.x+ hitbox.width, hitbox.y + hitbox.height+1, lvlData))
                return false;

        return true;
//        return lvlData[currentTileX][currentTileY]!=11;
    }
}
