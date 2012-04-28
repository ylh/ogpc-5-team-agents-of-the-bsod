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

    /**
     * Initializes a particle with motion and existence properties
     * @param s the sprite of the particle
     * @param p the position of the particle
     * @param v the velocity of the particle
     * @param g the gravity acting on the particle
     */
    Particle(Image2D s, Vector2 p, Vector2 v, double g){
        velocity=v.clone();
        position=p.clone();
        gravity=g;
        sprite=s;
    }

    /**
     * Gets the x value of the particle
     * @return x value
     */
    public double getX(){
        return position.getX();
    }

    /**
     * Gets the y value of the particle
     * @return y value
     */
    public double getY(){
        return position.getY();
    }

    /**
     * Moves the particle by accounting for velocity and gravity
     */
    public void update(){
        velocity.dY(gravity);
        position.dX(velocity.getX());
        position.dY(10);
    }

    /**
     * Draws the particle
     * @param batch ImageCollection to be drawn
     */
    public void draw(ImageCollection batch){
        batch.Draw(sprite,position,100);
    }

}
