/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Utilities.KeyBoard;
import Utilities.SoundFile;
import Utilities.Vector2;
import java.awt.event.KeyEvent;
import ogpc5.game.CityGame;

/**
 * Creates enemies at the beginning of the track
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
    
    /**
     * 
     * @param waitTime how long between waves
     * @param theGame the current game running
     * @param loc position of Spawner
     */
    public Spawner(long waitTime, CityGame theGame, Vector2 loc){
        firstUpdate=true;
        timeToWait=waitTime;
        this.theGame=theGame;
        lastTime=0;
        pos= loc;
        quota=10;
        isSpawning=false;
        thisRoundQuota=new double[12];
        for(int i = 0; i<12; i++){            
            EnemyProbabilityTable.setProbability(i, 20);
        }
    }

    /**
     * Determines how many of each enemy to create and when
     */
    public void update(KeyBoard k){
        double time=System.currentTimeMillis();
        if(firstUpdate){
            this.runFirstUpdate();
            firstUpdate=false;
        }
        if(!isSpawning && time- lastTime >= timeToWait){
            isSpawning=true;
            new SoundFile("Game Resources/Sound/ominous.wav",1).start();
            for(int i=0; i<12; i++){
                thisRoundQuota[i]=quota*(EnemyProbabilityTable.getProbability(i)/100.0);
            }
        }
        if(!isSpawning && k.isKeyDown(KeyEvent.VK_SPACE)){
            isSpawning=true;
            new SoundFile("Game Resources/Sound/ominous.wav",1).start();
            for(int i=0; i<12; i++){
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
    
    /**
     * Sets the initial time to properly arrange waves
     */
    private void runFirstUpdate(){
        lastTime=System.currentTimeMillis();
    }
    
    /**
     * Determines how long until the next wave
     * @return 
     */
    public double getRemainingTime(){
        if(isSpawning){
            return 0;
        }
        return timeToWait-(System.currentTimeMillis()-lastTime);
    }
    
    /**
     * Adds a given type of enemy at a specified location (the Spawner) location
     * @param enemy the enemy type 
     */
    private void addEnemy(int enemy){
        System.out.println("Enemy Type: " + enemy);
        AbstractEnemy e = null;
        switch(enemy){
            case AbstractEnemy.ARSONIST:
                e = new Enemy(AbstractEnemy.ARSONIST, 1, 1000, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Enemies/Arsonist/arsonist0.png", theGame.tiles);
                break;
            case AbstractEnemy.CRIMINAL:
                e = new Enemy(AbstractEnemy.CRIMINAL, 1, 1000, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.EARTHQUACKE:
                e = new Enemy(AbstractEnemy.EARTHQUACKE, 1, 50, 0, pos.clone(), "Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVD1-1.png", theGame.tiles);
                break;
            case AbstractEnemy.EDUCATION:
                e = new Enemy(AbstractEnemy.EDUCATION, 1, 50, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.FIRE:
                e = new Enemy(AbstractEnemy.FIRE, 1, 50, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Fire/fire0.png", theGame.tiles);
                break;
            case AbstractEnemy.FLOOD:
                e = new Enemy(AbstractEnemy.FLOOD, 1, 50, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Flood/flood0.png", theGame.tiles);
                break;
            case AbstractEnemy.GANGS:
                e = new Enemy(AbstractEnemy.GANGS, 1, 50, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.GENERIC:
                e = new Enemy(AbstractEnemy.GENERIC, 1, 50, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.GRAFITTI:
                e = new Enemy(AbstractEnemy.GRAFITTI, 1, 50, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Grafitti/graffiti0.png", theGame.tiles);
                break;
            case AbstractEnemy.SMOG:
                e = new Enemy(AbstractEnemy.SMOG, 1, 50, 0, pos.clone(), "Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png", theGame.tiles);
                break;
            case AbstractEnemy.TRASH:
                e = new Enemy(AbstractEnemy.TRASH, 1, 50, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.WATER_POLUTION:
                e = new Enemy(AbstractEnemy.WATER_POLUTION, 1, 50, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            default:
                e = new Enemy(AbstractEnemy.GENERIC, 1, 50, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
        }
        theGame.addToWorldObjects(e);
    }
    
    private void StartGameLoad(){
        EnemyProbabilityTable.setProbability(AbstractEnemy.ARSONIST, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.CRIMINAL, 50);
        EnemyProbabilityTable.setProbability(AbstractEnemy.EARTHQUACKE, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.EDUCATION, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.FIRE, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.FLOOD, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.GANGS, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.GRAFITTI, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.SMOG, 0);
        EnemyProbabilityTable.setProbability(AbstractEnemy.TRASH, 50);
        EnemyProbabilityTable.setProbability(AbstractEnemy.WATER_POLUTION, 0);
    }
}
