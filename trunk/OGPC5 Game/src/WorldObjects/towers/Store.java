/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author tsutton14
 */
public class Store extends Tower {
    
    public Store(Vector2 pos, int high, int width){
        super(pos,"",high,width);
        cost=650;
    }
    
    @Override
    protected void loadAnimation(){
        ani= new Animation(1, 20, position, 200);
        ani.addCell("Game Resources/Sprites/August/Supermarket 32x32.png");
    }

    @Override
    public Animation getAnimation() {
        return ani;
    }
    
    @Override
    public void Draw(ImageCollection batch){
        super.Draw(batch);
        ani.Draw(batch);
    }
    
    public void Update(ArrayList<WorldObject> wo){
        super.Update(wo);
        
    }

    @Override
    protected void loadStats() {
        damage=10;
        health=200;
        range=112*Math.sqrt(2);
        adamage=0;
        projspeed=10;//not correct
        speed=10;//not done
        this.moneyBonus=10;
        this.happyBonus=2;
    }

    @Override
    public void updateGameStats(CityGame theGame) {
       theGame.money+=moneyBonus;
       theGame.happiness+=happyBonus;
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        if(e.getID()==Enemy.GANGS){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==Enemy.CRIMINAL){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==Enemy.GRAFITTI){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
