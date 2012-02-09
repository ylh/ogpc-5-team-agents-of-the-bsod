/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Game.Game;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class GuiBuilder extends Utilities.InputAdvance{

    Game g;
    
    
    //Mouse Selection Boxes
    Vector2 mouseSelectStart;
    boolean mouseSelect=false;
    Mouse mouse;
    Rect mouseSelectBox;
    Menu menu;
    
    public GuiBuilder(Mouse m, Game g){
        mouseSelectStart= new Vector2();
        mouseSelect=false;
        mouse=m;
        this.g=g;
        menu= new Menu((CityGame)g);
    }
    
    private void determineMouseSelectRect(){
        double mouseSelectX=mouseSelectStart.getX();
        double mouseSelectY=mouseSelectStart.getY();
        double mouseX=mouse.location().getX();
        double mouseY=mouse.location().getY();
        
        if(mouseSelectX<mouseX && mouseSelectY<mouseY){
            mouseSelectBox=new Rect(mouseSelectStart.clone(), 
                    (int)(mouseX-mouseSelectX), (int)(mouseY-mouseSelectY));
        }else if(mouseSelectX<mouseX &&mouseSelectY>mouseY){
            mouseSelectBox= new Rect((int)mouseSelectX, (int)mouseY, 
                    (int)(mouseX-mouseSelectX),(int)(mouseSelectY-mouseY) );
        }else if(mouseSelectX>mouseX && mouseSelectY<mouseY){
            mouseSelectBox= new Rect((int)mouseX, (int)mouseSelectY, 
                    (int)(mouseSelectX-mouseX),(int)(mouseY-mouseSelectY));
        }else{
            mouseSelectBox=new Rect((int)mouseX, (int)mouseY, 
                    (int)(mouseSelectX-mouseX), (int)(mouseSelectY-mouseY));
        }
    }
    
    public void update(){
        menu.Update();
        System.out.println(g.getWidth() +" "+ g.getHeight());
    }
    
    public void Draw(ImageCollection batch){
        int width= 854;
        int height= 632;
        int squareConstant=32;
        int lx=0,ly=0;
        for(int i=0; i<height/squareConstant; i++){
            for(int j=0; j<width/squareConstant; j++){
                batch.drawRect(new Vector2(lx,ly), squareConstant, squareConstant, Color.MAGENTA, 5);
                lx+=squareConstant;
            }
            lx=0;ly+=squareConstant;
        }
        menu.Draw(batch);
        if(mouseSelect){
            determineMouseSelectRect();
            batch.drawRect(mouseSelectBox, Color.red, 10);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyChar()=='z'){
            menu.resetRibbons();
        }
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
        mouseSelectStart.reset(me.getX(), me.getY());
        menu.giveMouseEvent(me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       mouseSelect=false;  
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
        menu.giveMouseWheelEvent(mwe);
    }
    
}
