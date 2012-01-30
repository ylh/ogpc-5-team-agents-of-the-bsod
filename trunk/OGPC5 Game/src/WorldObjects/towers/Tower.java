/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class Tower extends WorldObject {
    
    protected double bonus;

    public Tower(Vector2 pos, int dir, String spritePath){
        super(pos, dir, spritePath);
    }
    
    @Override
    public void Update(ArrayList<WorldObject> wol) {        
        
    }

    @Override
    public void Draw(ImageCollection batch) {        
        
    }
    
}
