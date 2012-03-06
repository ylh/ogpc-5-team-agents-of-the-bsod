/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;

/**
 *
 * @author pcowal15, Nekel_Seyew
 */
public class Enemy extends WorldObject {
    double speed;
    double health;
    double armor;
    double danger;
    
    public Enemy(double Speed, double Health, double Armor, Vector2 pos, String path) {
        super(pos, 1, path);
        speed = Speed;
        health = Health;
        armor = Armor;
        danger = 0;
    }
    public void Hit(double damage,double adamage,double sdamage)
    {
        speed/=sdamage;
        armor=Math.max(0,armor-adamage);
        health-=Math.max(0,damage-armor);
    }
    public boolean isDead()
    {
        return health<1;
    }
    public double getDanger(){
        return danger;
    }
    @Override
    public void Update(ArrayList<WorldObject> wol) {
        //we'll want to change this in the future when we add roads
        position.add(new Vector2(0,-speed));
        danger+=speed;
    }

    @Override
    public void Draw(ImageCollection batch) {
        batch.addToCollection(sprite,1,position);
    }
    
    public static final int GENERIC=0;
    public static final int SMOG=1;
    public static final int GANGS=2;
    public static final int ARSONIST=3;
    public static final int CRIMINAL=4;
    public static final int TRASH=5;
    public static final int WATER_POLUTION=6;
    public static final int FIRE=7;
    public static final int FLOOD=8;
    public static final int EARTHQUACKE=9;
    public static final int GRAFITTI=10;
    public static final int EDUCATION=11;
}
