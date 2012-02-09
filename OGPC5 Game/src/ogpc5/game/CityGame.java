/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Game.Game;
import GamePlay.GuiBuilder;
import Utilities.Image2D;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import WorldObjects.towers.Tower;
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
    
    GuiBuilder gb;
    ArrayList<WorldObject> allObjects;
    Tower[][] towers;
    
    public double money;
    public double score;

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
        //this.base.theGUI.setExtendedState(Frame.MAXIMIZED_BOTH);//OPTIONAL
        //TODO: determine max size.
        this.base.theGUI.setMaximizedBounds(new Rect(new Vector2(0,0), 860, 660));
        this.base.theGUI.setMinimumSize(new Dimension(860,660));
        this.base.theGUI.setResizable(false);
    }

    @Override
    public void Update() {
        if(gb==null){
            InitializeAndLoad(); 
        }
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