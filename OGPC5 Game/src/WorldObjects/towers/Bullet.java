/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author tsutton14 and Nekel-Seyew
 */
public class Bullet extends WorldObject {
    
    Animation sprite;
    Vector2 velocity;
    double damage;
    double adamage;
    double sdamage;
    
    public Bullet(Vector2 pos, double damage,double adamage,double sdamage, double velocity, Vector2 endPoint){
        super(pos, -1, "Hi there");
        loadAnimation();
        this.velocity= new Vector2((pos.getX()-endPoint.getX())/velocity,
                (pos.getY()-endPoint.getY())/velocity);
        this.damage=damage;
        this.adamage=adamage;
        this.sdamage=sdamage;
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {
        this.position.dX(velocity.getX());
        this.position.dY(velocity.getY());
    }

    @Override
    public void Draw(ImageCollection batch) {
        if(sprite!=null)
            sprite.Draw(batch);
    }
    
    public void loadAnimation(){
        //TODO: load bullet animations
    }
    
    public double getDamage(){
        return damage;
    }
    public double getArmorDamage(){
        return adamage;
    }
    public double getSpeedDamage(){
        return sdamage;
    }
    
}
