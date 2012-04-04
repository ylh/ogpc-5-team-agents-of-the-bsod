package KylesTesting;

import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ksweeney12
 */
public class ClosingAnimation {
    
    public static final int CLUB=0;
    public static final int TEAM=1;
    
    Animation a;
    Vector2 position;
    int type;
    
    public ClosingAnimation(Vector2 pos, int type){
        position=pos;
        this.type=type;
        loadAnimation(type);
    }
    
    public void update(){
        //do nothing
    }
    
    public void draw(ImageCollection batch){
        a.Draw(batch);
    }
    
    private void loadAnimation(int t){
        if(t==CLUB){
            a=new Animation(20,10,position,200);
            a.addCell("Game Resources/Sprites/Credits/Club1.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club2.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club3.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club4.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club5.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club6.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club7.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club7.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club6.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club5.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club4.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club3.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club2.jpg");
            a.addCell("Game Resources/Sprites/Credits/Club1.jpg");
        }else if(t==TEAM){
            a=new Animation(33,10000,position,150);
        }
    }
    private void addJavaCell(int t){
        a.addCell("Game Resources/Sprites/Opening Animation/JAVA/java"+t+".png");
    }
    private void addBSODCell(int t){
        a.addCell("Game Resources/Sprites/Opening Animation/AOFBSOD/computer"+t+".png");
    }
    
}
