/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package particlefx2;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Vector2;
import java.util.ArrayList;

/**
 *
 * @author Peter
 */
public class ParticleManager {
    double gravity;
    Image2D sprite;
    ArrayList<Particle> particles;


    public ParticleManager(String s, double g){
        sprite= new Image2D(s);
        gravity=g;
        particles= new ArrayList<Particle>();
    }

    public void addParticle(Vector2 pos, Vector2 vel){
        particles.add(new Particle(sprite,pos.clone(),vel.clone(),gravity));
    }

    public void update(){
        for(int i=0; i<particles.size(); i++){
            Particle p = particles.get(i);
            p.update();
            if (p.getX()<0 || p.getX()>800 || p.getY()<0 || p.getY()>600){
                particles.remove(p);
            }
        }
    }
    public void draw(ImageCollection batch){
        for(int i=0; i<particles.size(); i++){
            particles.get(i).draw(batch);
        }
    }
}
