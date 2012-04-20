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
    public ScrollingBackground(String b, double s){
        background=new Image2D(b);
        backgroundLeft=new Image2D(b);
        backgroundRight=new Image2D(b);
        speed=s;
        x=1;
        y=320;
    }
    public void scroll(){
        x+=speed;
    }
    public void draw(ImageCollection batch){
        batch.Draw(background, new Vector2(x%970,y),1);
        batch.Draw(backgroundLeft, new Vector2((x%970)-970,y),1);
        batch.Draw(backgroundRight, new Vector2((x%970)+970,y),1);
    }
}
