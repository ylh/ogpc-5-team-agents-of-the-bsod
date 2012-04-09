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
public class OpeningAnimation {
    
    public static final int JAVA=0;
    public static final int AOTBSOD=1;
    
    Animation a;
    Vector2 position;
    int type;
    
    public OpeningAnimation(Vector2 pos, int type){
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
        if(t==JAVA){
            a=new Animation(18,10,position,200);
            addJavaCell(1);
            addJavaCell(2);
            addJavaCell(3);
            addJavaCell(4);
            addJavaCell(5);
            addJavaCell(6);
            addJavaCell(7);
            addJavaCell(8);
            addJavaCell(9);
            addJavaCell(9);
            addJavaCell(8);
            addJavaCell(7);
            addJavaCell(6);
            addJavaCell(5);
            addJavaCell(4);
            addJavaCell(3);
            addJavaCell(2);
            addJavaCell(1);
        }else if(t==AOTBSOD){
            a=new Animation(33,10000,position,150);
            addBSODCell(1);
            addBSODCell(1);
            addBSODCell(1);
            addBSODCell(1);
            addBSODCell(1);
            addBSODCell(2);
            addBSODCell(3);
            addBSODCell(2);
            addBSODCell(3);
            addBSODCell(4);
            addBSODCell(5);
            addBSODCell(6);
            addBSODCell(7);
            addBSODCell(8);
            addBSODCell(9);
            addBSODCell(8);
            addBSODCell(9);
            addBSODCell(8);
            addBSODCell(9);
            addBSODCell(8);
            addBSODCell(9);
            addBSODCell(8);
            addBSODCell(9);
            addBSODCell(8);
            addBSODCell(7);
            addBSODCell(6);
            addBSODCell(5);
            addBSODCell(10);
            addBSODCell(11);
            addBSODCell(12);
            addBSODCell(13);
            addBSODCell(14);
            addBSODCell(15);//33
        }
    }
    private void addJavaCell(int t){
        a.addCell("Game Resources/Sprites/Opening Animation/JAVA/java"+t+".png");
    }
    private void addBSODCell(int t){
        a.addCell("Game Resources/Sprites/Opening Animation/AOFBSOD/computer"+t+".png");
    }
    
}
