/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import GUIStuff.Tile;
import Utilities.Vector2;

/**
 * This was also back from the time we tried to make multitile towers
 * @author Ksweeney12
 */
public class MultiTileCell extends Tile{
    
    MasterTile theMaster;
    
    public MultiTileCell(Vector2 pos, MasterTile master){
        super(pos);
        theMaster=master;
    }
    
    public MasterTile getMaster(){
        return theMaster;
    }
}
