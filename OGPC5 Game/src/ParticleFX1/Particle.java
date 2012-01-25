/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParticleFX1;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;

/**
 *
 * @author pcowal15
 */
public class Particle {
    Vector2 pos; //position
    Vector2 vel; //velocity
    double g; //gravity
    double r; //resitsance
    int l; //life
    Image2D sprite;
    
    public Particle(Image2D s, Vector2 position, Vector2 velocity, double gravity, double resistance, int life){
        pos=position.clone();
        vel=velocity.clone();
        g=gravity;
        r=resistance;
        l=life;
        sprite=s;
    }
    public int update(int roomx, int roomy){
        pos.add(vel);
        vel.dY(g);
        double rx=(double)roomx;
        double ry=(double)roomy;
        if (vel.getX()<0) vel.setX(Math.min(0,vel.getX()+r));
        if (vel.getX()>0) vel.setX(Math.max(0,vel.getX()-r));
        if (vel.getY()<0) vel.setY(Math.min(0,vel.getY()+r));
        if (vel.getY()>0) vel.setY(Math.max(0,vel.getY()-r));
        l-=1;
        if (pos.getX()<0 || pos.getY()<0 || pos.getX()>rx || pos.getY()>ry){
            l=-9001;
        }
        return l;
    }
    public void draw(ImageCollection batch){
        batch.addToCollection(sprite,1,pos);
    }
}
