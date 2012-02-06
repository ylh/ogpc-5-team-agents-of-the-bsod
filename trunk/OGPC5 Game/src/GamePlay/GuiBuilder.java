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
    
    ArrayList<GUIObject> ribbons;
    
    //Mouse Selection Boxes
    Vector2 mouseSelectStart;
    boolean mouseSelect=false;
    Mouse mouse;
    Rect mouseSelectBox;
    
    public GuiBuilder(Mouse m, Game g){
        mouseSelectStart= new Vector2();
        mouseSelect=false;
        mouse=m;
        this.g=g;
        ribbons=new ArrayList<GUIObject>();
        ribbons.add(new GUIObject(new Vector2(100,100), "Game Resources/Sprites/GUIs/BlueBox.png"));
        ribbons.add(new GUIObject(new Vector2(780,100), "Game Resources/Sprites/GUIs/RedBox.png"));

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
        for(GUIObject r: ribbons){
            if((r.pos.getY())>=600){
                r.hasReachedBottom(true);
            }
            r.Update(ribbons);
        }
    }
    
    public void Draw(ImageCollection batch){
        for(GUIObject r: ribbons){
            r.Draw(batch);
        }
        int width= Toolkit.getDefaultToolkit().getScreenSize().width;
        int height= Toolkit.getDefaultToolkit().getScreenSize().height;
        int squareConstant=32;
        int lx=0,ly=0;
        for(int i=0; i<width/squareConstant; i++){
            for(int j=0; j<height/squareConstant; j++){
                batch.drawRect(new Vector2(lx,ly), squareConstant, squareConstant, Color.MAGENTA, 5);
                lx+=squareConstant;
            }
            lx=0;ly+=squareConstant;
        }
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
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        for(GUIObject o: ribbons){
            if(o.boundingBox.contains(me.getX(), me.getY())){
                o.setMove();
            }
        }
        mouseSelect=true;
        mouseSelectStart.reset(me.getX(), me.getY());
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
        
    }
    
}
