/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KylesTesting;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.Color;

/**
 *
 * @author Ksweeney12
 */
public class Block {
    public static final int SELECTION=-1;
    public static final int GENERIC=0;
    public static final int ROAD=1;
    public static final int HOUSE=2;
    
    Vector2 position;
    Image2D icon;
    int type;
    boolean selected;
    
    public Block(int type, Vector2 pos){
        this(type, pos, "Game Resources/Sprites/generic.png");
    }
    
    public Block(Vector2 pos){
        this(0, pos, "Game Resources/Sprites/generic.png");
    }
    
    public Block(int type, Vector2 pos, String imagePath){
        this.type=type;
        this.position=pos;
        icon= new Image2D(imagePath);
    }
    
    public void update(){
    }
    
    public void draw(ImageCollection batch){
        if(type!=SELECTION)
            batch.Draw(icon, position, 10);
        
        if(selected){
            Vector2 b=position.clone();
            b.dX(-16);
            b.dY(-16);
            batch.fillRect(b, 32, 32, Color.red, 11);
        }
    }
}
