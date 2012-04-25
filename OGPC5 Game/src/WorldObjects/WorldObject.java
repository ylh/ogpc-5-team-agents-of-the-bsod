/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Rect;
import Utilities.Vector2;
import java.util.ArrayList;

/**
 *
 * @author Nekel_Seyew
 */
public abstract class WorldObject {
    protected Vector2 position;
    protected int direction;//1=left, 2= right, 3=up, 4= down
    protected Image2D sprite;
    
    /**
     * Initializes an object with basic properties determining its motion and appearance
     * @param pos the position of the object (x,y)
     * @param dir the direction of the object's motion
     * @param spritePath the file path of the object's sprite
     */
        public WorldObject(Vector2 pos, int dir, String spritePath){
        this.direction=dir;
        this.position=pos;
        this.sprite=new Image2D(spritePath);
        
    }
    
    /**
    * Update method implemented by subclasses
    * @param wol List of all WorldObjects
    */
    public abstract void Update(ArrayList<WorldObject> wol);
    
    /**
     * Draw method implemented by subclasses
     * @param batch ImageCollection for drawing
     */
    public abstract void Draw(ImageCollection batch);
    
    /**
     * Gets the object's position
     * @return position of the object
     */
    public Vector2 getPosition(){
        return position;
    }
    
    /**
     * Provides a new value for the object's position
     * @param newPos the new position for the object
     */
    public void setPosition(Vector2 newPos){
        position= newPos;
    }
    
    /**
     * Gets a bounding box based on the center of the image
     * @param i2d the Image2D of the object
     * @return return the bounding box
     */
    public static Rect getRectangle(Image2D i2d){
        return new Rect((int)(i2d.getPosition().getX()-i2d.getIconWidth()/2), 
                (int)(i2d.getPosition().getY()-i2d.getIconHeight()/2), 
                i2d.getIconWidth(), i2d.getIconHeight());
    }
    
}
