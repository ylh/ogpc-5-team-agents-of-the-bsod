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
    double start;
    
    public static final int CREDITS=0;
    public static final int START=1;
    public static final int SKIP=2;
    public static final int YES=3;
    public static final int NEXT=4;
    
    /**
     * Initializes the button
     * @param pos the position of the button
     * @param type the kind of button
     */
    public MenuButton(Vector2 pos, int type){
        position=pos;
        this.type=type;
        load(type);
        start=System.currentTimeMillis();
    }
    
    /**
     * Draws a different sprite depending on the location of the mouse relative to the button
     * @param batch The ImageCollection for drawing
     */
    public void draw(ImageCollection batch){
        if(selected){
            batch.Draw(scrolled, position, 15);
        }else{
            batch.Draw(normal, position, 15);
        }
    }
    
    /**
     * Determines if the button should be highlighted
     * @param m Mouse in active use
     */
    public void update(Mouse m){
        if(normal.getRectangle().contains((int)m.location().getX(), (int)m.location().getY())){
            selected=true;
        }else{
            selected=false;
        }
    }
    /**
     * Initializes the image for the button based upon its type
     * @param type the kind of MenuButton
     */
    private void load(int type){
        if(type==CREDITS){
            normal = new Image2D("Game Resources/Sprites/GUIs/NormalCredits.png");
            scrolled= new Image2D("Game Resources/Sprites/GUIs/LargeCredits.png");
        }
        if(type==START){
            normal = new Image2D("Game Resources/Sprites/GUIs/NormalStart.png");
            scrolled= new Image2D("Game Resources/Sprites/GUIs/LargeStart.png");
        }
        if(type==YES){
            normal = new Image2D("Game Resources/Sprites/GUIs/YesUnselect.png");
            scrolled = new Image2D("Game Resources/Sprites/GUIs/YesSelect.png");
        }
        if(type==SKIP){
            normal = new Image2D("Game Resources/Sprites/GUIs/SkipUnselect.png");
            scrolled = new Image2D("Game Resources/Sprites/GUIs/SkipSelect.png");
        }
        if(type==NEXT){
            normal=new Image2D("Game Resources/Sprites/GUIs/nextSmall.png");
            scrolled=new Image2D("Game Resources/Sprites/GUIs/nextLarge.png");
        }
    }
    
    /**
     * Determines if the MenuButton is pressed
     * @param m the Mouse in active use
     * @return whether the mouse is over the button and the mouse is pressed
     */
    public boolean isPressed(Mouse m){
        return selected && m.isPressed(Mouse.LEFT_BUTTON);
    }
    
    /**
     * Determines if the button press is delayed
     * @param m Mouse in use
     * @return if the press is delayed
     */
    public boolean isPressedDelayed(Mouse m){
        double now=System.currentTimeMillis();
        if(selected && m.isPressed(Mouse.LEFT_BUTTON) && now-start >=1000){
            start=now;
            return true;
        }else{
            return false;
        }
    }
        
}
