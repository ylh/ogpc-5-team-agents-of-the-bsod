/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class PoliceFire extends Tower{

    public PoliceFire(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=1500;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        ani = new Animation(1,10, position,100);
        ani.addCell("Game Resources/Sprites/August/Police Station 32x32.png");
    }

    @Override
    protected void loadStats() {
        damage=1000;
        health=500;
        range=80*Math.sqrt(2);
        adamage=100;
        projspeed=10;//not correct
        speed=200;//not done
        this.moneyBonus=-2;
        this.happyBonus=1;
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
        ani.Draw(batch);
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        Bullet b=null;
        switch(e.getID()){
            case Enemy.SMOG:
                b=new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break; 
            case Enemy.TRASH:
                b=new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break;
            case Enemy.WATER_POLUTION:
                b=new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break;
            case Enemy.EDUCATION:
                b= new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break;
            case Enemy.ARSONIST:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case Enemy.CRIMINAL:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case Enemy.EARTHQUACKE:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case Enemy.FIRE:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);;
                break;
            case Enemy.FLOOD:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case Enemy.GANGS:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case Enemy.GRAFITTI:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);;
                break;
            default:
                b= new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
                break;
        }
        return b;
    }
    
}
