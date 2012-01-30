/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Ksweeney12
 */
public class GuiBuilder extends Utilities.InputAdvance{

    Vector2 mouseStart;
    boolean mouseSelect=false;
    Mouse mouse;
    Rect mouseSelectRect;
    
    public GuiBuilder(Mouse m){
        mouseStart= new Vector2();
        mouseSelect=false;
        mouse=m;
    }
    
    private void determineMouseSelectRect(){
        double mouseStartX=mouseStart.getX();
        double mouseStartY=mouseStart.getY();
        double destX=mouse.location().getX();
        double destY=mouse.location().getY();
        
        if(mouseStartX<destX && mouseStartY<destY){
            mouseSelectRect= new Rect(mouseStart, (int)(destX-mouseStartX), (int)(destY-mouseStartY));
        }
    }
    
    public void Draw(ImageCollection batch){
        batch.drawRect(new Vector2(200,200), 40, 30, Color.red, 10);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseSelect=true;
        mouseStart.reset(me.getX(), me.getY());
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        
    }
    
}
