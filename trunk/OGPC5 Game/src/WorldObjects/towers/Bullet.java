/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
import Utilities.Animation;
import Utilities.Image2D;
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
    Vector2 startPos;
    WorldObject target;
    double distance;
    double damage;
    double adamage;
    double sdamage;
    double speed;
    
    public Bullet(Vector2 pos, double damage,double adamage,double sdamage, double velocity, WorldObject t){
        super(pos.clone(), -1, "Hi there");
        loadAnimation();
        this.speed=velocity;
        startPos=pos.clone();
        this.target=t;
        
        Vector2 endPoint=t.getPosition().clone();
        
        this.distance=0;
        this.damage=damage;
        this.adamage=adamage;
        this.sdamage=sdamage;
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {
        double sx=startPos.getX();
        double sy=startPos.getY();
        double ex=target.getPosition().getX();
        double ey=target.getPosition().getY();
        this.position=new Vector2(sx+distance*(ex-sx),sy+distance*(ey-sy));
        this.distance+=1/speed;
        
        
    }
    public boolean HasReachedTarget(){
        return distance>1;
        //return false;
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
        if(spriteAnimation!=null){
            //spriteAnimation.Draw(batch);
        }
        if (distance<1){
            Image2D i = new Image2D("Game Resources/Sprites/particle.png");
            batch.Draw(i,position,100);
        }
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
