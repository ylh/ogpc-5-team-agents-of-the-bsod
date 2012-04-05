/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
import GUIStuff.Tile;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author Taylor Sutton & Peter Cowal
 * WHAT!? pcowal15 totally did more work than tsutton14 on this!!!
 * Let's not get petty about this...-Nekel_Seyew
 */
public abstract class Tower extends MasterTile {

    protected int cost;
    protected int bonus;
    protected double damage;  //Base damage dealt
    protected double health;
    protected double range;  
    protected double adamage;  //Damage dealt to armor
    protected double sdamage;  //Damage dealt to speed
    protected double projspeed;   //speed of the projectile
    protected double speed;  //lower is faster- any value <=1 fires every frame.
    protected double loaded;  //used to compute firing times.
    Animation ani;
    /*
     * Coordinates for bounding box
     */
    protected int bx1;
    protected int bx2;
    protected int by1;
    protected int by2;

    public Tower(Vector2 pos,String spritePath, int high, int wide) {
        super(pos,high,wide);
        //sets default values so it'll work properly
        damage=10;
        health=10;
        range=100;
        adamage=0;
        sdamage=0;
        projspeed=10;
        speed=10;
        loaded=0;
        cost=0;
        loadAnimation();
        loadStats();
    }

    public abstract Animation getAnimation();
    
    public int getCost(){
        return cost;
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {
        loaded -= 1;
        shoot(wol);

    }

    @Override
    public abstract void Draw(ImageCollection batch);

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int b) {
        bonus = b;
    }

    public double getDamage() {
        return damage;
    }

    public double getArmorDamage() {
        return adamage;
    }

    public double getSpeedDamage() {
        return sdamage;
    }

    public void Hit(double damage, double adamage, double sdamage) {
        speed /= sdamage;
        health -= Math.max(0, damage);
    }

    public void setDamage(double s) {
        damage = s;
    }

    public void setArmorDamage(double s) {
        adamage = s;
    }

    public void setSpeedDamage(double s) {
        sdamage = s;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double h) {
        health = h;
    }

    protected void setBoundingBox() {
        bx1 = (int) ((int) sprite.getPosition().getX() - (.5 * sprite.getIconWidth()));
        bx2 = (int) ((int) sprite.getPosition().getX() + (.5 * sprite.getIconWidth()));
        by1 = (int) ((int) sprite.getPosition().getY() + (.5 * sprite.getIconHeight()));
        by2 = (int) ((int) sprite.getPosition().getY() - (.5 * sprite.getIconHeight()));
    }

    public void shoot(ArrayList<WorldObject> wo) {
        if (loaded < 0) {


            double minDistance = range;

            Vector2 displacement = new Vector2(range, 0);
            double distance;
            WorldObject target = null;
            for (WorldObject w : wo) {
                if (w instanceof Enemy) {
                    displacement = w.getPosition();
                    displacement.subtract(position);
                    distance = displacement.length();

                    if (distance < minDistance) {
                        target = w;
                        minDistance = distance;
                    }
                }
            }

            if (target != null) {
                wo.add(new Bullet(position, damage, adamage, sdamage, projspeed, target));
                loaded+=speed;
            }
        }
    }
    
    protected abstract void loadAnimation();
    protected abstract void loadStats();
}
