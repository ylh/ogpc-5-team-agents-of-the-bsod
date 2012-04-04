/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.Color;

/**
 *
 * @author RomulusAaron
 */
public class Credit {
    String creditString;
    Vector2 position;
    int velocity=1;
    
    public Credit(String creditString, Vector2 position){
        this.creditString=creditString;
        this.position=position;
    }
    
    public void update(){
        position.dY(velocity);
    }
    
    public void draw(ImageCollection batch){
        batch.DrawString(position, creditString, Color.red, 10);
    }
    
    public void stop(){
        velocity=0;
    }
    
    public double getY(){
        return position.getY();
    }
    
}
