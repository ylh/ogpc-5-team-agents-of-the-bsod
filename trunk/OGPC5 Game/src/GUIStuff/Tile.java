/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Animation;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.util.ArrayList;

/**
 *A tile is the main unit of the towers.
 * @author Nekel-Seyew
 */
public class Tile extends WorldObject{
    
    protected boolean isSelected;
    protected Image2D selection;
    Vector2 offset;

    /**
     *This is a default constructor.
     * @param pos 
     */
    public Tile(Vector2 pos){
        super(pos, 0, "");
        selection=new Image2D("Game Resources/Sprites/GUIs/transpSquare.png");
        offset=new Vector2(16,16);
    }
    /**
     * This selects this tile.
     */
    public void select(Tile[][] t){
        isSelected=true;
    }
    /**
     * This checks if the tile is selected.
     * @return 
     */
    public boolean isSelected(){
        return isSelected;
    }
    /**
     * This de-selects the tile.
     */
    public void unselect(Tile[][] t){
        isSelected=false;
    }
    /**
     * This draws the tile. It also draws a highlighting rectangle.
     * @param batch The batch is the image collection used by the game.
     */
    @Override
    public void Draw(ImageCollection batch){
        if(isSelected){
            Vector2 pos = position.clone();
            pos.add(offset);
            //batch.fillRect(position, 32, 32, Color.blue, 2);
            //batch.drawRect(position, 32, 32, Color.blue, 100);
            batch.Draw(selection,pos,100);
            batch.DrawString(new Vector2(850,15), "X:"+(position.getX()/32+1)+", Y:"+(position.getY()/32+1), Color.black, direction);
        }
    }

    /**
     * This updates the tile.
     * @param wol It's the array of world objects in the game.
     */
    @Override
    public void Update(ArrayList<WorldObject> wol) {
        
    }
    
}
