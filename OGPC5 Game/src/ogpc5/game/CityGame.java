/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Game.Game;
import GamePlay.GuiBuilder;
import WorldObjects.WorldObject;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Nekel_Seyew
 */
public class CityGame extends Game{
    
    GuiBuilder gb;
    ArrayList<WorldObject> allObjects;

    @Override
    public void InitializeAndLoad() {
        gb=new GuiBuilder(mouse,this);
        this.addKeyListener(gb);
        this.addMouseListener(gb);
        this.addMouseMotionListener(gb);
        this.addMouseWheelListener(gb);
        allObjects= new ArrayList<WorldObject>();
    }

    @Override
    public void UnloadContent() {
        
    }
    
    @Override
    public void Run(){
        super.Run();
    }

    @Override
    public void Update() {
        gb.update();
        for(WorldObject wo: allObjects){
            wo.Update(allObjects);
        }
    }

    @Override
    public void Draw(Graphics g) {
        for(WorldObject wo: allObjects){
            wo.Draw(batch);
        }
        gb.Draw(batch);
    }
    
    public void addToWorldObjects(WorldObject wo){
        allObjects.add(wo);
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
