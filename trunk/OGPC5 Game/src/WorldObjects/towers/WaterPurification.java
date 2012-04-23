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
public class WaterPurification extends Tower{

    public WaterPurification(Vector2 pos, int high, int wide){
        super(pos, "", high, wide);
        cost=45;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
    }

    @Override
    protected void loadAnimation() {
        
    }

    @Override
    protected void loadStats() {
        damage=10;
        health=150;
        range=112*Math.sqrt(2);
        adamage=0;
        projspeed=10;//not correct
        speed=10;//not done
        this.moneyBonus=-2;
        this.happyBonus=0;
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        if(e.getID()==Enemy.TRASH){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==Enemy.WATER_POLUTION){
            return new Bullet(position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
        }
        
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
