/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class Tower extends WorldObject {

    protected int bonus;
    protected double damage;  //Base damage dealt
    protected double health;
    protected double range;
    protected double adamage;  //Damage dealt to armor
    protected double sdamage;  //Damage dealt to speed
    protected double speed;
    /*
     * Coordinates for bounding box
     */
    protected int bx1;
    protected int bx2;
    protected int by1;
    protected int by2;

    public Tower(Vector2 pos, int dir, String spritePath) {
        super(pos, dir, spritePath);
    }

    @Override
    public void Update(ArrayList<WorldObject> wol) {
        shoot(wol);

    }

    @Override
    public void Draw(ImageCollection batch) {
    }

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
            wo.add(new Bullet(position, damage, adamage, sdamage, speed, target.getPosition()));
        }
    }
}
