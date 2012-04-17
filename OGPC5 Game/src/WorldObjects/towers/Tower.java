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
import java.awt.Color;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author Taylor Sutton & Peter Cowal
 * WHAT!? pcowal15 totally did more work than tsutton14 on this!!!
 * Let's not get petty about this...-Nekel_Seyew
 */
public abstract class Tower extends Tile {

    protected int cost;
    protected int moneyBonus;
    protected int happyBonus;
    protected double damage;  //Base damage dealt
    protected double health;
    protected double range;  
    protected double adamage;  //Damage dealt to armor
    protected double sdamage;  //Damage dealt to speed
    protected double projspeed;   //speed of the projectile
    protected double speed;  //lower is faster- any value <=1 fires every frame.
    protected double loaded;  //used to compute firing times.
    Animation ani;
    protected boolean rangeSelected=false;
    /*
     * Coordinates for bounding box
     */
    protected int bx1;
    protected int bx2;
    protected int by1;
    protected int by2;
    
    //Stuff For gameplay Updating
    boolean isNearHouse;
    boolean isNearFactory;
    boolean isNearStore;

    public Tower(Vector2 pos,String spritePath, int high, int wide) {
        super(pos);
        //sets default values so it'll work properly
        damage=1;
        health=10;
        range=100;
        adamage=0;
        sdamage=0;
        projspeed=10;
        speed=10;
        loaded=-10;
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
    public void Draw(ImageCollection batch){
        if(isSelected){
            //batch.fillRect(position, 32, 32, Color.blue, 2);
            //batch.drawRect(position, 32, 32, Color.blue, 100);
            batch.Draw(selection,position,100);
            batch.DrawString(new Vector2(850,15), "X:"+(position.getX()/32+1)+", Y:"+(position.getY()/32+1), Color.black, direction);
        }
        if(rangeSelected){
            
        }
    }

    public int getmoneyBonus() {
        return moneyBonus;
    }

    public int getHappyBonus() {
        return happyBonus;
    }

    public void setHappyBonus(int happyBonus) {
        this.happyBonus = happyBonus;
    }

    public void setmoneyBonus(int b) {
        moneyBonus = b;
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
    
    @Override
    public void select(Tile[][] t){
        isSelected=true;
        int x=((int)position.getX())/32;
        int y= ((int)position.getY())/32;
    }
    @Override
    public void unselect(Tile[][] t){
        isSelected=false;
        int x=((int)position.getX())/32;
        int y= ((int)position.getY())/32;
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


            double maxDanger = 0;

            Vector2 displacement = new Vector2(range, 0);
            double distance;
            WorldObject target = null;
            for (WorldObject w : wo) {
                if (w instanceof Enemy) {
                    
                    displacement = w.getPosition().clone();
                    displacement.subtract(position);
                    distance = displacement.length();
                    Enemy e=(Enemy)w;

                    if (distance < range && e.getDanger()>maxDanger) {
                        target = w;
                        maxDanger = e.getDanger();
                    }
                }
            }

            if (target != null) {
                //Bullet t=setEnemyBulletHitting((Enemy)target);
                Bullet t=new Bullet(position, damage, adamage, sdamage, projspeed, target);
                wo.add(t);//new Bullet(position, damage, adamage, sdamage, projspeed, target));
                
                loaded=speed;
            }
        }
    }
    
    
    public abstract void updateGameStats(CityGame theGame);
    protected abstract void loadAnimation();
    protected abstract void loadStats();
    protected abstract Bullet setEnemyBulletHitting(Enemy e);
    
    public static final int GENERIC=0;
    public static final int FACTORY=1;
    public static final int HOUSE=2;
    public static final int GREEN_BELT=3;
    public static final int MONUMENT=4;
    public static final int POLICE_FIRE_STATION=5;
    public static final int RECYCLING_CENTER=6;
    public static final int SCHOOL=7;
    public static final int STORE=8;
    public static final int WATER_PURIFICATION_CENTER=9;
}
