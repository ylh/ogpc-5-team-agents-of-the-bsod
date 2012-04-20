/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author pcowal15, Nekel_Seyew, tsutton14
 */
public class Enemy extends WorldObject {
    Vector2 targetPos;
    /**
     * The Speed the enemy travels at
     */
    double speed;
    /**
     * The health of the enemy
     */
    double health;
    /**
     * The maximum health of the enemy
     */
    double maxhealth;
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
     * The score the enemy is worth
     */
    int score;
    
    int dir;
    int healthDisplay;
    
    Tile[][] tiles; //Map from which the enemy works
    
    ArrayList<Vector2> path; //Enemy path
    
    public EnemyNavigation pathCreator;    
    public EnemyNavigation2 pathCreator2;
    /**
     * The Constructor for the enemy class. Because enemies all act similarly, They all have similar inputs. 
     * This is the Generic Constructor
     * @param Speed how fast the enemy moves per turn
     * @param Health how much health the enemy has
     * @param Armor the armor rating of the enemy
     * @param pos the starting position of the enemy
     * @param path the String path for the enemy's default static sprite
     * @param t the main Tile[][] array
     */
    public Enemy(double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t) {
        super(pos, 1, path);
        speed = Speed;
        targetPos=new Vector2(pos.getX(),pos.getY()-32);
        health = Health;
        maxhealth= Health;
        armor = Armor;
        danger = 0;
        tiles = t;
        score=10;
        id=Enemy.GENERIC;
        System.out.println("Enemy Created");
//        setEnemyPath();
        pathCreator2 = new EnemyNavigation2(t);
        pathCreator2.update(position);
        healthDisplay=0;
    }
    
    /**
     * 
     * @param type designates the kind of enemy
     * @param Speed how fast the enemy moves per turn
     * @param Health how much health the enemy has
     * @param Armor the armor rating of the enemy
     * @param pos the starting position of the enemy
     * @param path the String path for the enemy's default static sprite
     * @param t the main Tile[][] array
     */
    public Enemy(int type, double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t){
        this(Speed, Health, Armor, pos, path, t);
        id=type;
        System.out.println("Enemy Created");
    }
    /**
     * The method called on the enemy when a projectile hits it
     * @param damage the amount of damage health takes
     * @param adamage the amount of damage the armor rating takes
     * @param sdamage the amount of damage the speed rating takes
     */
    public void Hit(double damage,double adamage,double sdamage){
        speed/=(sdamage+1);
        armor=Math.max(0,armor-adamage);
        health-=Math.max(0,damage-armor);
        healthDisplay=20;
        
    }
    /**
     * gets the ID of the ENEMY
     * @return this instance's id
     */
    public int getID(){
        return id;
    }
    /**
     * Used for obtaining the score of the enemy
     * @return the score the enemy is worth
     */
    public int getScore(){
        return score;
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
        if (snapped()){
            pathCreator2.update(position);
            dir=pathCreator2.decide(position,dir);
            Vector2 q= new Vector2();
            if (dir==0) q= new Vector2(0,-32);
            if (dir==1) q= new Vector2(32,0);
            if (dir==2) q= new Vector2(0,32);
            if (dir==3) q= new Vector2(-32,0);
            q.add(position);
            targetPos=q;
        }
        if (position.getX()<targetPos.getX()){
            position.setX(Math.min(position.getX()+speed, targetPos.getX()));
        }
        else{
            position.setX(Math.max(position.getX()-speed, targetPos.getX()));
        }
        if (position.getY()<targetPos.getY()){
            position.setY(Math.min(position.getY()+speed, targetPos.getY()));
        }
        else{
            position.setY(Math.max(position.getY()-speed, targetPos.getY()));
        }
        danger+=speed;
        healthDisplay--;
    }
    /**
     * Draws the enemy at it's current location
     * @param batch the ImageCollection used by the Game
     */
    @Override
    public void Draw(ImageCollection batch) {
//        batch.addToCollection(sprite,1,position);
        batch.Draw(sprite, position, 5);
        if (healthDisplay>0){
            int h=(int)(health/maxhealth*13);
            Image2D healthBar=new Image2D("Game Resources/Sprites/Status/Health/health"+h+".png");
            batch.Draw(healthBar, position,6);
        }
    }
    
    /**
     * Resets pathCreator2's visited array to reflect the main Tile[][] array
     * @param t the main Tile[][] array
     */
    public void updatePath2(Tile[][] t){
        pathCreator2.reset(t);
    }
    
    /**
     * Determines if a unit's position is equal to its target position
     * @return whether or not the unit's position has reached the target position
     */
    public boolean snapped(){
        if (position.getX()==targetPos.getX() && position.getY()==targetPos.getY()){
            return true;
        }
        else return false;
    }
    
    /**
     * Rewards the player for the destruction of an enemy
     * @param theGame the game that is running
     */
    public void die(CityGame theGame){
        theGame.score+=this.score;
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
