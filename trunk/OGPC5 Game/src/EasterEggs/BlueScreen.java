/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EasterEggs;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.KeyBoard;
import Utilities.SoundFile;
import Utilities.Vector2;
import java.awt.Color;

/**
 *
 * @author RomulusAaron
 */
public class BlueScreen {
    Image2D background= new Image2D("Game Resources/Sprites/EasterEggs/BlueScreen/BlueScreen.png");
    SoundFile aww=new SoundFile("Game Resources/Sound/AwwComeon.wav",1);
    
    boolean hasStarted=false;
    boolean shouldExit=false;
    boolean sgde=false;
    double start;
    
    String s1="A problem has been detected and Urban Towers has been shut down to prevent damage";
    String s2="to your computer.";
    String s3="AGENTS_OF_THE_BSoD";
    String s4="If this is the first time you've seen this Stop error screen,";
    String s5="the game will restart, If this screen appears again, follow";
    String s6="these steps: ";
    String s7="Check to make sure that any new hardware or software is properly installed.";
    String s8="If this is a Java problem, you are screwed until Oracle releases a new update";
    String s9="which might simply continue the problem.";
    String s10 ="If problems continue, disable or remove any newly installed hardware";
    String s11="or software. Disable JAVA memory options such as caching or shadowing.";
    String s12="If you need to use Safe Mode to remove or disable any components, then";
    String s13="you are doing something wrong, because this isn't the OS. Protip:";
    String s14="hold down BSoD.";
    String s15="Technical information: ";
    String s16="***   STOP: 0x000000D1 (0x0000006c, 0x0000006f, 0x0000006c, 0x0000007a)";
    String s17="***          Main.join() -6982Bf error at Address 777Bg";
    String s18="Begining dump of physical memory";
    String s19="Physical memory dump complete";
    String s20="Hope you city was nice, because now it's gone!";
    String s21="Contact your local Sys Admin for more information";
    
    /**
     * Hasn't yet started
     */
    public BlueScreen(){
        hasStarted=false;
    }
    
    /**
     * Draws blue screen and each String of text
     * @param batch ImageCollection for drawing
     */
    public void Draw(ImageCollection batch){
        batch.Draw(background, new Vector2(970 / 2, 611 / 2));
        batch.DrawString(new Vector2(10,14), s1, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*2), s2, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*4), s3, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*6), s4, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*7), s5, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*8), s6, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*10), s7, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*11), s8, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*12), s9, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*14), s10, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*15), s11, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*16), s12, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*17), s13, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*18), s14, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*20), s15, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*22), s16, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*25), s17, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*27), s18, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*28), s19, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*29), s20, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
        batch.DrawString(new Vector2(10,14*30), s21, Color.white, 1,ImageCollection.FONT_MONOSPACED, ImageCollection.FONT_NORMAL, 18);
    }
    
    /**
     * Allows the user the ability to return to game before it restarts
     * @param k KeyBoard in use
     */
    public void Update(KeyBoard k){
        if(k.isKeyDown('s') && k.isKeyDown('d') && k.isKeyDown('b') && k.isKeyDown('o')){
            sgde=true;
        }
        double t=System.currentTimeMillis();
        if(t-start>=5000){
            shouldExit=true;
        }
    }
    
    /**
     * Starts
     */
    public void start(){
        start=System.currentTimeMillis();
        hasStarted=true;
    }
    
    /**
     * Determines if game should restart
     * @return if the blue screen has persisted long enough without opt-out
     */
    public boolean goToStart(){
        return shouldExit && !sgde;
    }
    
    /**
     * Determines if game should resume
     * @return if the blue screen timer has run out and the proper keys have been pressed for opt-out
     */
    public boolean returnToGame(){
        return shouldExit && sgde;
    }
    
    /*
     * Determines if the blue screen has started
     */
    public boolean hasStarted(){
        return hasStarted;
    }
}
