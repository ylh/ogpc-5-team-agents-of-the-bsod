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
 * These are the little thingys that come down whenever you earn an achievement!
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
    /**
     * Creates an achievement, which proceeds to display itself
     * @param i the path to the achievement sprite
     * @param n the name of the achievement
     * @param d the description of the achievement
     */
    public AchievementPopup(String i, String n, String d){
        Icon=new Image2D(i);
        name=n;
        description=d;
        x=20;
        y=-32;
        timer=0;
        active=true;
    }
    /**
     * The alternate constructor for the achievement
     * This one doesn't display the achievement immediately
     */
    public AchievementPopup(){
        active=false;
    }
    /**
     * If you use the alternate constructor, you can use this to make it appear
     * @param i the path to the achievement sprite
     * @param n the name of the achievement
     * @param d the description of the achievement
     */
    public void set(String i, String n, String d){
        Icon=new Image2D(i);
        name=n;
        description=d;
        x=20;
        y=-32;
        timer=0;
        active=true;
    }
    /**
     * If the achievement is currently being displayed, this moves it around
     */
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
    /**
     * If the achievement is active, this draws the icon and the text
     * The text is actually drawn twice for the shadow effect
     * @param batch 
     */
    public void draw(ImageCollection batch){
        if(active){
            batch.Draw(Icon, new Vector2(x,y),1000);
            //draw the text
            batch.DrawString(new Vector2(x+20,y-10), "ACHIEVEMENT GET!", 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD, 10);
            batch.DrawString(new Vector2(x+20,y), name, 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD, 10);
            batch.DrawString(new Vector2(x+20,y+10), description, 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD, 10);
            //draw the text's shadow
            batch.DrawString(new Vector2(x+19,y-9), "ACHIEVEMENT GET!", 
                Color.black, 999, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD, 10);
            batch.DrawString(new Vector2(x+19,y+1), name, 
                Color.black, 999, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD, 10);
            batch.DrawString(new Vector2(x+19,y+11), description, 
                Color.black, 999, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD, 10);
        }
    }
}
