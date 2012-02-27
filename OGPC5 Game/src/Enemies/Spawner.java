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
        setting(1,i);
    }
    public void setGangs(int i){
        setting(2,i);
    }
    public void setArsonist(int i){
        setting(3,i);
    }
    public void setCriminal( int i){
        setting(4,i);
    }
    public void setTrash(int i){
        setting(5,i);
    }
    public void setWaterPolution(int i){
        setting(6,i);
    }
    public void setFire(int i){
        setting(7,i);
    }
    public void setFlood(int i){
        setting(8,i);
    }
    public void setEarthquake(int i){
        setting(9,i);
    }
    public void setPoorEducation(int i){
        setting(10,i);
    }
    
    private void setting(int type, int percentile) {
        synchronized (this) {
            if (percentile < 0) {
                enemyProbabilities[type] = enemyProbabilities[type] - percentile;
            }
            enemyProbabilities[type] = percentile;
        }
    }
    
    public void spawn(){
        double counter=0;
        double past=0;
        double odds= Math.random()*100;
        for(int t=0; t<quota; t++){
            for(int j=0; j<enemyProbabilities.length; j++){
                if(odds<=enemyProbabilities[j]){
                    makeMonstor(j);
                    past=counter;
                    counter++;
                    continue;
                }
            }
            if(counter==past){
                makeMonstor(0);
                past=counter;
                counter++;
            }
        }
    }
    
    private void makeMonstor(int i){
        if(i==1){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==2){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==3){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==4){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==5){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==6){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==7){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==8){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==9){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==10){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
        }else if(i==0){
            theGame.addToWorldObjects(new Enemy(1,20,20,pos.clone(), ""));
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
