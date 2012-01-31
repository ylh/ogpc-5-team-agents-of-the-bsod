/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class Tower extends WorldObject {
    
    protected int bonus;
    protected double strength;  //Base damage dealt
    protected double health;
    
    /*
     * Coordinates for bounding box
     */
    protected int bx1;
    protected int bx2;
    protected int by1;
    protected int by2;

    public Tower(Vector2 pos, int dir, String spritePath){
        super(pos, dir, spritePath);
    }
    
    @Override
    public void Update(ArrayList<WorldObject> wol) {        
        
    }

    @Override
    public void Draw(ImageCollection batch) {        
        
    }
    
    public int getBonus(){
        return bonus;
    }
    
    public void setBonus(int b){
        bonus = b;
    }
    
    public double getStrength(){
        return strength;
    }
    
    public void setStrength(double s){
        strength = s;
    }
    
    public double getHealth(){
        return health;
    }
    
    public void setHealth(double h){
        health = h;
    }
    
    protected void setBoundingBox(){
        bx1 = (int) ((int)sprite.getPosition().getX()-(.5*sprite.getIconWidth()));
        bx2 = (int) ((int)sprite.getPosition().getX()+(.5*sprite.getIconWidth()));
        by1 = (int) ((int)sprite.getPosition().getY()+(.5*sprite.getIconHeight()));
        by2 = (int) ((int)sprite.getPosition().getY()-(.5*sprite.getIconHeight()));
    }
}
