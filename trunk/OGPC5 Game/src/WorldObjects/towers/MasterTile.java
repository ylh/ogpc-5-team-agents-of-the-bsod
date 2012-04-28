/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 * This was back from when we tried to create multi-tile buildings
 * We scrapped that idea
 * @author Ksweeney12
 */
public class MasterTile extends Tile{
    
    Tile[][] suboordinates;
    
    public MasterTile(Vector2 pos, int high, int wide){
        super(pos);
        suboordinates= new Tile[high][wide];
        suboordinates[0][0]=this;
        for(int r=0; r<high; r++){
            for(int c=0; c<wide; c++){
                if(suboordinates[r][c]==this){
                    continue;
                }else{
                    suboordinates[r][c]= new MultiTileCell(new Vector2(pos.getX()+32*(r), pos.getY()+32*(c)),this);
                }
            }
        }
    }
    
    public MasterTile getMaster(){
        return this;
    }
    
    @Override
    public void Update(ArrayList<WorldObject> wol) {
        
    }
    
}
