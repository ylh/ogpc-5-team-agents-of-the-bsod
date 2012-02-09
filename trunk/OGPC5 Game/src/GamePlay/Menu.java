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
public class Menu {
    Vector2 position;
    int width;
    int height;
    int currentRibbon;
    
    GUIObject towerRibbon;
    GUIObject statsRibbon;
    GUIObject optionsRibbon;
    
    ScrollingMenu stats;
    
    public Menu(CityGame g){
        position=new Vector2(715,0);
        width= 128;
        height=40*8;
        towerRibbon= new GUIObject(new Vector2(780,20), "Game Resources/Sprites/GUIs/BlueBox.png");
        statsRibbon=new GUIObject(new Vector2(780,60), "Game Resources/Sprites/GUIs/RedBox.png");
        optionsRibbon=new GUIObject(new Vector2(780,100), "Game Resources/Sprites/GUIs/BlueBox.png");
        stats= new Stats(g, new Vector2());
    }
    
    public void Draw(ImageCollection batch){
        towerRibbon.Draw(batch);
        statsRibbon.Draw(batch);
        optionsRibbon.Draw(batch);
        batch.fillRect(position, width, height, Color.white, 9);
        batch.drawRect(position, width, height, Color.black, 9);
    }
    
    public void Update(){
        towerRibbon.Update();
        statsRibbon.Update();
        optionsRibbon.Update();
    }
    
    public void giveMouseEvent(MouseEvent e){
        if(towerRibbon.boundingBox.contains(e.getX(), e.getY())){
            if (statsRibbon.isAtBottom) {
                statsRibbon.setDistanceToGo(40 * 5, -1);
                optionsRibbon.setDistanceToGo(40 * 5, -1);
                statsRibbon.start();
                optionsRibbon.start();
            } else {
                if(optionsRibbon.isAtBottom){
                    statsRibbon.setDistanceToGo(40 * 5, 1);
                    statsRibbon.start();
                } else {
                    statsRibbon.setDistanceToGo(40 * 5, 1);
                    optionsRibbon.setDistanceToGo(40 * 5, 1);
                    statsRibbon.start();
                    optionsRibbon.start();
                }
            }
        }
        if(statsRibbon.boundingBox.contains(e.getX(), e.getY())) {
            if (optionsRibbon.isAtBottom) {
                optionsRibbon.setDistanceToGo(40 * 5, -1);
                optionsRibbon.start();
            } else {
                optionsRibbon.setDistanceToGo(40 * 5, 1);
                optionsRibbon.start();
            }
        }
        if(optionsRibbon.boundingBox.contains(e.getX(), e.getY())){
            
        }
    }
    public void giveMouseWheelEvent(MouseWheelEvent e){
        
    }
}
