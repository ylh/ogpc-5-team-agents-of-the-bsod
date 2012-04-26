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
    String s10="Technicle information: ";
    String s11="*** STOP: 0x000000D1 (0x0000006c, 0x0000006f, 0x0000006c, 0x0000007a)";
    String s12="***     Main.join() -6982Bf error at Address 777Bg";
    String s13="Begining dump of physical memory";
    String s14="Physical memory dump complete";
    String s15="Hope you city was nice, because now it's gone!";
    
    public BlueScreen(){
        hasStarted=false;
    }
    
    public void Draw(ImageCollection batch){
        batch.Draw(background, new Vector2(970 / 2, 611 / 2));
    }
    
    public void Update(KeyBoard k){
        if(k.isKeyDown('s') && k.isKeyDown('g') && k.isKeyDown('d')  && k.isKeyDown('e')){
            sgde=true;
        }
        double t=System.currentTimeMillis();
        if(t-start>=5000){
            shouldExit=true;
        }
    }
    public void start(){
        start=System.currentTimeMillis();
        hasStarted=true;
    }
    public boolean goToStart(){
        return shouldExit && !sgde;
    }
    public boolean returnToGame(){
        return shouldExit && sgde;
    }
    public boolean hasStarted(){
        return hasStarted;
    }
}
