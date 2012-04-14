/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;

/**
 *
 * @author kyle
 */
public class taylorEnemy extends Enemy{
    
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
//        setEnemyPath();
//        pathCreator2 = new EnemyNavigation2(t);
//        pathCreator2.update(position);
    }
    public taylorEnemy(int type, double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t){
        this(Speed, Health, Armor, pos, path, t);
        id=type;
        System.out.println("Enemy Created");
    }
    
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
    
    @Override
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
    
    
    
}
