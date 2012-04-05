/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;

/**
 *
 * @author pcowal15
 */
public class GenericTower extends Tower{
    GenericTower(Vector2 pos,String spritePath) {
        super(pos,spritePath,1,1);
    }
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        ani= new Animation(1, 20, position, 200);
        ani.addCell("Game Resources/Sprites/placeHold.png");
    }

    @Override
    protected void loadStats() {
        
    }

    @Override
    public void Draw(ImageCollection batch) {
        
    }
    
}
