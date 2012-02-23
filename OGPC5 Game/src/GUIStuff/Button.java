/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Vector2;
import WorldObjects.towers.Tower;

/**
 *
 * @author pcowal15
 */
public class Button {
    Vector2 pos;
    Vector2 openpos;
    Vector2 closepos;
    Tower tower;
    double movespeed; //you want a value less than 0.5
    boolean open;
    public Button(Vector2 op, Vector2 cp, Tower t, double ms){
        openpos=op;
        closepos=cp;
        pos=cp;
        movespeed=ms;
        open=false;
    }
    public void update(){
        //open or closed?
        Vector2 targetpos;
        double dy;
        if (open){
            targetpos=openpos.clone();
        }
        else{
            targetpos=closepos.clone();
        }
        dy=targetpos.getY()-pos.getY();
        dy*=movespeed;
        pos.dY(dy);
    }
}
