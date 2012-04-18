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
public class House extends Tower{

    public House(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=300;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        ani = new Animation(8,10, position,100);
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-1.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-2.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-3.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-4.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-5.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-3.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-2.png");
        ani.addCell("Game Resources/Sprites/Liam's Sprites/Towers/House/House1-1.png");
    }

    @Override
    protected void loadStats() {
        damage=5;
        health=100;
        range=80*Math.sqrt(2);
        adamage=0;
        projspeed=10;//num of frames to reach enemy
        speed=10;//not done
        this.moneyBonus=1;
        this.happyBonus=3;
    }

    @Override
    public void Draw(ImageCollection batch) {
        ani.Draw(batch);
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        theGame.money+=moneyBonus;
        theGame.happiness+=happyBonus;
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
