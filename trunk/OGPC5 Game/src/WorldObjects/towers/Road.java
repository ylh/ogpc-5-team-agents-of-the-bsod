/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import GUIStuff.Tile;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Utilities.Mouse;

/**
 *
 * @author tsutton14
 */
public class Road extends Tile {
    
    protected Mouse mouse = new Mouse();
    
    
    public Road(Vector2 pos){
        super(pos);
        sprite=new Image2D("Game Resources/Sprites/BasicRoad.png");
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {        
    }

    @Override
    public void Draw(ImageCollection batch) {
        super.Draw(batch);
        Vector2 dp=position.clone();
        dp.dX(16);
        dp.dY(16);
        batch.addToCollection(sprite, 20, dp);
    }

    
    
}
