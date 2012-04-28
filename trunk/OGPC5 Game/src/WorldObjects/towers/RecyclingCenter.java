/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.AbstractEnemy;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class RecyclingCenter extends Tower{
/**
 * Creates a Recycling Center
 * @param pos
 * @param high
 * @param wide 
 */
    public RecyclingCenter(Vector2 pos, int high, int wide){
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
        ani.Draw(batch);
    }

    @Override
    protected void loadAnimation() {
        ani = new Animation(1,10, position,100);
        ani.addCell("Game Resources/Sprites/August/Recycling Center.png");
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
        theGame.money+=moneyBonus;
        theGame.happiness+=happyBonus;
    }

    @Override
    protected Bullet setEnemyBulletHitting(AbstractEnemy e) {
        String s="Game Resources/Sprites/August/Recycling Center Bullet.png";
        if(e.getID()==AbstractEnemy.TRASH){
            return new Bullet(s,position.clone(), damage*2, adamage*2, sdamage*2, projspeed, e);
        }else if(e.getID()==AbstractEnemy.WATER_POLUTION){
            return new Bullet(s,position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }
        
        return new Bullet(s,position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
