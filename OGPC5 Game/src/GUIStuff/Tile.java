/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Nekel-Seyew
 */
public class Tile extends WorldObject{
    
    private boolean isSelected;

    public Tile(Vector2 pos){
        super(pos, 0, "");
    }
    
    public void select(){
        isSelected=true;
    }
    public boolean isSelected(){
        return isSelected;
    }
    public void unselect(){
        isSelected=false;
    }
    
    @Override
    public void Draw(ImageCollection batch){
        if(isSelected){
            batch.fillRect(position, 32, 32, Color.blue, 2);
            batch.drawRect(position, 32, 32, Color.blue, 100);
            batch.DrawString(new Vector2(850,15), "X:"+(position.getX()/32+1)+", Y:"+(position.getY()/32+1), Color.black, direction);
        }
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {
        
    }
    
}
