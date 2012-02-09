/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import ogpc5.game.CityGame;

/**
 *
 * @author Nekel-Seyew
 */
public class Stats implements ScrollingMenu{

    CityGame g;
    Vector2 offset;
    
    public Stats(CityGame game, Vector2 offSet){
        g=game;
        offset=offSet;
    }
    
    @Override
    public void Draw(ImageCollection batch) {
        batch.DrawString(new Vector2(offset.getX(), offset.getY()+20), 
                "Score: "+g.score, Color.red, 10);
        batch.DrawString(new Vector2(offset.getX(), offset.getY()+40), 
                "Money: "+g.money, Color.red, 10);
    }

    @Override
    public void GiveWheelEvent(MouseWheelEvent e) {
        
    }

    @Override
    public Object giveMouseEvent(MouseEvent e) {
        return new Object();
    }

    @Override
    public void setOffset(Vector2 offset) {
        this.offset=offset;
    }
    
}
