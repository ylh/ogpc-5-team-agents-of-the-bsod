/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Vector2;

/**
 *
 * @author RomulusAaron
 */
public class MenuButton {
    Image2D normal;
    Image2D scrolled;
    Vector2 position;
    int type;
    boolean selected;
    
    public static final int CREDITS=0;
    public static final int START=1;
    
    public MenuButton(Vector2 pos, int type){
        position=pos;
        this.type=type;
        load(type);
    }
    public void draw(ImageCollection batch){
        if(selected){
            batch.Draw(scrolled, position, 15);
        }else{
            batch.Draw(normal, position, type);
        }
    }
    
    public void update(Mouse m){
        if(normal.getRectangle().contains((int)m.location().getX(), (int)m.location().getY())){
            selected=true;
        }else{
            selected=false;
        }
    }
    
    private void load(int type){
        if(type==CREDITS){
            normal = new Image2D("Game Resources/Sprites/GUIs/NormalCredits.png");
            scrolled= new Image2D("Game Resources/Sprites/GUIs/LargeCredits.png");
        }
        if(type==START){
            normal = new Image2D("Game Resources/Sprites/GUIs/NormalStart.png");
            scrolled= new Image2D("Game Resources/Sprites/GUIs/LargeStart.png");
        }
    }
    
    public boolean isPressed(Mouse m){
        return selected && m.isPressed(Mouse.LEFT_BUTTON);
    }
        
}
