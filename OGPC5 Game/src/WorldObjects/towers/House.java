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
public class House extends Tower{
/**
 * creates a house tower
 * @param pos
 * @param high
 * @param wide 
 */
    public House(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=30;
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
        adamage=2;
        projspeed=5;//num of frames to reach enemy
        speed=5;//not done
        this.moneyBonus=1;
        this.happyBonus=3;
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
        
        boolean factoryNear=false;
        ArrayList<Tile> s=this.getRangedTowers(theGame.tiles);
        for(Tile t : s){
            if(t instanceof Factory){
                factoryNear=true;
            }
        }
        if(factoryNear){
            theGame.happiness-=1;
        }else{
            theGame.polution-=2;
        }
    }

    @Override
    protected Bullet setEnemyBulletHitting(AbstractEnemy e) {
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}
