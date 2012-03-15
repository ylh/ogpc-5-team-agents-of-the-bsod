/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import GUIStuff.Tile;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Utilities.Mouse;
import ogpc5.game.CityGame;

/**
 *
 * @author tsutton14
 */
public class Road extends Tile {
    
    protected Mouse mouse = new Mouse();
    protected CityGame city = new CityGame(); 
    protected Vector2 spritePos;
    private static final int ROAD_UP_RIGHT = 0;
    private static final int ROAD_UP_LEFT= 1;
    private static final int ROAD_DOWN_RIGHT = 2;
    private static final int ROAD_DOWN_LEFT = 3;
    private static final int ROAD_T_RIGHT = 4;
    private static final int ROAD_OMNISECTION = 5;
    private static final int ROAD_T_LEFT = 6;
    private static final int ROAD_T_DOWN = 7;
    private static final int ROAD_T_UP = 8;
    private static final int ROAD_HORIZONTAL = 9;
    
    
    public Road(Vector2 pos, String spritePath){
        super(pos);   
        sprite = new Image2D(spritePath);
        spritePos=position.clone();
        spritePos.dX(16);
        spritePos.dY(16);
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {        
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
        // dp=position.clone();
//        dp.dX(16);        
//        dp.dY(16);
//        batch.addToCollection(sprite, 20, dp);
        batch.Draw(sprite, spritePos, 20);
    }
    
    
    public static boolean isTileRoad(Tile[][] t,int i, int j){  //Used by Road to determine the shape it should take
        if(t[i][j] instanceof Road){
            return true;
        }
        return false;
    }
    
    public static int setRoadShape(Tile[][] tiles,int i, int j){
        int roadIndex = 0;
        if(isRoadDown(tiles, i, j) && isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_OMNISECTION;
        }
        else if(isRoadDown(tiles, i, j) && isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && !isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_T_RIGHT;
        }
        else if(isRoadDown(tiles, i, j) && isRoadUp(tiles, i, j) && !isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_T_LEFT;
        }
        else if(isRoadDown(tiles, i, j) && !isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_T_DOWN;
        }
        else if(isRoadDown(tiles, i, j) && !isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && !isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_UP_RIGHT;
        }
        else if(isRoadDown(tiles, i, j) && !isRoadUp(tiles, i, j) && !isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_UP_LEFT;
        }
        else if(!isRoadDown(tiles, i, j) && isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_T_UP;
        }
        else if(!isRoadDown(tiles, i, j) && isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && !isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_DOWN_RIGHT;
        }
        else if(!isRoadDown(tiles, i, j) && isRoadUp(tiles, i, j) && !isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_DOWN_LEFT;
        }
        else if(!isRoadDown(tiles, i, j) && !isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_HORIZONTAL;
        }
        else if(!isRoadDown(tiles, i, j) && !isRoadUp(tiles, i, j) && isRoadRight(tiles, i, j) && !isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_HORIZONTAL;
        }
        else if(!isRoadDown(tiles, i, j) && !isRoadUp(tiles, i, j) && !isRoadRight(tiles, i, j) && isRoadLeft(tiles,i,j)){
            roadIndex = ROAD_HORIZONTAL;
        }
        else{
            roadIndex = 10;
        }
        
        return roadIndex;
    }

    private static boolean isRoadUp(Tile[][] t, int i, int j) {  //Checks if road is above
        try {
            if (t[i][j - 1] instanceof Tile) {
                if (t[i][j - 1] instanceof Road) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isRoadDown(Tile[][] t, int i, int j) {  //Checks if road is above
        try {
            if (t[i][j + 1] instanceof Tile) {
                if (t[i][j + 1] instanceof Road) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isRoadRight(Tile[][] t, int i, int j) {  //Checks if road is right
        try {
            if (t[i + 1][j] instanceof Tile) {
                if (t[i + 1][j] instanceof Road) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isRoadLeft(Tile[][] t, int i, int j) {  //Checks if road is left
        try {
            if (t[i - 1][j] instanceof Tile) {
                if (t[i - 1][j] instanceof Road) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static String returnSprite(int i){  //Returns proper image according to orientation
        switch(i){
            case 0: return "Game Resources/Sprites/CurvedRoadRight.png";
            case 1: return "Game Resources/Sprites/CurvedRoadLeft.png";
            case 2: return "Game Resources/Sprites/CurvedRoadRightDown.png";
            case 3: return "Game Resources/Sprites/CurvedRoadLeftDown.png";
            case 4: return "Game Resources/Sprites/TRight.png";
            case 5: return "Game Resources/Sprites/OmniSection.png";
            case 6: return "Game Resources/Sprites/TLeft.png";
            case 7: return "Game Resources/Sprites/TDown.png";
            case 8: return "Game Resources/Sprites/TUp.png";
            case 9: return "Game Resources/Sprites/BasicHorizontal.png";
            default: return "Game Resources/Sprites/BasicRoad.png";                
        }
    }
    
    public static void setNeighbors(Tile[][] t, int i, int j){        
        try {
            if (t[i][j - 1] instanceof Tile) {
                if (t[i][j - 1] instanceof Road) {
                    t[i][j-1] = new Road(new Vector2(i*32,(j-1)*32),Road.returnSprite(Road.setRoadShape(t,i,j-1)));
                }
            }            
        } catch (Exception e) {            
        }
        try {
            if (t[i][j + 1] instanceof Tile) {
                if (t[i][j + 1] instanceof Road) {
                    t[i][j+1] = new Road(new Vector2(i*32,(j+1)*32),Road.returnSprite(Road.setRoadShape(t,i,j+1)));
                }
            }            
        } catch (Exception e) {            
        }  
        try {
            if (t[i+1][j] instanceof Tile) {
                if (t[i+1][j] instanceof Road) {
                    t[i+1][j] = new Road(new Vector2((i+1)*32,j*32),Road.returnSprite(Road.setRoadShape(t,i+1,j)));
                }
            }            
        } catch (Exception e) {            
        }  
        try {
            if (t[i-1][j] instanceof Tile) {
                if (t[i-1][j] instanceof Road) {
                    t[i-1][j] = new Road(new Vector2((i-1)*32,j*32),Road.returnSprite(Road.setRoadShape(t,i-1,j)));
                }
            }            
        } catch (Exception e) {            
        }  
    }
}