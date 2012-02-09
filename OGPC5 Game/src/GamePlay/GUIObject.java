/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Rect;
import Utilities.Vector2;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class GUIObject {
    
    Rect boundingBox;
    Image2D sprite;
    Vector2 pos;
    int distanceToGo;
    int direction;
    
    public boolean isMoving;
    public boolean isAtTop;
    public boolean isAtBottom;
    
    
    public GUIObject(Vector2 position, String path){
        pos=position;
        sprite= new Image2D(path);
        sprite.setVector2(pos);
        boundingBox=CityGame.getRectangle(sprite);
    }
    
    public void Update(){
        boundingBox=CityGame.getRectangle(sprite);
        if(isMoving && distanceToGo>0){
            distanceToGo-=10;
            pos.dY(10*direction);
        }
        if(isMoving && distanceToGo<=0){
            if(direction>0){
                isAtBottom=true;
                isAtTop=false;
            }
            if(direction<0){
                isAtTop=true;
                isAtBottom=false;
            }
            this.stop();
        }
    }
    
    public void Draw(ImageCollection batch){
        batch.addToCollection(sprite, 10, pos);
    }
    
     public void setDistanceToGo(int i, int direction){
        distanceToGo=i;
        this.direction=sign(direction);
    }
     
     private int sign(int i){
         return Math.abs(i)/i;
     }
     
    public void start(){
        if(!isMoving){
            isMoving=true;
        }
        if(direction>0){
            isAtTop=false;
        }
        if(direction<0){
            isAtBottom=false;
        }
    }
    public void stop(){
        if(isMoving){
            isMoving=false;
        }
    }
}
