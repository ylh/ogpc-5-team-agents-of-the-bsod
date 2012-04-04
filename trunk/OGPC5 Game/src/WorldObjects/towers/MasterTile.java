/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import GUIStuff.Tile;
import Utilities.Vector2;

/**
 *
 * @author Ksweeney12
 */
public class MasterTile extends Tile{
    
    Tile[][] suboordinates;
    
    public MasterTile(Vector2 pos, int high, int wide){
        super(pos);
        suboordinates= new MultiTileCell[high][wide];
        suboordinates[0][0]=this;
    }
}
