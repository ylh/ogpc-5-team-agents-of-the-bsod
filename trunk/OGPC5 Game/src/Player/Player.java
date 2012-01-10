/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Utilities.Animation;
import Utilities.Vector2;
import java.util.ArrayList;
import ogpc5.game.WorldObjects.WorldObject;

/**
 *
 * @author Nekel_Seyew
 */
public class Player {
    protected Vector2 position;
    protected int direction;//1=left, 2= right, 3=up, 4= down
    //Animations
    protected Animation walkLeft;
    protected Animation walkRight;
    protected Animation walkUp;
    protected Animation walkDown;
    protected Animation stillLeft;
    protected Animation stillRight;
    protected Animation stillUp;
    protected Animation stillDown;
    
    public Player(Vector2 position, int direction){
        this.position=position;
        this.direction=direction;
        this.getAnimations();
    }
    
    public void update(ArrayList<WorldObject> worldObjects){
        
    }
    
    //TODO: create all of the animations
    private void getAnimations(){
        
    }
}
