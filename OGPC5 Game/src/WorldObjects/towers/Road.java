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
    private static final int ROAD_UP_RIGHT = 0;
    private static final int ROAD_UP_LEFT= 1;
    private static final int ROAD_DOWN_RIGHT = 2;
    private static final int ROAD_DOWN_LEFT = 3;
    
    
    public Road(Vector2 pos, String spritePath){
        super(pos);   
        sprite = new Image2D(spritePath);
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {        
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
        Vector2 dp=position.clone();
        dp.dX(16);        
        dp.dY(16);
        batch.addToCollection(sprite, 20, dp);
    }
    
    
    public static boolean isTileRoad(Tile[][] t,int i, int j){  //Used by Road to determine the shape it should take
        if(t[i][j] instanceof Road){
            return true;
        }
        return false;
    }
    
    public static int setRoadShape(Tile[][] t,int i, int j){
        int roadIndex=0;
        if(isRoadDown(t, i, j) && !isRoadUp(t, i, j) && !isRoadRight(t, i, j)){
            roadIndex = ROAD_UP_RIGHT;
        }
        else if(isRoadDown(t, i, j) && !isRoadUp(t, i, j) && !isRoadLeft(t, i, j)){
            roadIndex = ROAD_UP_LEFT;
        }
        else if(isRoadUp(t, i, j) && !isRoadDown(t, i, j) && !isRoadRight(t, i, j)){
            roadIndex = ROAD_DOWN_RIGHT;
        }
        else if(isRoadUp(t, i, j) && !isRoadDown(t, i, j) && !isRoadLeft(t, i, j)){
            roadIndex = ROAD_DOWN_LEFT;
        }
        else{
            roadIndex = 4;
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

    private static boolean isRoadLeft(Tile[][] t, int i, int j) {  //Checks if road is left
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
    
    public static String returnSprite(int i){  //Returns proper image according to orientation
        switch(i){
            case 0: return "Game Resources/Sprites/UpRight.png";
            case 1: return "Game Resources/Sprites/UpLeft.png";
            case 2: return "Game Resources/Sprites/DownRight.png";
            case 3: return "Game Resources/Sprites/DownLeft.png";
            default: return "Game Resources/Sprites/BasicRoad.png";                
        }
    }
}