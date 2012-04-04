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
            int position=0;
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
    
    private void addEnemy(int enemy, int position){//add the given enemy at the given location.
        Enemy e;
        switch(enemy){
            case Enemy.ARSONIST:
                e = new Enemy(Enemy.ARSONIST, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.CRIMINAL:
                e = new Enemy(Enemy.CRIMINAL, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.EARTHQUACKE:
                e = new Enemy(Enemy.EARTHQUACKE, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.EDUCATION:
                e = new Enemy(Enemy.EDUCATION, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.FIRE:
                e = new Enemy(Enemy.FIRE, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.FLOOD:
                e = new Enemy(Enemy.FLOOD, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.GANGS:
                e = new Enemy(Enemy.GANGS, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.GENERIC:
                e = new Enemy(Enemy.GENERIC, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.GRAFITTI:
                e = new Enemy(Enemy.GRAFITTI, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.SMOG:
                e = new Enemy(Enemy.SMOG, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.TRASH:
                e = new Enemy(Enemy.TRASH, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            case Enemy.WATER_POLUTION:
                e = new Enemy(Enemy.WATER_POLUTION, 1, 10, 0, new Vector2((position+1)*32-16,19*32-16), "Game Resources/Sprites/PlaceHolderEnemy", theGame.tiles);
                break;
            default:
                break;
        }
    }
}
