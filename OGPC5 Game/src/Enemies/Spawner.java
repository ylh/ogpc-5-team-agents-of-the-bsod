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
public class Spawner extends Thread{
    private Vector2 pos;
    private int quota;
    private long timeToWait;
    CityGame theGame;
    
    int[] enemyProbabilities;
    
    public Spawner(Vector2 position, long waitTime, int quota, CityGame theGame){
        this.pos=position;
        timeToWait=waitTime;
        this.quota=quota;
        this.theGame=theGame;
        enemyProbabilities=new int[11];
        start();
    }
    
    public void setSmog(int i){
        enemyProbabilities[1]=1;
    }
    public void setGangs(int i){
        enemyProbabilities[2]=1;
    }
    public void setArsonist(int i){
        enemyProbabilities[3]=i;
    }
    public void setCriminal( int i){
        enemyProbabilities[4]=i;
    }
    public void setTrash(int i){
        enemyProbabilities[5]=i;
    }
    public void setWaterPolution(int i){
        enemyProbabilities[6]=i;
    }
    public void setFire(int i){
        enemyProbabilities[7]=i;
    }
    public void setFlood(int i){
        enemyProbabilities[8]=i;
    }
    public void setEarthquake(int i){
        enemyProbabilities[9]=i;
    }
    public void setPoorEducation(int i){
        enemyProbabilities[10]=i;
    }
    
    public void spawn(){
        double counter=0;
        double odds= Math.random()*100;
        for(int t=0; t<quota; t++){
            for(int j=0; j<enemyProbabilities.length; j++){
                if(odds<=enemyProbabilities[j]){
                    
                }
            }
        }
    }
    
    @Override
    public void run(){
        while(true){
            synchronized(this){
                try {
                    this.wait(timeToWait);
                    spawn();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
