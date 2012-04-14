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
    boolean firstUpdate;
    
    //spawning variables
    private int quota;
    private double[] thisRoundQuota;
    private int location=0;
    private double spawnLast;
    
    public Spawner(long waitTime, CityGame theGame, Vector2 loc){
        firstUpdate=true;
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
        if(firstUpdate){
            this.runFirstUpdate();
            firstUpdate=false;
        }
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
    }
    
    private void runFirstUpdate(){
        lastTime=System.currentTimeMillis();
    }
    
    public double getRemainingTime(){
        if(isSpawning){
            return 0;
        }
        return timeToWait-(System.currentTimeMillis()-lastTime);
    }
    
    private void addEnemy(int enemy){//add the given enemy at the given location.
        Enemy e = null;
        switch(enemy){
            case Enemy.ARSONIST:
                e = new taylorEnemy(Enemy.ARSONIST, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.CRIMINAL:
                e = new taylorEnemy(Enemy.CRIMINAL, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.EARTHQUACKE:
                e = new taylorEnemy(Enemy.EARTHQUACKE, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.EDUCATION:
                e = new taylorEnemy(Enemy.EDUCATION, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.FIRE:
                e = new taylorEnemy(Enemy.FIRE, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.FLOOD:
                e = new taylorEnemy(Enemy.FLOOD, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.GANGS:
                e = new taylorEnemy(Enemy.GANGS, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.GENERIC:
                e = new taylorEnemy(Enemy.GENERIC, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.GRAFITTI:
                e = new taylorEnemy(Enemy.GRAFITTI, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.SMOG:
                e = new taylorEnemy(Enemy.SMOG, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.TRASH:
                e = new taylorEnemy(Enemy.TRASH, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case Enemy.WATER_POLUTION:
                e = new taylorEnemy(Enemy.WATER_POLUTION, 1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            default:
                e = new taylorEnemy(1, 10, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
        }
        theGame.addToWorldObjects(e);
    }
}
