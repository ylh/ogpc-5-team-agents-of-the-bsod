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
            
        }else if(t==AOTBSOD){
            
        }
    }
    
}
