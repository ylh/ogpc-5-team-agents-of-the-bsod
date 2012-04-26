/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.KeyBoard;
import Utilities.Mouse;
import Utilities.Vector2;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author RomulusAaron
 */
public class Tutorial {
    MenuButton next;
    int screen;
    EnemyScreen es;
    Image2D first;
    Image2D second;
    Image2D third;
    Image2D fourth;
    public Tutorial(){
        next = new MenuButton(new Vector2(200,500), MenuButton.NEXT);
        screen=1;
        es= new EnemyScreen();
        first = new Image2D("Game Resources/Sprites/Tutorial/FirstTutorialScreen.png");
        second= new Image2D("Game Resources/Sprites/Tutorial/SecondTutorialScreen.png");
        third= new Image2D("Game Resources/Sprites/Tutorial/ThirdTutorialScreen.png");
        fourth= new Image2D("Game Resources/Sprites/Tutorial/FourthTutorialScreen.png");
    }
    
    public void Draw(ImageCollection batch){
        next.draw(batch);
        if(screen ==1){
            batch.Draw(first, new Vector2(970 / 2, 611 / 2), 0);
        }else if(screen ==2){
            batch.Draw(second, new Vector2(970 / 2, 611 / 2), 0);
        }else if(screen==3){
            batch.Draw(third, new Vector2(970 / 2, 611 / 2), 0);
        }else if(screen==4){
            batch.Draw(fourth, new Vector2(970 / 2, 611 / 2), 0);
        }else if(screen==5){
            es.Draw(batch);
        }
    }
    
    public void Update(KeyBoard k, Mouse m){
        next.update(m);
        if(next.isPressedDelayed(m) || k.isKeyDown(KeyEvent.VK_SPACE)){
            loadNext();
        }
        
        
    }
    
    public boolean isDone(){
        return screen>=6;
    }
    
    private void loadNext(){
        screen++;
    }
    
    
    private class EnemyScreen{
        Image2D Smog=new Image2D("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png");
        Image2D Gangs= new Image2D("Game Resources/Sprites/Yestin/gang.png");
        Image2D Arsonists=new Image2D("Game Resources/Sprites/SamSprites/Enemies/Arsonist/arsonist0.png");
        Image2D Criminal= new Image2D("Game Resources/Sprites/Yestin/criminal1.png");
        Image2D Trash= new Image2D("Game Resources/Sprites/Yestin/trash.png");
        Image2D WaterPolution= new Image2D("Game Resources/Sprites/SamSprites/Enemies/WaterPolution/waterPollution0.png");
        Image2D Fire= new Image2D("Game Resources/Sprites/SamSprites/Enemies/Fire/fire0.png");
        Image2D Flood= new Image2D("Game Resources/Sprites/SamSprites/Enemies/Flood/flood6.png");
        Image2D Earthquake= new Image2D("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-1.png");
        Image2D Grafitti= new Image2D("Game Resources/Sprites/SamSprites/Enemies/Grafitti/graffiti18.png");
        Image2D Education= new Image2D("Game Resources/Sprites/Liam's Sprites/Enemies/Bad Education/Bad Education.png");
        
        Image2D Background=new Image2D("Game Resources/Sprites/bgtexture.png");
        
        public EnemyScreen(){
            
        }
        
        public void Draw(ImageCollection batch){
            batch.Draw(Background, new Vector2(970 / 2, 611 / 2), 0);
            batch.Draw(Smog, new Vector2(100,40), 1);
            batch.Draw(Gangs, new Vector2(100,80), 1);
            batch.Draw(Arsonists, new Vector2(100,120), 1);
            batch.Draw(Criminal, new Vector2(100,160), 1);
            batch.Draw(Trash, new Vector2(100,200), 1);
            batch.Draw(WaterPolution, new Vector2(100,240), 1);
            batch.Draw(Fire, new Vector2(100,280), 1);
            batch.Draw(Flood, new Vector2(100,320), 1);
            batch.Draw(Earthquake, new Vector2(100,360), 1);
            batch.Draw(Grafitti, new Vector2(100,400), 1);
            batch.Draw(Education, new Vector2(100,440), 1);
            
            batch.DrawString(new Vector2(140,40),"The Smog Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,80),"The Gangs Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,120),"The Arsonists Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,160),"The Criminal Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,200),"The Trash Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,240),"The Water Polution Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,280),"The Fire Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,320),"The Flood Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,360),"The Earthquake Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,400),"The Grafitti Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,440),"The Bad Education Enemy", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            
        }
    }
}
