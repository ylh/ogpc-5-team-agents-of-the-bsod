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
    
}
