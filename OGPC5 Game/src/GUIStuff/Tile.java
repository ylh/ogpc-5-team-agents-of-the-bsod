/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.towers.Tower;
import java.awt.Color;

/**
 *
 * @author Nekel-Seyew
 */
public class Tile extends Tower{
    
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
            batch.fillRect(position, 32, 32, Color.red, 2);
        }else{
            batch.drawRect(position, 32, 32, Color.red, 1);
        }
        super.Draw(batch);
    }
    
    @Override
    public Animation getAnimation() {
        return null;
    }
    
}
