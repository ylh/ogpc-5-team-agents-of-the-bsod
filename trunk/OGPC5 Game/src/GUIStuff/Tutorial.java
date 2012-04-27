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
    Image2D fifth;
    public Tutorial(){
        next = new MenuButton(new Vector2(200,500), MenuButton.NEXT);
        screen=1;
        es= new EnemyScreen();
        first = new Image2D("Game Resources/Sprites/Tutorial/FirstTutorialScreen.png");
        second= new Image2D("Game Resources/Sprites/Tutorial/SecondTutorialScreen.png");
        third= new Image2D("Game Resources/Sprites/Tutorial/ThirdTutorialScreen.png");
        fourth= new Image2D("Game Resources/Sprites/Tutorial/FourthTutorialScreen.png");
        fifth= new Image2D("Game Resources/Sprites/Tutorial/FifthTutorialScreen.png");
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
            batch.Draw(fifth, new Vector2(970 / 2, 611 / 2), 0);
        }else if(screen == 6){
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
        return screen>=7;
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
            
            batch.DrawString(new Vector2(140,40),"The Smog Enemy: This is caused by excessivly high Polution.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,80),"The Gangs Enemy: These are caused by lack of Education and Lots of Stores", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,120),"The Arsonists Enemy: These are anarchists. They just don't like you.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,160),"The Criminal Enemy: They like to steal stuff. The police should stop them.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,200),"The Trash Enemy: Trash build up is what happens when you don't recycle.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,260),"The Water Polution Enemy: This water is filthy. You should clean it up.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,295),"The Fire Enemy: FIRE!!!!!! IT MOVES SO FAST!", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,340),"The Flood Enemy: Floods are never fun.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,370),"The Earthquake Enemy: Sure, it doesn't look like an earthquake. But when this lizard walks, the earth shakes.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,405),"The Grafitti Enemy: Beloved of Hip-Hop Artists, Archenemy of Posh Elitists. Schools should take care of them.", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            batch.DrawString(new Vector2(140,440),"The Bad Education Enemy: No one likes wearing the dunce cap. You should have tried harder at school!", Color.black, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 14, 10);
            
        }
    }
}
