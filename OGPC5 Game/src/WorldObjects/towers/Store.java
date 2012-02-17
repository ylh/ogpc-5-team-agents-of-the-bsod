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
 * @author tsutton14
 */
public class Store extends Tower {
    
    public Store(Vector2 pos, int dir, String spritePath){
        super(pos, dir, spritePath);
        loadAnimation();
    }
    
    private void loadAnimation(){
        ani= new Animation(1, 20, position, 200);
        ani.addCell("Game Resources/Sprites/placeHold.png");
    }

    @Override
    public Animation getAnimation() {
        return ani;
    }
    
    @Override
    public void Draw(ImageCollection batch){
        ani.Draw(batch);
    }
    
}
