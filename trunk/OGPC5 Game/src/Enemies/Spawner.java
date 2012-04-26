/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Utilities.KeyBoard;
import Utilities.SoundFile;
import Utilities.Vector2;
import WorldObjects.towers.Factory;
import WorldObjects.towers.GreenBelt;
import WorldObjects.towers.House;
import WorldObjects.towers.Monument;
import WorldObjects.towers.PoliceFire;
import WorldObjects.towers.RecyclingCenter;
import WorldObjects.towers.School;
import WorldObjects.towers.Store;
import WorldObjects.towers.Tower;
import WorldObjects.towers.WaterPurification;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
    private double scaleFactor=1;
    
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
        StartGameLoad();
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
            for(int n=0; n<quota;){
                for(int i=0; i<12; i++){
                    if (Math.random()<EnemyProbabilityTable.getProbability(i)/100.0){
                        thisRoundQuota[i]++;
                        n++;
                    }
                }
            }
        }
        if(!isSpawning && k.isKeyDown(KeyEvent.VK_SPACE)){
            isSpawning=true;
            new SoundFile("Game Resources/Sound/ominous.wav",1).start();
            for(int n=0; n<quota;){
                for(int i=0; i<12; i++){
                    if (Math.random()<EnemyProbabilityTable.getProbability(i)/100.0){
                        thisRoundQuota[i]++;
                        n++;
                    }
                }
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
                e = new Enemy(AbstractEnemy.ARSONIST, 1, 1000*scaleFactor, 2*scaleFactor, pos.clone(), "Game Resources/Sprites/SamSprites/Enemies/Arsonist/arsonist0.png", theGame.tiles);
                break;
            case AbstractEnemy.CRIMINAL:
                e = new Enemy(AbstractEnemy.CRIMINAL, 2, 1000*scaleFactor, 2*scaleFactor, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.EARTHQUACKE:
                e = new Enemy(AbstractEnemy.EARTHQUACKE, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVD1-1.png", theGame.tiles);
                break;
            case AbstractEnemy.EDUCATION:
                e = new Enemy(AbstractEnemy.EDUCATION, 1, 50*scaleFactor, 20*scaleFactor, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.FIRE:
                e = new Enemy(AbstractEnemy.FIRE, 3, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Fire/fire0.png", theGame.tiles);
                break;
            case AbstractEnemy.FLOOD:
                e = new Enemy(AbstractEnemy.FLOOD, 2, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Flood/flood0.png", theGame.tiles);
                break;
            case AbstractEnemy.GANGS:
                e = new Enemy(AbstractEnemy.GANGS, 1, 50*scaleFactor, 15*scaleFactor, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.GENERIC:
                e = new Enemy(AbstractEnemy.GENERIC, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.GRAFITTI:
                e = new Enemy(AbstractEnemy.GRAFITTI, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/SamSprites/Grafitti/graffiti0.png", theGame.tiles);
                break;
            case AbstractEnemy.SMOG:
                e = new Enemy(AbstractEnemy.SMOG, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png", theGame.tiles);
                break;
            case AbstractEnemy.TRASH:
                e = new Enemy(AbstractEnemy.TRASH, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            case AbstractEnemy.WATER_POLUTION:
                e = new Enemy(AbstractEnemy.WATER_POLUTION, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
                break;
            default:
                e = new Enemy(AbstractEnemy.GENERIC, 1, 50*scaleFactor, 0, pos.clone(), "Game Resources/Sprites/PlaceHolderEnemy.png", theGame.tiles);
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
    
    public void setSpawnProbabilities(int score,ArrayList<Tower> towers){
        quota=(int)(score/100)+2;
        scaleFactor=1+score/2000;
        int factories=0;
        int parks=0;
        int houses=0;
        int monuments=0;
        int policefire=0;
        int recycling=0;
        int schools=0;
        int stores=0;
        int water=0;
        
        int total=0;
        for(Tower t: towers){
            if(t instanceof Factory){
                factories++;
            }
            if(t instanceof GreenBelt){
                parks++;
            }
            if(t instanceof House){
                houses++;
            }
            if(t instanceof Monument){
                monuments++;
            }
            if(t instanceof PoliceFire){
                policefire++;
            }
            if(t instanceof RecyclingCenter){
                recycling++;
            }
            if(t instanceof School){
                schools++;
            }
            if(t instanceof Store){
                stores++;
            }
            if(t instanceof WaterPurification){
                water++;
            }
        }
        total=factories+parks+houses+monuments+policefire+recycling+schools+stores+water;
        if (total!=0){
            
            
            EnemyProbabilityTable.setProbability(AbstractEnemy.EARTHQUACKE, total);
            EnemyProbabilityTable.setProbability(AbstractEnemy.EDUCATION, total*8/(schools*2+1));
            EnemyProbabilityTable.setProbability(AbstractEnemy.FIRE, total*6+parks+factories);
            EnemyProbabilityTable.setProbability(AbstractEnemy.FLOOD, 5+total*5);
            EnemyProbabilityTable.setProbability(AbstractEnemy.GRAFITTI, total*10/(schools+1));
            EnemyProbabilityTable.setProbability(AbstractEnemy.SMOG, total*5+factories*15);
            EnemyProbabilityTable.setProbability(AbstractEnemy.TRASH, (total*5+factories*15)/(recycling*2+1));
            EnemyProbabilityTable.setProbability(AbstractEnemy.WATER_POLUTION, (total*3+factories*20)/(water*3+1));
            if (score>500){
                EnemyProbabilityTable.setProbability(AbstractEnemy.GANGS, stores*15/(schools+1));
            }
            if (score>1000){
                EnemyProbabilityTable.setProbability(AbstractEnemy.CRIMINAL, (total*3+stores*5)/(policefire+factories+1));
            }
            if (score>2500){
                EnemyProbabilityTable.setProbability(AbstractEnemy.ARSONIST, total*3/(policefire+1));
            }
            
        }
        else{
            EnemyProbabilityTable.setProbability(AbstractEnemy.ARSONIST, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.CRIMINAL, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.EARTHQUACKE, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.EDUCATION, 100);
            EnemyProbabilityTable.setProbability(AbstractEnemy.FIRE, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.FLOOD, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.GANGS, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.GRAFITTI, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.SMOG, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.TRASH, 0);
            EnemyProbabilityTable.setProbability(AbstractEnemy.WATER_POLUTION, 0);
        }
    }
}
