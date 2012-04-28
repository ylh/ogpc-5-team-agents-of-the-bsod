/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Credits;

import Utilities.Animation;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;

/**
 *
 * @author Nekel-Seyew
 */
public class BackgroundStar {
    Vector2 Position;
    Image2D sprite;
    public boolean shine;
    Animation d;
    double delay;
    double time;
    
    /**
     * Initializes the star's animation and the current time
     * @param pos the position of the star
     * @param delay the delay between redraws
     */
    public BackgroundStar(Vector2 pos, double delay){
        this.Position=pos;
        sprite= new Image2D("Game Resources/Sprites/Credits/BackgroundStar.png");
        d= new Animation(7, 5, this.Position, 500);
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar.png");
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar2.png");
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar3.png");
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar4.png");
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar3.png");
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar2.png");
        d.addCell("Game Resources/Sprites/Credits/BackgroundStar.png");
        time=System.currentTimeMillis();
        this.delay=delay;
    }
    
    /**
     * Draws star, shining if necessary
     * @param batch ImageCollection to be drawn
     */
    public void Draw(ImageCollection batch){
        if(System.currentTimeMillis()-time>=delay){
            shine=true;
            time=System.currentTimeMillis();
        }
        
        if(shine){
            d.Draw(batch);
            if(System.currentTimeMillis()-time>=3500){
                shine=false;
                time=System.currentTimeMillis();
            }
        }else{
            batch.Draw(sprite,Position, 5);
        }
    }
}
