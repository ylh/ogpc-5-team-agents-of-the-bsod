/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;

/**
 *
 * @author pcowal15
 */
public class ScrollingBackground {
    double x;
    double y;
    Image2D background;
    Image2D backgroundLeft;
    Image2D backgroundRight;
    double speed;
    
    /**
     * Initializes the a scrolling set of images
     * @param b the file path
     * @param s the speed of the scrolling
     */
    public ScrollingBackground(String b, double s){
        background=new Image2D(b);
        backgroundLeft=new Image2D(b);
        backgroundRight=new Image2D(b);
        speed=s;
        x=1;
        y=320;
    }
    
    /**
     * Scrolls by increasing the x value by the speed
     */
    public void scroll(){
        x+=speed;
    }
    
    /**
     * Draws different parts of each of the images in visible space to give the appearance of scrolling
     * @param batch the ImageCollection for drawing
     */
    public void draw(ImageCollection batch){
        batch.Draw(background, new Vector2(x%970,y),1);
        batch.Draw(backgroundLeft, new Vector2((x%970)-970,y),1);
        batch.Draw(backgroundRight, new Vector2((x%970)+970,y),1);
    }
}
