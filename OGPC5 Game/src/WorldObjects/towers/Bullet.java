/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.AbstractEnemy;
import Utilities.*;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author tsutton14 and Nekel-Seyew
 */
public class Bullet extends WorldObject {
    
    Animation spriteAnimation;
    Vector2 startPos;
    WorldObject target;
    double distance;
    double damage;
    double adamage;
    double sdamage;
    double speed;
    double arc;
    /**
     * Constructor for the bullet class.
     * @param pos the starting position for the bullet
     * @param damage the amount of damage the bullet will inflict
     * @param adamage the amount of armor damage the bullet will inflict
     * @param sdamage the amount of speed damage the bullet will inflict
     * @param velocity the velocity the bullet travels at
     * @param t the Target of the bullet
     */
    public Bullet(Vector2 pos, double damage,double adamage,double sdamage, double velocity, WorldObject t){
        super(pos.clone(), -1, "Hi there");
        loadAnimation();
        this.speed=velocity;
        startPos=pos.clone();
        this.target=t;
        arc=0;
        Vector2 endPoint=t.getPosition().clone();
        
        this.distance=0;
        this.damage=damage;
        this.adamage=adamage;
        this.sdamage=sdamage;
        sprite=new Image2D("Game Resources/Sprites/particle.png");
        
    }
    
    /**
     * Alternate constructor for the bullet class
     * @param s  the file path of the Image2D
     * @param pos the starting position for the bullet
     * @param damage the amount of damage the bullet will inflict
     * @param adamage the amount of armor damage the bullet will inflict
     * @param sdamage the amount of speed damage the bullet will inflict
     * @param velocity the velocity the bullet travels at
     * @param t the Target of the bullet
     */
    public Bullet(String s, Vector2 pos, double damage,double adamage,double sdamage, double velocity, WorldObject t){
        super(pos.clone(), -1, "Hi there");
        loadAnimation();
        this.speed=velocity;
        startPos=pos.clone();
        this.target=t;
        arc=0;
        Vector2 endPoint=t.getPosition().clone();
        
        this.distance=0;
        this.damage=damage;
        this.adamage=adamage;
        this.sdamage=sdamage;
        sprite=new Image2D(s);
    }
    
    /**
     * Moves the bullet from startPos towards target
     * @param wol the ArrayList of all WorldObjects
     */
    @Override
    public void Update(ArrayList<WorldObject> wol) {
        double sx=startPos.getX();
        double sy=startPos.getY();
        double ex=target.getPosition().getX();
        double ey=target.getPosition().getY();
        this.position=new Vector2(sx+distance*(ex-sx),sy+distance*(ey-sy)-4*arc*distance*(1-distance));
        this.distance+=1/speed;
        
        
    }
    
    /**
     * Determines if the bullet has reached its target.
     * @return if reached target
     */
    public boolean HasReachedTarget(){
        return distance>1;
        //return false;
    }
    
    /**
     * Determines if the bullet hits the target and determines damage to deal
     */
    public void HitTarget(){
        AbstractEnemy e;
        Tower t;
        if (target instanceof AbstractEnemy){
            e=(AbstractEnemy)target;
            e.Hit(damage,adamage,sdamage);
            damage=0;
            adamage=0;
            sdamage=0;
        }
        else if (target instanceof Tower){
            t=(Tower)target;
            t.Hit(damage);
            damage=0;
            adamage=0;
            sdamage=0;
        }
        
         
    }
    
    /**
     * Draws the bullet
     * @param batch ImageCollection to be drawn
     */
    @Override
    public void Draw(ImageCollection batch) {
        if(spriteAnimation!=null){
            //spriteAnimation.Draw(batch);
        }
        if (distance<1){
            batch.Draw(sprite,position,100);
        }
    }
    
    /**
     * Sets the bullet's animation to a default animation of length 1
     */
    public void loadAnimation(){
        spriteAnimation= new Animation(1,1, position, 1000);
    }
    
    /**
     * Get damage
     * @return damage
     */
    public double getDamage(){
        return damage;
    }
    
    /**
     * Gets damage for armor
     * @return adamage 
     */
    public double getArmorDamage(){
        return adamage;
    }
    
    /**
     *Gets damage due to speed 
     * @return sdamage
     */
    public double getSpeedDamage(){
        return sdamage;
    }
}
