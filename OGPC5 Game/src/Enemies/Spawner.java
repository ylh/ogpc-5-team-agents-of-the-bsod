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
    private Vector2 pos;
    private long timeToWait;
    private double lastTime;
    private boolean isSpawning;
    CityGame theGame;
    
    //spawning variables
    private int quota;
    private double[] thisRoundQuota;
    private int location=0;
    private double spawnLast;
    
    public Spawner(long waitTime, CityGame theGame, Vector2 loc){
        timeToWait=waitTime;
        this.theGame=theGame;
        lastTime=0;
        pos= loc;
        quota=10;
        isSpawning=false;
        thisRoundQuota=new double[11];
        for(int i = 0; i<12; i++){            
            EnemyProbabilityTable.setProbability(i, 20);
        }
    }

    public void update(){
        double time=System.currentTimeMillis();
        if(!isSpawning && time- lastTime >= timeToWait){
            isSpawning=true;
            for(int i=0; i<11; i++){
                thisRoundQuota[i]=quota*(EnemyProbabilityTable.getProbability(i)/100.0);
            }
        }
        if(isSpawning && time-spawnLast >=500){
            if(location>=thisRoundQuota.length){
                location=0;
                isSpawning=false;
                lastTime=time;//End spawning until next round
            }else if(thisRoundQuota[location]!=0){
                addEnemy(location);
                thisRoundQuota[location]-=1;
                spawnLast=time;
            }else if(thisRoundQuota[location]<=0){
                location++;
            }
        }
        
        
        
//        double time=System.currentTimeMillis();
//        double innerTimerA=System.currentTimeMillis();
//        if(time- lastTime >= timeToWait){            
//            for(int i=0; i<=11; i++){
//                double innerTimerB = System.currentTimeMillis();;
//                if (innerTimerB - innerTimerA >= 500) {
//                    double d = quota * (EnemyProbabilityTable.getProbability(i) / 100.0);
//                    for (int j = 0; j < (int) d; j++) {
//                        addEnemy(i);
//                    }
//                    innerTimerA = innerTimerB;
//                }
//            }
//            quota+=2;
//           lastTime=time;
//        }
    }
    
    private void addEnemy(int enemy){//add the given enemy at the given location.
        Enemy e = null;
        switch(enemy){
            case Enemy.ARSONIST:
                e = new Enemy(Enemy.ARSONIST, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.CRIMINAL:
                e = new Enemy(Enemy.CRIMINAL, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.EARTHQUACKE:
                e = new Enemy(Enemy.EARTHQUACKE, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.EDUCATION:
                e = new Enemy(Enemy.EDUCATION, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.FIRE:
                e = new Enemy(Enemy.FIRE, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.FLOOD:
                e = new Enemy(Enemy.FLOOD, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.GANGS:
                e = new Enemy(Enemy.GANGS, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.GENERIC:
                e = new Enemy(Enemy.GENERIC, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.GRAFITTI:
                e = new Enemy(Enemy.GRAFITTI, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.SMOG:
                e = new Enemy(Enemy.SMOG, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.TRASH:
                e = new Enemy(Enemy.TRASH, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.WATER_POLUTION:
                e = new Enemy(Enemy.WATER_POLUTION, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            default:
                e = new Enemy(1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
        }
        theGame.addToWorldObjects(e);
    }
}
