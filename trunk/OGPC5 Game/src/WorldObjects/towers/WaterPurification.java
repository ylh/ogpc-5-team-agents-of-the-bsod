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
        cost=450;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    public void Draw(ImageCollection batch) {
        
    }

    @Override
    protected void loadAnimation() {
        
    }

    @Override
    protected void loadStats() {
        
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        return null;
    }
    
}
