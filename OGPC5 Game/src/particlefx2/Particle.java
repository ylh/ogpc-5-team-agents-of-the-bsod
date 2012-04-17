/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package particlefx2;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;

/**
 *
 * @author Peter
 */
public class Particle {
    public Image2D sprite;
    public Vector2 velocity;
    public Vector2 position;
    public double gravity;

    Particle(Image2D s, Vector2 p, Vector2 v, double g){
        velocity=v.clone();
        position=p.clone();
        gravity=g;
        sprite=s;
    }

    public double getX(){
        return position.getX();
    }

    public double getY(){
        return position.getY();
    }

    public void update(){
        velocity.dY(gravity);
        position.dX(velocity.getX());
        position.dY(10);
    }

    public void draw(ImageCollection batch){
        batch.Draw(sprite,position,100);
    }

}
