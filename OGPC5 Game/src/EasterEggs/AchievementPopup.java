/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EasterEggs;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.Color;

/**
 *
 * @author peter
 */
public class AchievementPopup {
    String name;
    String description;
    Image2D Icon;
    double x;
    double y;
    int timer;
    boolean active;
    public AchievementPopup(String i, String n, String d){
        Icon=new Image2D(i);
        name=n;
        description=d;
        x=20;
        y=-32;
        timer=0;
        active=true;
    }
    public void update(){
        if (active){
            timer++;
            if (timer<100){
                y=Math.min(y+2,20);
            }
            else if(timer>150){
                y=Math.max(y-2,-32);
                if (y==-32){
                    active=false;
                }
            }
        }
    }
    public void draw(ImageCollection batch){
        if(active){
            batch.Draw(Icon, new Vector2(x,y),1000);
            batch.DrawString(new Vector2(x+20,y-10), "ACHIEVEMENT GET!", 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            batch.DrawString(new Vector2(x+20,y), name, 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            batch.DrawString(new Vector2(x+20,y+10), description, 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
        }
    }
}
