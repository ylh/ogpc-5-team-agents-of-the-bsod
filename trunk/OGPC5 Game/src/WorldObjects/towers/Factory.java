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
public class Factory extends Tower{

    public Factory(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=55;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        ani = new Animation(4,10, position,100);
        ani.addCell("Game Resources/Sprites/SamSprites/Towers/Factory/3factory1_32x32.png");
        ani.addCell("Game Resources/Sprites/SamSprites/Towers/Factory/3factory2_32x32.png");
        ani.addCell("Game Resources/Sprites/SamSprites/Towers/Factory/3factory3_32x32.png");
        ani.addCell("Game Resources/Sprites/SamSprites/Towers/Factory/3factory32x32.png");
        
//        ani= new Animation(21, 10, position, 100);
//        for(int i=0; i<21; i++){
//            ani.addCell("Game Resources/Sprites/August/ACME Corp/"+i+".png");
//        }
    }

    @Override
    protected void loadStats() {
        damage=25;
        health=200;
        range=48*Math.sqrt(2);
        adamage=0;
        projspeed=10;//not correct
        speed=15;//not done
        this.moneyBonus=8;
        this.happyBonus=-10;
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
        ani.Draw(batch);
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        theGame.polution+=100;
        if(theGame.moneyBonus){
            theGame.money+=moneyBonus;
        }
        theGame.money+=moneyBonus;
        theGame.happiness+=happyBonus;
    }

    @Override
    protected Bullet setEnemyBulletHitting(AbstractEnemy e) {
        if(e.getID()==AbstractEnemy.SMOG){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }
        
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
