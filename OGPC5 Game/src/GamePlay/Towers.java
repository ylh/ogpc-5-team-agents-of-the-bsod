/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.towers.Store;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Nekel-Seyew
 */
public class Towers implements ScrollingMenu{
    
    Store b= new Store(new Vector2(), 3, "");
    
    Tile TowerA;
    Vector2 offset;
    
    public Towers(Vector2 offset){
        this.offset=offset;
        Vector2 give= offset.clone();
        give.dY(20);
        TowerA= new Tile(b.getAnimation(),give, 1);
    }

    @Override
    public void Draw(ImageCollection batch) {
        TowerA.Draw(batch);
    }

    @Override
    public void GiveWheelEvent(MouseWheelEvent e) {
        
    }

    @Override
    public Object giveMouseEvent(MouseEvent e) {
        if(TowerA.bounding.contains(e.getX(), e.getY())){
            return new Tile(new Store(new Vector2(), 3, "").getAnimation(), new Vector2(e.getX(), e.getY()),1);
        }
        return null;
    }

    @Override
    public void setOffset(Vector2 offset) {
        
    }
    
}
