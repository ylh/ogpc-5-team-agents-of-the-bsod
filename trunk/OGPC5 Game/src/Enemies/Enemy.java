/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

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
     */
    public Enemy(double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t) {
        super(pos, 1, path);
        speed = Speed;
        targetPos=new Vector2(pos.getX(),pos.getY()-32);
        health = Health;
        armor = Armor;
        danger = 0;
        tiles = t;
        score=10;
        id=Enemy.GENERIC;
        System.out.println("Enemy Created");
//        setEnemyPath();
        pathCreator2 = new EnemyNavigation2(t);
        pathCreator2.update(position);
    }
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
    public void Hit(double damage,double adamage,double sdamage)
    {
        speed/=sdamage;
        armor=Math.max(0,armor-adamage);
        health-=Math.max(0,damage-armor);
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
        //we'll want to change this in the future when we add roads
        if (snapped()){
            pathCreator2.update(position);
            int decision=pathCreator2.decide(position);
            Vector2 q= new Vector2();
            if (decision==0) q= new Vector2(0,-32);
            if (decision==1) q= new Vector2(32,0);
            if (decision==2) q= new Vector2(0,32);
            if (decision==3) q= new Vector2(-32,0);
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
    }
    /**
     * Draws the enemy at it's current location
     * @param batch the ImageCollection used by the Game
     */
    @Override
    public void Draw(ImageCollection batch) {
//        batch.addToCollection(sprite,1,position);
        batch.Draw(sprite, position, 5);
    }
    
    public void setEnemyPath() {                 
        pathCreator.start();        
    }
    
//    public void updatePath(EnemyNavigation en) {
//        pathCreator = en;
//        path = pathCreator.getPath();
//        System.out.println(path);     
//    }
    public void updatePath2(Tile[][] t){
        pathCreator2.reset(t);
    }
    
    public void followPath(int i){
        if(i < path.size()){            
            for(int counter = i; counter <path.size(); counter++){
                move((int)(path.get(i).getX()*32-16),(int)(path.get(i).getY()*32-16));
            }
        }
    }
    
    private void move(int i, int j){
        int goalX = (i+1)*32-16;
        int goalY = (j+1)*32-16;
        while(position.getX() != goalX && position.getY() != goalY){
            if(position.getX()>goalX){
                position.setX(position.getX()-1);
            }
            else if (position.getX()<goalX){
                position.setX(position.getX()+1);
            }
            if(position.getY()>goalY){
                position.setY(position.getY()-1);
            }
            else if (position.getY()<goalY){
                position.setY(position.getY()+1);
            }
        }
    }
    public boolean snapped(){
        if (position.getX()==targetPos.getX() && position.getY()==targetPos.getY()){
            return true;
        }
        else return false;
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
