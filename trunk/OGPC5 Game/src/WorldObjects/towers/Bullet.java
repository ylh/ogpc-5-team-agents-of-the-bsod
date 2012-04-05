/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
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
    
    Animation spriteAnimation;
    Vector2 velocity;
    WorldObject target;
    double distance;
    double damage;
    double adamage;
    double sdamage;
    double speed;
    
    public Bullet(Vector2 pos, double damage,double adamage,double sdamage, double velocity, WorldObject t){
        super(pos, -1, "Hi there");
        loadAnimation();
        this.speed=velocity;
        
        this.target=t;
        
        Vector2 endPoint=t.getPosition();
        this.velocity= new Vector2((pos.getX()-endPoint.getX())/velocity,
                (pos.getY()-endPoint.getY())/velocity);
        this.distance=0;
        this.damage=damage;
        this.adamage=adamage;
        this.sdamage=sdamage;
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {
        this.position.dX(velocity.getX());
        this.position.dY(velocity.getY());
        this.distance+=1/speed;
        if(HasReachedTarget()){
            HitTarget();
        }
    }
    public boolean HasReachedTarget(){
        return distance>1;
    }
    public void HitTarget(){
        Enemy e;
        Tower t;
        if (target instanceof Enemy){
            e=(Enemy)target;
            e.Hit(damage,adamage,sdamage);
        }
        else if (target instanceof Tower){
            //this.target=(Tower)target;
        }
         
    }

    @Override
    public void Draw(ImageCollection batch) {
        if(spriteAnimation!=null)
            spriteAnimation.Draw(batch);
    }
    
    public void loadAnimation(){
        spriteAnimation= new Animation(1,1, position, 1000);
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
