/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParticleFX1;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import java.util.ArrayList;

/**
 *
 * @author pcowal15
 */
public class ParticleManager {
    public ArrayList<Particle> particles;
    Image2D sprite;
    double g;
    double r;
    int l;
    public ParticleManager(Image2D s, double gravity, double resistance, int life){
        sprite=s;
        g=gravity;
        r=resistance;
        l=life;
    }
    public void update(){
        for(Particle p : particles){
            int i=p.update(1600,1200);
            if (i<1) particles.remove(p);
        }
    }
    public void draw(ImageCollection batch){
        for(Particle p : particles){
            p.draw(batch);
        }
    }
    public void add(Vector2 pos, Vector2 vel){
        particles.add(new Particle(sprite,pos,vel,g,r,l));
    }
    public void explode(Vector2 pos, double speed, int amt){
        for(int i=0;i<amt;i+=1){
            double theta=Math.random()*Math.PI;
            double s=Math.random()*speed;
            Vector2 vel=new Vector2(s*Math.cos(theta),s*Math.sin(theta));
            add(pos,vel);
        }
    }
            
    
}
