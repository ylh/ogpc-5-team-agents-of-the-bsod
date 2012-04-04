/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.ImageCollection;
import Utilities.KeyBoard;
import Utilities.Vector2;
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
    Credit MrCowal;
    Credit Jesuit;
    Credit oracle;
    Credit aotbsod;
    ArrayList<Credit> credits;
    double startTimer;
    int counter=0;
    boolean done;
    boolean endinground=false;;
    
    public CreditScreen(){
        kyle= new Credit("Kyle Sweeney: Project Manager, Lead Programmer", new Vector2(50,0));
        peter= new Credit("Peter Cowal: Programmer, Artistic Contributer", new Vector2(50,0));
        august=new Credit("August Lindgren-Ruby: Artistic and Music Contributer", new Vector2(50,0));
        liam=new Credit("Liam Mahoney: Artistic Contributer", new Vector2(50,0));
        sam=new Credit("Sam Strba: Artistic Contributer", new Vector2(50,0));
        taylor=new Credit("Taylor Sutton: Programmer", new Vector2(50,0));
        thane=new Credit("Thane Meyer: Documentation Engineer, Music Contributer", new Vector2(50,0));
        MrCowal=new Credit("Mr. Vince Cowal: Coach Extraordinaire", new Vector2(50,0));
        Jesuit=new Credit("Jesuit High School: Our Wonderful School", new Vector2(50,0));
        oracle= new Credit("Made Using Java(c) by Oracle", new Vector2(50, 0));
        aotbsod=new Credit("An Agents of the BSoD Production 2012(c)", new Vector2(340,0));
        credits= new ArrayList<Credit>();
        
        credits.add(kyle);
        done=false;
    }
    
    public void start(){
        startTimer=System.currentTimeMillis();
    }
    
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
                    credits.add(MrCowal);
                    counter++;
                    startTimer=time;
                    break;
                case 7:
                    credits.add(Jesuit);
                    counter++;
                    startTimer=time;
                    break;
                case 8:
                    credits.add(oracle);
                    counter++;
                    startTimer=time;
                    break;
                case 9:
                    credits.add(aotbsod);
                    counter++;
                    startTimer=time;
                    break;
            }
        }
        
        for(Credit c: credits){
            if(c==aotbsod && aotbsod.getY()>=570 && !endinground){
                c.stop();
                endinground=true;
                startTimer=System.currentTimeMillis();
            }
            c.update();
        }
        if(time-startTimer>=10000 && endinground){
            done=true;
        }
    }
    
    public void draw(ImageCollection batch){
        for(Credit c: credits){
            c.draw(batch);
        }
    }
    
    public boolean isDone(KeyBoard k){
        return k.isKeyDown(KeyEvent.VK_SPACE) || done;
    }
}
