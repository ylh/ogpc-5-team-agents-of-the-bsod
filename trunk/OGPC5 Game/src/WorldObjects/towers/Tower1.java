/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Utilities.Animation;
import Utilities.Vector2;

/**
 *
 * @author pcowal15
 */
public class Tower1 extends Tower{
    Tower1(Vector2 pos,String spritePath) {
        super(pos,spritePath);
    }
    @Override
    public Animation getAnimation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
