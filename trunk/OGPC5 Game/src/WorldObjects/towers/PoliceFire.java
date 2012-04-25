/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.AbstractEnemy;
import GUIStuff.Tile;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class PoliceFire extends Tower{

    public PoliceFire(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=150;
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
        theGame.money+=moneyBonus;
        theGame.happiness+=happyBonus;
        
        ArrayList<Tile> s=this.getRangedTowers(theGame.tiles);
        for(Tile t : s){
            if(t instanceof Tower){
                if(((Tower)t).health<100){
                    ((Tower)t).health+=2;
                }
            }
        }
    }

    @Override
    protected Bullet setEnemyBulletHitting(AbstractEnemy e) {
        Bullet b=null;
        switch(e.getID()){
            case AbstractEnemy.SMOG:
                b=new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break; 
            case AbstractEnemy.TRASH:
                b=new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break;
            case AbstractEnemy.WATER_POLUTION:
                b=new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break;
            case AbstractEnemy.EDUCATION:
                b= new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
                break;
            case AbstractEnemy.ARSONIST:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case AbstractEnemy.CRIMINAL:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case AbstractEnemy.EARTHQUACKE:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case AbstractEnemy.FIRE:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);;
                break;
            case AbstractEnemy.FLOOD:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case AbstractEnemy.GANGS:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
                break;
            case AbstractEnemy.GRAFITTI:
                b=new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);;
                break;
            default:
                b= new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
                break;
        }
        return b;
    }
    
}
