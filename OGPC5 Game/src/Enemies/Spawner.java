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
    public void addLocation(int i, Vector2 p){
        pos[i]=p;
    }

    public void update(){
        double time=System.currentTimeMillis();
        if(time- lastTime >= timeToWait){
            double prob= Math.random()*100;
            int position=0;
            //NOT EFFICIENT. GAH!!!!!!!
            for(int i=0; i<=11; i++){
                double d=quota*(EnemyProbabilityTable.getProbability(i)/100.0);
                for(int j=0; j<(int)d; j++){
                    try {
                        while (pos[position] == null) {
                            position++;
                        }
                        addEnemy(i, position);
                        position++;
                    } catch (Exception e) {
                        position = 0;
                        if(pos[position]!=null){
                            addEnemy(i, position);
                            position++;
                        }
                    }
                }
            }
            quota+=2;
           lastTime=time;
        }
    }
    //TODO: finish this
    private void addEnemy(int enemy, int position){//add the given enemy at the given location.
        switch(enemy){
            case Enemy.ARSONIST:
                break;
            case Enemy.CRIMINAL:
                break;
            case Enemy.EARTHQUACKE:
                break;
            case Enemy.EDUCATION:
                break;
            case Enemy.FIRE:
                break;
            case Enemy.FLOOD:
                break;
            case Enemy.GANGS:
                break;
            case Enemy.GENERIC:
                break;
            case Enemy.GRAFITTI:
                break;
            case Enemy.SMOG:
                break;
            case Enemy.TRASH:
                break;
            case Enemy.WATER_POLUTION:
                break;
            default:
                break;
        }
    }
}
