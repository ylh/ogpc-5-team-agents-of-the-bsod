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
    int damage;
    
    public Bullet(Vector2 pos, int damage, double velocity, Vector2 endPoint){
        super(pos, -1, "Hi there");
        loadAnimation();
        this.velocity= new Vector2((pos.getX()-endPoint.getX())/velocity,
                (pos.getY()-endPoint.getY())/velocity);
        this.damage=damage;
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
    
    public int getDamage(){
        return damage;
    }
    
}
