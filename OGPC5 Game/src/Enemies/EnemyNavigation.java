package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;
import java.util.ArrayList;

/**
 *Not functional
 * @author tsutton14
 */
public class EnemyNavigation extends Thread {
    
    Tile[][] tiles;
    int goalX;
    int goalY;
    int startX, startY;
    boolean paused, destroy;
    ArrayList<ArrayList<Vector2>> pathHolder = new ArrayList<ArrayList<Vector2>>();
    
    /**
     * 
     * @param t the main Tile[][] array
     * @param navName the name of the Thread
     * @param i the target x value
     * @param j the target y value
     */
    public EnemyNavigation(Tile[][] t, String navName, int i, int j){
        super(navName);
        tiles = t;
        goalX = i;
        goalY = j;
        pathHolder = new ArrayList<ArrayList<Vector2>>();
        pathHolder.add(new ArrayList<Vector2>());
    }
    
    /**
     * Currently effectively does nothing
     */
    @Override
    public void run(){
        synchronized(this){
            System.out.println("Ran");
            System.out.println(pathHolder.get(0));
            pause();
            while(true){
                if(!paused){
                    System.out.println("Got here!");
                    if(destroy){
                        pause();
                    }
                    else{
                        pause();
                    }
                }
                else{
                    try{
                        wait();
                    }
                    catch(Exception e){
                    }
                }
            }
        }       
    }
    
    /**
     * Scores a Road based upon its distance from the goal
     * @param i current x coordinate
     * @param j current y coordinate
     * @return the favorability of a Road
     */
    private double scoreRoad(int i, int j){
        double score;
        
        score = 1/(1+Math.sqrt(Math.pow(goalX-i,2)+Math.pow(goalY-j, 2)));
        
        return score;
    }
        
    /**
     * Finds the most favorable road adjacent to the current road
     * @param i current x value
     * @param j current y value
     * @return returns the location of the highest scored road
     */
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
                        highScore = scoreRoad(i,j+1);
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
                        highScore = scoreRoad(i+1,j);
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
                        highScore = scoreRoad(i-1,j);
                        highX = i-1;
                        highY = j;
                    }
                }
            }            
        } catch (Exception e) {            
        }
        
        return new Vector2(highX,highY);
    }
    
    /**
     * Finds the best path from a given position
     * @param i current x value
     * @param j current y value
     * @return returns a list of target Road locations
     */
    public ArrayList<Vector2> findPath(int i, int j){
        ArrayList<Vector2> path = new ArrayList();
        
        int currentX = i;
        int currentY = j;
        
        path.add(new Vector2(currentX, currentY));
        
        while(currentX != goalX && currentY != goalY){
            Vector2 v = new Vector2();
            v = checkSurrounding(currentX, currentY);
            path.add(v);
            currentX = (int)v.getX();
            currentY = (int)v.getY();
        }
        
        return path;        
    }
    
    /**
     * Pauses this thread
     */
    public void pause() {
        this.paused=true;
    }

    /**
     * Recalculates all paths
     * @param i current x value
     * @param j current y value
     * @param b should be destroyed
     */
    public void recalculatePath(int i, int j, boolean b) {
        synchronized (this) {
            this.paused = false;
            this.startX = i;
            this.startY = j;
            this.destroy = b;            
            this.notify();
        }
    } 
    
    /**
     * Determines if this Thread is paused
     * @return if the Thread is paused
     */
    public boolean isPaused(){
        return this.paused;
    }

    
}
