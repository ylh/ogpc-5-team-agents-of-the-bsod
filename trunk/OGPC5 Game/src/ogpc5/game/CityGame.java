/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import GUIStuff.Tile;
import Game.Game;
import Utilities.Image2D;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Nekel_Seyew
 */
public class CityGame extends Game{
    
    ArrayList<WorldObject> allObjects;
    public Tower[][] towers;
    
    public double money;
    public double score;
    
    Tile selection;

    @Override
    public void InitializeAndLoad() {
        allObjects= new ArrayList<WorldObject>();
        towers = new Tower[854/32][632/32];
        for(int i=0; i<towers.length; i++){
            for(int j=0; j<towers[i].length; j++){
                towers[i][j]=new Tile(new Vector2(i*32, j*32));
            }
        }
    }

    @Override
    public void UnloadContent() {
        
    }
    
    @Override
    public void Run(){
        super.Run();
        this.base.theGUI.setSize(new Dimension(970,640));
        this.base.theGUI.setResizable(false);
    }

    @Override
    public void Update() {
        for(WorldObject wo: allObjects){
            wo.Update(allObjects);
        }
        for(int i=0; i<towers.length; i++){
            for(int j=0; j<towers[i].length; j++){
                if(towers[i][j]==null){
                    continue;
                }else{
                    Rect b = new Rect(new Vector2(i * 32, j * 32), 32, 32);
                    if (mouse.isPressed(Mouse.LEFT_BUTTON) && b.contains(mouse.location().getX(), mouse.location().getY())) {
                        if(selection==null){
                            selection=(Tile)towers[i][j];
                            selection.select();
                        }else{
                            selection.unselect();
                            selection=(Tile)towers[i][j];
                            selection.select();
                        }
                    }
                    towers[i][j].Update(allObjects);
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        for(WorldObject wo: allObjects){
            wo.Draw(batch);
        }
        for(int i=0; i<towers.length; i++){
            for(int j=0; j<towers[i].length; j++){
                if(towers[i][j]==null){
                    continue;
                }else{
                    towers[i][j].Draw(batch);
                }
            }
        }if(selection!=null){
            selection.Draw(batch);
        }
    }
    
    public void addToWorldObjects(WorldObject wo){
        allObjects.add(wo);
    }
    
    public static Rect getRectangle(Image2D i2d){
        return new Rect((int)(i2d.getPosition().getX()-i2d.getIconWidth()/2), 
                (int)(i2d.getPosition().getY()-i2d.getIconHeight()/2), 
                i2d.getIconWidth(), i2d.getIconHeight());
    }
    
    private void save(){
        String path="Saved Files/";
        File allSavedFiles= new File(path+"SavedFilesHead.txt");
    }
    private void load(){
        String path="Saved Files/";
        File allSavedFiles= new File(path+"SavedFilesHead.txt");
    }
    
}
