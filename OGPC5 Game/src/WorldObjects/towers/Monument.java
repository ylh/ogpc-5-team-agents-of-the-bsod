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
public class Monument extends Tower{
/**
 * creates a monument tower
 * @param pos
 * @param high
 * @param wide 
 */
    public Monument(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=200;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        ani = new Animation(11,10, position,100);
        for(int n=0; n<10; n++){
            ani.addCell("Game Resources/Sprites/August/Memorial/0"+n+".png");
        }
        ani.addCell("Game Resources/Sprites/August/Memorial/10.png");
    }

    @Override
    protected void loadStats() {
        damage=1;
        health=1000;
        range=48*Math.sqrt(2);
        adamage=10;
        projspeed=10;//not correct
        speed=25;//not done
        sdamage=0.5;
        this.moneyBonus=35;
        this.happyBonus=7;
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
        ani.Draw(batch);
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        theGame.money+=moneyBonus;
        if(theGame.moneyBonus){
            theGame.money+=moneyBonus;
        }
        theGame.happiness+=happyBonus;
    }

    @Override
    protected Bullet setEnemyBulletHitting(AbstractEnemy e) {
        if(e.getID()==AbstractEnemy.SMOG){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==AbstractEnemy.ARSONIST){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
