package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class EnemyNavigation extends Thread {
    
    Tile[][] tiles;
    int goalX;
    int goalY;
    int spawnX;
    int spawnY;
    ArrayList<Vector2> route = new ArrayList<Vector2>();
    
    public EnemyNavigation(Tile[][] t, String navName, int i, int j){
        super(navName);
        tiles = t;
        goalX = i;
        goalY = j;
    }
    
    @Override
    public void run(){
        synchronized(this){ 
        }       
    }
    
    private double scoreRoad(int i, int j){
        double score;
        
        score = 1/(1+Math.sqrt(Math.pow(goalX-i,2)+Math.pow(goalY-j, 2)));
        
        return score;
    }
        
    
    private Vector2 checkSurrounding(int i, int j){                
        int highX = 0;
        int highY = 0;
        double highScore = 0;
        
    try {
            if (tiles[i][j - 1] instanceof Tile) {
                if (tiles[i][j - 1] instanceof Road) {
                    if(scoreRoad(i,j-1)>=highScore){
                        highScore = scoreRoad(i,j-1);
                        highX = i;
                        highY = j-1;
                    }
                }
            }            
        } catch (Exception e) {            
        }
        try {
            if (tiles[i][j + 1] instanceof Tile) {
                if (tiles[i][j + 1] instanceof Road) {
                    if(scoreRoad(i,j+1)>=highScore){
                        highScore = scoreRoad(i,j-1);
                        highX = i;
                        highY = j+1;
                    }
                }
            }            
        } catch (Exception e) {            
        }  
        try {
            if (tiles[i+1][j] instanceof Tile) {
                if (tiles[i+1][j] instanceof Road) {
                    if(scoreRoad(i+1,j)>=highScore){
                        highScore = scoreRoad(i,j-1);
                        highX = i+1;
                        highY = j;
                    }
                }
            }            
        } catch (Exception e) {            
        }  
        try {
            if (tiles[i-1][j] instanceof Tile) {
                if (tiles[i-1][j] instanceof Road) {
                    if(scoreRoad(i-1,j)>=highScore){
                        highScore = scoreRoad(i,j-1);
                        highX = i-1;
                        highY = j;
                    }
                }
            }            
        } catch (Exception e) {            
        }
        
        return new Vector2(highX,highY);
    }
    
    private ArrayList<Vector2> findPath(int i, int j){
        ArrayList<Vector2> path = new ArrayList();
        
        int currentX = i;
        int currentY = j;
        
        while(currentX != goalX && currentY != goalY){
            Vector2 v = new Vector2();
            v = checkSurrounding(currentX, currentY);
            path.add(v);
            currentX = (int)v.getX();
            currentY = (int)v.getY();
        }
        
        return path;        
    }
    
    public void setPath(int startX, int startY){
        route = findPath(startX, startY);
    }
    
    public ArrayList getPath(){
        synchronized(this){            
        return route;
        }
    }

    
}
