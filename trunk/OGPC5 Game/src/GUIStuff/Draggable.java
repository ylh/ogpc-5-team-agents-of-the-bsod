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
 * @author pcowal15
 */
public class Draggable {
    Vector2 pos;
    Image2D sprite;
    double movespeed = 0.5;
    int id;
    public Draggable(String sp,Vector2 p){
        sprite=new Image2D(sp);
        pos=p;
        
    }
    public void update(Mouse m){
        double x1=pos.getX();
        double x2=m.location().getX();
        double y1=pos.getY();
        double y2=m.location().getY();
        //smooth glidey motion!!!1!1
        pos.dX((x2-x1)*movespeed);
        pos.dY((y2-y1)*movespeed);
        
    }
    public void draw(ImageCollection batch){
        batch.Draw(sprite,pos,1000);
    }
    
}
