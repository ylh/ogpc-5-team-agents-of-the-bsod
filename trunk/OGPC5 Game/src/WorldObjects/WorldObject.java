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
    
    public WorldObject(Vector2 pos, int dir, String spritePath){
        this.direction=dir;
        this.position=pos;
        this.sprite=new Image2D(spritePath);
    }
    
    public abstract void Update(ArrayList<WorldObject> wol);
    
    public abstract void Draw(ImageCollection batch);
    
    public static Rect getRectangle(Image2D i2d){
        return new Rect((int)(i2d.getPosition().getX()-i2d.getIconWidth()/2), 
                (int)(i2d.getPosition().getY()-i2d.getIconWidth()/2), 
                i2d.getIconWidth(), i2d.getIconHeight());
    }
    
}
