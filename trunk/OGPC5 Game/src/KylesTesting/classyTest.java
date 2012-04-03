/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KylesTesting;

import Game.Game;
import Utilities.Mouse;
import Utilities.Vector2;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Ksweeney12
 */
public class classyTest extends Game{
    
    Block[][] theGrid;
    Block selected;

    @Override
    public void InitializeAndLoad() {
        theGrid=new Block[40][40];
        selected = new Block(new Vector2());
        
    }

    @Override
    public void UnloadContent() {
        
    }

    @Override
    public void Update() {
        
        int row=(int)mouse.location().getY()/32;
        int col=(int)mouse.location().getX()/32;
        
        if(mouse.isPressed(Mouse.LEFT_BUTTON)){
            
            if (keyboard.isKeyDown('r')) {
                if (theGrid[row][col] == null) {
                    theGrid[row][col] = new Block(new Vector2(col * 32 +16, row * 32+16));
                }else{
                    theGrid[row][col] = new Block(new Vector2(col * 32 +16, row * 32+16));
                }
            }
            
            if(theGrid[row][col]!=null){
                selected.selected=false;
                selected=theGrid[row][col];
                selected.selected=true;
            }else{
                selected.selected=false;
                theGrid[row][col]= new Block(Block.SELECTION, new Vector2(col * 32 +16, row * 32+16));
                selected=theGrid[row][col];
                selected.selected=true;
            }
        }
        
    }

    @Override
    public void Draw(Graphics grphcs) {
        for(Block[] line: theGrid){
            for(Block b: line){
                if(b!=null){
                    b.draw(batch);
                }
            }
        }
        
    }

    @Override
    public void run() {
        
    }
    
}
