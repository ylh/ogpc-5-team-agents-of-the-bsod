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
public class Factory extends Tower{

    public Factory(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=550;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        
    }

    @Override
    protected void loadStats() {
        
    }

    @Override
    public void Draw(ImageCollection batch) {
        
    }
    
}
