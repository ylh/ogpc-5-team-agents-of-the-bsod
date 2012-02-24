/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.towers.Tower;
import WorldObjects.towers.Tower1;
/**
 *
 * @author pcowal15
 */
public class Button {

    Vector2 pos;
    Vector2 openpos;
    Vector2 closepos;
    Rect button;
    double movespeed; //you want a value less than 0.5
    boolean open;
    Mouse mouse;

    public Button(Vector2 op, Vector2 cp, Tower t, double ms) {
        openpos = op;
        closepos = cp;
        pos = cp;
        movespeed = ms;
        open = false;
        button = new Rect(pos, 48, 16);
    }

    
    public void setOpen(boolean o){
        open=o;
    }

    public void glide() {
        //open or closed?
        Vector2 targetpos;
        double dy;
        if (open) {
            targetpos = openpos.clone();
        } else {
            targetpos = closepos.clone();
        }
        //smooth glidey motion!
        dy = targetpos.getY() - pos.getY();
        dy *= movespeed;
        pos.dY(dy);
        //reset the bounding box
        button = new Rect(pos, 48, 16);
    }
    public boolean isPressed() {
        if (open &&
                mouse.isPressed(Mouse.LEFT_BUTTON) && 
                button.contains(mouse.location().getX(), mouse.location().getY())){
            return true;
        }
        else{
            return false;
        }
    }
    public Vector2 mouseSquare(){
        //returns the mouse's grid square
        double mx;
        double my;
        mx=32*Math.floor(mouse.location().getX()/32);
        my=32*Math.floor(mouse.location().getY()/32);
        return new Vector2(mx,my);
    }
}

