/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.towers.Store;
import WorldObjects.towers.Tower;

/**
 *
 * @author Nekel-Seyew
 */
public class Tile {
    Vector2 pos;
    Animation ani;
    Rect bounding;
    int towerType;
    
    public Tile(Animation a, Vector2 pos, int towerType){
        ani=a;
        this.pos=pos;
        bounding=new Rect((int)pos.getX()-16, (int)pos.getY()-16,32,32);
        ani.setPosition(pos);
        this.towerType=towerType;
    }
    
    public void giveNewPos(Vector2 pos){
        this.pos=pos;
        ani.setPosition(pos);
    }
    
    public void Draw(ImageCollection batch){
        bounding=new Rect((int)pos.getX()-16, (int)pos.getY()-16,32,32);
        ani.Draw(batch);
    }
    
    public Tower getTower(){
        if(towerType==1){
            return new Store(pos.clone(),0,"");
        }
        return null;
    }
}
