/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Bullet;
import WorldObjects.towers.Road;
import WorldObjects.towers.Tower;
import java.util.ArrayList;

/**
 *
 * @author HAL-9000
 */
public class SuperEnemy extends AbstractEnemy{
    
    int targetX, targetY;
    int startX, startY;
    
    ArrayList<Tile> frontier;
    ArrayList<Tile> explored;
    int[][] scores;
    
    /**
     * 
     * @param Speed the speed of the enemy
     * @param Health how much health the enemy has
     * @param Armor how much armor the enemy has
     * @param pos the starting position
     * @param path the enemy's file path
     * @param t the main Tile[][] array
     * @param startR starting x value
     * @param startC starting y value
     */
    public SuperEnemy(double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t, int startR, int startC){
        super(Speed, Health, Armor, pos, path, t);
        startX=startR;
        startY=startC;
        scores= new int[t.length][t[0].length];
        frontier= new ArrayList<Tile>();
        explored= new ArrayList<Tile>();
        
        int mid= t[0].length/2;
        int lastDist=19;
        Vector2 p=new Vector2();
        for(int i=0; i<t[0].length; i++){
            if(t[0][i] instanceof Road){
                if(Math.abs(mid-i)<lastDist){
                    p=t[0][i].getPosition().clone();
                    lastDist=(int)Math.abs(mid-i);
                }
            }
        }
        targetX=(int)p.getX()/32;
        targetY=(int)p.getY()/32;
        
        prepareAStar(t);
    }
    
    /**
     * Adds main array Tiles to frontier
     * @param t Main Tile[][] array
     */
    private void prepareAStar(Tile[][] t){
        for(int i=0; i<t.length; i++){
            for(int j=0; j<t[i].length; j++){
                frontier.add(t[i][j]);
            }
        }
    }

    @Override
    public Bullet setTowerBulletHitting(Tower t) {
        return null;
    }
    
}
