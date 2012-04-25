/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.AbstractEnemy;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.Color;
import ogpc5.game.CityGame;

/**
 * A generic tower class, used primarily for testing purposes
 * @author pcowal15
 */
public class GenericTower extends Tower{
    
    /**
     * 
     * @param pos
     * @param spritePath 
     */
    public GenericTower(Vector2 pos,String spritePath) {
        super(pos,spritePath,1,1);
    }
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        ani= new Animation(1, 5, position, 200);
        ani.addCell("Game Resources/Sprites/placeHold.png");
    }

    @Override
    protected void loadStats() {
        
    }

    @Override
    public void Draw(ImageCollection batch){
        super.Draw(batch);
        ani.Draw(batch);
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        
    }

    /**
     * 
     * @param e
     * @return 
     */
    @Override
    protected Bullet setEnemyBulletHitting(AbstractEnemy e) {
        return null;
    }
    
}
