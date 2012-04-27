/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Credits;

import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.Color;

/**
 *A credit is a way of giving credit to an individual who created the game and their role in the creation.
 * @author RomulusAaron
 */
public class Credit {
    String creditString;
    Vector2 position;
    int velocity=1;
    /**
     * This is the credit.
     * @param creditString This is the credit itself
     * @param position This is the position of the credit.
     */
    public Credit(String creditString, Vector2 position){
        this.creditString=creditString;
        this.position=position;
    }
    /**
     * This moves the credit down.
     */
    public void update(){
        position.dY(velocity);
    }
    /**
     * This shows the credit.
     * @param batch 
     */
    public void draw(ImageCollection batch){
        batch.DrawString(position, creditString, Color.white, 10, ImageCollection.FONT_SERIF, ImageCollection.FONT_ITALIC, 10);
    }
    /**
     * This stops the credit's movement.
     */
    public void stop(){
        velocity=0;
    }
    /**
     * This gets the Y-coordinate of the credit.
     * @return 
     */
    public double getY(){
        return position.getY();
    }
    
}
