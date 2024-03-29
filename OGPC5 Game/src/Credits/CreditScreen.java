/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Credits;

import KylesTesting.ClosingAnimation;
import Utilities.ImageCollection;
import Utilities.KeyBoard;
import Utilities.Mouse;
import Utilities.Vector2;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author RomulusAaron
 */
public class CreditScreen {
    Credit kyle;
    Credit peter;
    Credit august;
    Credit liam;
    Credit sam;
    Credit taylor;
    Credit thane;
    Credit yestin;
    Credit MrCowal;
    Credit Jesuit;
    Credit oracle;
    Credit aotbsod;
    ArrayList<Credit> credits;
    double startTimer;
    double theEndStart;
    int counter=0;
    boolean done;
    boolean endinground=false;
    ClosingAnimation finalPic;
    ArrayList<BackgroundStar> bgs= new ArrayList<BackgroundStar>();
    
    /**
     * Initializes the words that will appear in the credits
     */
    public CreditScreen(){
        kyle= new Credit("Kyle Sweeney: Project Manager, Lead Programmer", new Vector2(50,0));
        peter= new Credit("Peter Cowal: Programmer, Artistic and Music Contributor", new Vector2(50,0));
        august=new Credit("August Lindgren-Ruby: Lead Artist", new Vector2(50,0));
        liam=new Credit("Liam Mahoney: Artistic Contributor", new Vector2(50,0));
        sam=new Credit("Sam Strba: Artistic Contributor", new Vector2(50,0));
        taylor=new Credit("Taylor Sutton: Programmer", new Vector2(50,0));
        thane=new Credit("Thane Meyer: Documentation Engineer, Music Contributor", new Vector2(50,0));
        yestin = new Credit("Yestin Harrison: Artistic Contributor", new Vector2(50,0));
        MrCowal=new Credit("Mr. Vince Cowal: Coach Extraordinaire", new Vector2(50,0));
        Jesuit=new Credit("Jesuit High School: Our Wonderful School", new Vector2(50,0));
        oracle= new Credit("Made Using Java(c) by Oracle", new Vector2(50, 0));
        aotbsod=new Credit("An Agents of the BSoD Production (c)2012", new Vector2(340,0));
        credits= new ArrayList<Credit>();
        finalPic= new ClosingAnimation(new Vector2(500,250),ClosingAnimation.CLUB);
        
        credits.add(kyle);
        done=false;
        this.MakeBackgroundStars();
    }
    
    /**
     * Starts the credits
     */
    public void start(){
        startTimer=System.currentTimeMillis();
        theEndStart=startTimer;
    }
    
    /**
     * Scrolls each credit over time and determines if the credits are done scrolling
     */
    public void update(){
        double time=System.currentTimeMillis();
        if(time-startTimer>=5000){
            switch(counter){
                case 0:
                    credits.add(peter);
                    counter++;
                    startTimer=time;
                    break;
                case 1:
                    credits.add(august);
                    counter++;
                    startTimer=time;
                    break;
                case 2:
                    credits.add(liam);
                    counter++;
                    startTimer=time;
                    break;
                case 3:
                    credits.add(sam);
                    counter++;
                    startTimer=time;
                    break;
                case 4:
                    credits.add(taylor);
                    counter++;
                    startTimer=time;
                    break;
                case 5:
                    credits.add(thane);
                    counter++;
                    startTimer=time;
                    break;
                case 6:
                    credits.add(yestin);
                    counter++;
                    startTimer=time;
                    break;
                case 7:
                    credits.add(MrCowal);
                    counter++;
                    startTimer=time;
                    break;
                case 8:
                    credits.add(Jesuit);
                    counter++;
                    startTimer=time;
                    break;
                case 9:
                    credits.add(oracle);
                    counter++;
                    startTimer=time;
                    break;
                case 10:
                    credits.add(aotbsod);
                    counter++;
                    startTimer=time;
                    break;
            }
        }
        for (Credit c : credits) {
            if (c == aotbsod && aotbsod.getY() >= 570 && !endinground) {
                c.stop();
                endinground = true;
                startTimer = System.currentTimeMillis();
            }
            c.update();
        }
        if (time - startTimer >= 10000 && endinground) {
            done = true;
        }
    }
    
    /**
     * Draws credits and stars needed
     * @param batch ImageCollection to be drawn
     */
    public void draw(ImageCollection batch){
        for(Credit c: credits){
            c.draw(batch);
        }
        if(endinground){
            finalPic.draw(batch);
        }
        for(BackgroundStar bs : bgs){
            bs.Draw(batch);
        }
    }
    
    /**
     * Determines if the credits should be finished
     * @param k KeyBoard in use
     * @param m Mouse in use
     * @return if the credits are ended
     */
    public boolean isDone(KeyBoard k, Mouse m){
        return (k.isKeyDown(KeyEvent.VK_SPACE) ||m.isPressed(Mouse.LEFT_BUTTON) || done) && (System.currentTimeMillis()-theEndStart>=100);
    }
    
    /**
     * Creates stars randomly for the background
     */
    public void MakeBackgroundStars(){
        Toolkit t = Toolkit.getDefaultToolkit();
        double height=t.getScreenSize().height;
        double width=t.getScreenSize().width;
        for(int i=0; i<120; i++){
            bgs.add(new BackgroundStar(new Vector2(Math.random()*width, Math.random()*height),Math.random()*100000));//JTW
        }
    }
}
