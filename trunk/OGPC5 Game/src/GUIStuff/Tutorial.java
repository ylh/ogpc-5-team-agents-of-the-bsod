/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.ImageCollection;
import Utilities.KeyBoard;
import Utilities.Mouse;
import Utilities.Vector2;
import java.awt.event.KeyEvent;

/**
 *
 * @author RomulusAaron
 */
public class Tutorial {
    MenuButton next;
    int screen;
    public Tutorial(){
        next = new MenuButton(new Vector2(800,500), MenuButton.NEXT);
        screen=1;
    }
    
    public void Draw(ImageCollection batch){
        
    }
    
    public void Update(KeyBoard k, Mouse m){
        next.update(m);
        if(next.isPressed(m) || k.isKeyDown(KeyEvent.VK_SPACE)){
            loadNext();
        }
        
        
    }
    
    private void loadNext(){
        screen++;
    }
    
}
