/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class taylorEnemy extends Enemy{
    
    Animation a;
    Animation earthquake1, earthquake2, earthquake3, earthquake4, smog1, smog2, smog3, smog4;
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
        initializeAnimations();
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
        initializeAnimations();
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
                a = new Animation(2, 5, this.position, 300);
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
                a = new Animation(19, 5, this.position, 50);
                for(int i = 0; i <= 18; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Grafitti/graffiti";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case Enemy.SMOG:
                a = new Animation(2, 5, this.position, 300);
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png");
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-2.png");
                break;
            case Enemy.WATER_POLUTION:
                a = new Animation(9, 5, this.position, 300);
                for(int i = 0; i <= 8; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/WaterPolution/waterPollution";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case Enemy.GANGS:
                a = new Animation(1, 5, this.position, Integer.MAX_VALUE);
                a.addCell("Game Resources/Sprites/Yestin/gang.png");
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
        alterAnimationDirection();
        danger+=speed;
        healthDisplay--;
        armorDisplay++;
    }
        
    private void alterAnimationDirection() {
        if (id == Enemy.EARTHQUACKE) {
            if(dir == 0){
                a = earthquake1;
            }
            if(dir == 1){
                a = earthquake3;
            }
            if(dir == 2){
                a = earthquake2;
            }
            if(dir == 3){
                a = earthquake4;
            }
        }else if(id == Enemy.SMOG){
            if(dir == 0){
                a = smog1;
            }
            if(dir == 1){
                a = smog3;
            }
            if(dir == 2){
                a = smog2;
            }
            if(dir == 3){
                a = smog4;
            }
        }
    }
    
    private void initializeAnimations(){
            earthquake1 = new Animation(2, 5, this.position, 300);
            earthquake1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-1.png");
            earthquake1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-2.png");
            earthquake2 = new Animation(2, 5, this.position, 300);
            earthquake2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVD1-1.png");
            earthquake2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVD1-2.png");
            earthquake3 = new Animation(2, 5, this.position, 300);
            earthquake3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVR1-1.png");
            earthquake3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVR1-2.png");
            earthquake4 = new Animation(2, 5, this.position, 300);
            earthquake4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVL1-1.png");
            earthquake4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVL1-2.png");
            smog1 = new Animation(2, 5, this.position, 300);
            smog1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVU1-1.png");
            smog1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVU1-2.png");
            smog2 = new Animation(2, 5, this.position, 300);
            smog2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVD1-1.png");
            smog2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVD1-2.png");
            smog3 = new Animation(2, 5, this.position, 300);
            smog3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogR1-1.png");
            smog3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogR1-2.png");
            smog4 = new Animation(2, 5, this.position, 300);
            smog4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png");
            smog4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-2.png");
    }
    
}
