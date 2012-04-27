/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.ImageCollection;
import Utilities.KeyBoard;
import Utilities.Vector2;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author RomulusAaron
 */
public class EndScreen {
    double start;
    boolean restart;
    boolean hasStarted;
    
    public EndScreen(){
        restart=false;
        hasStarted=false;
    }
    public void Update(KeyBoard k){
        if(System.currentTimeMillis()-start >=10 * 1000 || k.isKeyDown(KeyEvent.VK_SPACE)){
            restart=true;
        }
    }
    public void start(){
        start=System.currentTimeMillis();
        hasStarted=true;
    }
    public boolean hasStarted(){
        return hasStarted;
    }
    
    public boolean restart(){
        return restart;
    }
    
    public void Draw(ImageCollection batch){
        batch.DrawString(new Vector2(100,100), "Your citizenry no longer trust you to run their city.", 
                Color.white, 10, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 18);
        batch.DrawString(new Vector2(100,140), "You have been DEPOSED!", 
                Color.white, 10, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 18);
    }
    
}
