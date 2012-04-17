/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;
import WorldObjects.towers.Tower;
import java.util.ArrayList;

/**
 *
 * @author HAL-9000
 */
public class SuperEnemy extends Enemy{
    
    int targetX, targetY;
    int startX, startY;
    
    ArrayList<Tile> frontier;
    ArrayList<Tile> explored;
    int[][] scores;
    
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
    
    private void prepareAStar(Tile[][] t){
        for(int i=0; i<t.length; i++){
            for(int j=0; j<t[i].length; j++){
                frontier.add(t[i][j]);
            }
        }
    }
    
}
