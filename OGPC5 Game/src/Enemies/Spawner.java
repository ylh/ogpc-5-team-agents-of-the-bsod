/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Utilities.Vector2;
import ogpc5.game.CityGame;

/**
 *
 * @author Nekel-Seyew
 */
public class Spawner {
    private Vector2[] pos;
    private int quota;
    private long timeToWait;
    private double lastTime;
    CityGame theGame;
    EnemyProbabilityTable probs;
    
    public Spawner(long waitTime, CityGame theGame){
        timeToWait=waitTime;
        this.theGame=theGame;
        lastTime=0;
        pos= new Vector2[632/32];
        quota=10;
    }

    public void update(){
        double time=System.currentTimeMillis();
        if(time- lastTime >= timeToWait){
            
           lastTime=time;
        }
    }
}
