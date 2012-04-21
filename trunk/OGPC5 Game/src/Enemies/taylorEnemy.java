/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;

/**
 *
 * @author kyle
 */
public class taylorEnemy extends Enemy{
    
    Animation a;    
    /**
     * 
     * @param Speed enemy speed
     * @param Health how much health the enemy has
     * @param Armor how much armor the enemy has
     * @param pos the position of the enemy
     * @param path the file path of the enemy
     * @param t the main Tile[][] array
     */
    public taylorEnemy(double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t) {
        super(Speed, Health, Armor, pos, path, t);
        speed = Speed;
        targetPos=new Vector2(pos.getX(),pos.getY()-32);
        health = Health;
        armor = Armor;
        danger = 0;
        tiles = t;
        score=10;
        id=Enemy.GENERIC;
        System.out.println("Enemy Created");
        chooseAnimation();
    }
    
    /**
     * 
     * @param type the kind of enemy
     * @param Speed enemy speed
     * @param Health how much health the enemy has
     * @param Armor how much armor the enemy has
     * @param pos the position of the enemy
     * @param path the file path of the enemy
     * @param t the main Tile[][] array
     */
    public taylorEnemy(int type, double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t){
        this(Speed, Health, Armor, pos, path, t);
        id=type;
        System.out.println("Enemy Created");
        chooseAnimation();
    }
    
    /**
     * Attempts to access a nonexistent Thread to set the enemy's path
     * @param e the existing (so to speak) navigation Thread
     */
    public void setEnemyPath(EnemyNavigation e) {
        synchronized (this) {
            try {                
                System.out.println("Setting");
                e.notify();
                path = e.findPath(((int) position.getX()) / 32, ((int) position.getY()) / 32);
            } catch (Exception d) {
                System.out.println("Failed setting");
            }
        }        
    }
    
    /**
     * Follows a given path
     * @param i starting point on the path
     */
    public void followPath(int i){
        if(i < path.size()){            
            for(int counter = i; counter <path.size(); counter++){
                move((int)(path.get(i).getX()*32-16),(int)(path.get(i).getY()*32-16));
            }
        }
    }
    
    /**
     * Moves towards a target Road
     * @param i target Road x value
     * @param j target Road y value
     */
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
    
    private void chooseAnimation(){
        switch(id){
            case Enemy.ARSONIST: 
                a = new Animation(8, 5, this.position, 500);
                for(int i = 0; i <= 7; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Arsonist/arsonist";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case Enemy.EARTHQUACKE:
                a = new Animation(2, 5, this.position, 500);
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-1.png");
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-2.png");
                break;
            case Enemy.FIRE:
                a = new Animation(4, 5, this.position, 500);
                for(int i = 0; i <= 3; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Fire/fire";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case Enemy.FLOOD:
                a = new Animation(10, 5, this.position, 500);
                for(int i = 0; i <= 9; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Flood/flood";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case Enemy.GRAFITTI:
                a = new Animation(19, 5, this.position, 200);
                for(int i = 0; i <= 18; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Grafitti/graffiti";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case Enemy.SMOG:
                a = new Animation(2, 5, this.position, 500);
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png");
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-2.png");
                break;
            default:
                a = new Animation(1, 5, this.position, Integer.MAX_VALUE);
                a.addCell("Game Resources/Sprites/PlaceHolderEnemy.png");
        }
    }
    
    @Override
    public void Draw(ImageCollection batch){
        a.Draw(batch);
    }
    
    
    
}
