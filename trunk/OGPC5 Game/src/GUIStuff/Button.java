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
 *A button is an image that has a code. This code is executed out when the button is clicked on.
 * @author pcowal15
 */
public class Button {

    Vector2 pos;
    double travel;
    double openY;
    double closeY;
    Rect button;
    double movespeed; //you want a value less than 0.5
    boolean open;
    Image2D sprite;
    int id;
    String spriteString;

    


    /**
     * This is the button.
     * @param i The id for the button.
     * @param sp The sprite for the button.
     * @param op The opened position of the button.
     * @param cp The closed position of the button.
     * @param ms How fast the button goes.
     */

    public Button(int i, String sp, double x,double y) {
        id=i;
        openY = y;
        closeY = y-300;
        pos = new Vector2(x,closeY);
        movespeed = 0.05;
        travel=0;
        sprite=new Image2D(sp);
        open = false;
        button = new Rect(pos, 100, 32);
        spriteString=sp;
    }

    /**
     * This tells the game that the button is opened.
     * @param o 
     */
    public void setOpen(boolean o){
        open=o;
    }
    /**
     * This returns the id.
     * @return 
     */
    public int id(){
        return id;
    }
    /**
     * This makes the button move down as if it is opening if it is closed.
     * It makes the button move back up as if it is closing if it is open.
     */
    public void glide() {
        //open or closed?
        if (open) {
            travel=Math.min(1,travel+movespeed);
            
        } else {
            travel=Math.max(0,travel-movespeed);
        }
        //System.out.print( targetpos.getY());
        //smooth glidey motion!
        pos=new Vector2(pos.getX(),openY+(1-travel)*(1-travel)*(closeY-openY));
        //pos=new Vector2(pos.getX(),closeY+travel*(openY-closeY));
        //reset the bounding box
        button = new Rect(pos, 32, 32);
        
    }
    /**
     * This tells the game if the button is clicked on.
     * @param mouse This is the arrow that when it is over the button when the mouse is clicked, it executes the code.
     * @return This tells the game if the button is opened or closed.
     */
    public boolean isPressed(Mouse mouse) {
        if (open && mouse.isPressed(Mouse.LEFT_BUTTON)) {
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
    /**
     * This draws the new position of the sprite.
     * @param batch 
     */
    public void draw(ImageCollection batch){
        batch.Draw(sprite,pos,400);
    }
    public String getPath(){
        return spriteString;
    }
    public Image2D getSprite(){
        return sprite;
    }
}

