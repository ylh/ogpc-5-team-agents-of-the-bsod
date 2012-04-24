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
public class School extends Tower{

    public School(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=70;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        
    }

    @Override
    protected void loadStats() {
        damage=15;
        health=250;
        range=112*Math.sqrt(2);
        adamage=5;
        projspeed=5;//not correct
        speed=4;//not done
        this.moneyBonus=-3;
        this.happyBonus=2;
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        theGame.money+=moneyBonus;
        theGame.happiness+=happyBonus;
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        String s="Game Resources/Sprites/Bullets/apple.png";
        if(e.getID()==Enemy.GANGS){
            return new Bullet(s,position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
        }else if(e.getID()==Enemy.ARSONIST){
            return new Bullet(s,position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==Enemy.CRIMINAL){
            return new Bullet(s,position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==Enemy.GRAFITTI){
            return new Bullet(s,position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
        }else if(e.getID()==Enemy.EDUCATION){
            return new Bullet(s,position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
        }
        
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
