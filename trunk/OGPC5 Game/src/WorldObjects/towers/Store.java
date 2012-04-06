/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author tsutton14
 */
public class Store extends Tower {
    
    public Store(Vector2 pos, int high, int width){
        super(pos,"",high,width);
        cost=700;
    }
    
    @Override
    protected void loadAnimation(){
        ani= new Animation(1, 20, position, 200);
        ani.addCell("Game Resources/Sprites/placeHold.png");
    }

    @Override
    public Animation getAnimation() {
        return ani;
    }
    
    @Override
    public void Draw(ImageCollection batch){
        ani.Draw(batch);
    }
    
    public void Update(ArrayList<WorldObject> wo){
        super.Update(wo);
        
    }

    @Override
    protected void loadStats() {
        
    }

    @Override
    public void updateGameStats(CityGame theGame) {
       
    }
    
}
