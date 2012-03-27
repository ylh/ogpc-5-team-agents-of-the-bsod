/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author pcowal15, Nekel_Seyew
 */
public class Enemy extends WorldObject {
    /**
     * The Speed the enemy travels at
     */
    double speed;
    /**
     * The health of the enemy
     */
    double health;
    /**
     * The armor rating of the enemy
     */
    double armor;
    /**
     * The danger rating of each enemy
     */
    double danger;
    /**
     * The type ID for the enemy
     */
    int id;
    
    /**
     * The Constructor for the enemy class. Because enemies all act similarly, They all have similar inputs. 
     * This is the Generic Constructor
     * @param Speed how fast the enemy moves per turn
     * @param Health how much health the enemy has
     * @param Armor the armor rating of the enemy
     * @param pos the starting position of the enemy
     * @param path the String path for the enemy's default static sprite
     */
    public Enemy(double Speed, double Health, double Armor, Vector2 pos, String path) {
        super(pos, 1, path);
        speed = Speed;
        health = Health;
        armor = Armor;
        danger = 0;
        id=Enemy.GENERIC;
    }
    public Enemy(int type, double Speed, double Health, double Armor, Vector2 pos, String path){
        this(Speed, Health, Armor, pos, path);
        id=type;
    }
    /**
     * The method called on the enemy when a projectile hits it
     * @param damage the amount of damage health takes
     * @param adamage the amount of damage the armor rating takes
     * @param sdamage the amount of damage the speed rating takes
     */
    public void Hit(double damage,double adamage,double sdamage)
    {
        speed/=sdamage;
        armor=Math.max(0,armor-adamage);
        health-=Math.max(0,damage-armor);
    }
    /**
     * Figures out if the health is below 1, and therefor, dead
     * @return if the enemy is dead or not
     */
    public boolean isDead()
    {
        return health<1;
    }
    /**
     * Used for determining the danger rating of this instance of the enemy
     * @return danger rating
     */
    public double getDanger(){
        return danger;
    }
    /**
     * Updates the Enemy by recalculating it's danger rating based on the enemy's speed and moves the location of the enemy
     * @param wol the list of the world's objects
     */
    @Override
    public void Update(ArrayList<WorldObject> wol) {
        //we'll want to change this in the future when we add roads
        position.add(new Vector2(0,-speed));
        danger+=speed;
    }
    /**
     * Draws the enemy at it's current location
     * @param batch the ImageCollection used by the Game
     */
    @Override
    public void Draw(ImageCollection batch) {
//        batch.addToCollection(sprite,1,position);
        batch.Draw(sprite, position, 1);
    }
    
    /**
     * The Static ID for a Generic Enemy
     */
    public static final int GENERIC=0;
    /**
     * The Static ID for a Smog Enemy
     */
    public static final int SMOG=1;
    /**
     * The Static ID for a Gang Enemy
     */
    public static final int GANGS=2;
    /**
     * The Static ID for an Arsonist Enemy
     */
    public static final int ARSONIST=3;
    /**
     * The Static ID for a Criminal Enemy
     */
    public static final int CRIMINAL=4;
    /**
     * The Static ID for a Trash Buildup enemy
     */
    public static final int TRASH=5;
    /**
     * The Static ID for a Water Polution Enemy
     */
    public static final int WATER_POLUTION=6;
    /**
     * The Static ID for a Fire Enemy
     */
    public static final int FIRE=7;
    /**
     * The Static ID for a Flood Enemy
     */
    public static final int FLOOD=8;
    /**
     * The Static ID for an Earthquake Enemy
     */
    public static final int EARTHQUACKE=9;
    /**
     * The Static ID for a Grafitti Enemy
     */
    public static final int GRAFITTI=10;
    /**
     * The Static ID for a Poor Education Enemy
     */
    public static final int EDUCATION=11;
}
