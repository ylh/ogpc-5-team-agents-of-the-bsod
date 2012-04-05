/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.towers.Tower;
import WorldObjects.towers.GenericTower;
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
    Image2D sprite;

    public Button(String sp, Vector2 op, Vector2 cp, double ms) {
        openpos = op;
        closepos = cp;
        pos = cp.clone();
        movespeed = ms;
        sprite=new Image2D(sp);
        open = false;
        button = new Rect(pos, 100, 32);
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
        //System.out.print( targetpos.getY());
        //smooth glidey motion!
        dy = targetpos.getY() - pos.getY();
        dy *= movespeed;
        pos.dY(dy);
        //reset the bounding box
        button = new Rect(pos, 32, 32);
    }

    public boolean isPressed(Mouse mouse) {
        if (open
                && mouse.isPressed(Mouse.LEFT_BUTTON)) {
            Vector2 q = mouse.location().clone();
            q.subtract(pos);
            if (q.getX() > -16 && q.getX() < 16 && q.getY() > -16 && q.getY() < 16) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
    public void draw(ImageCollection batch){
        batch.Draw(sprite,pos,10000);
    }
}

