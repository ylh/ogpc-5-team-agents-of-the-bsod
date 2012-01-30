/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Game.Game;
import GamePlay.GuiBuilder;
import java.awt.Graphics;
import java.io.File;

/**
 *
 * @author Nekel_Seyew
 */
public class CityGame extends Game{
    
    GuiBuilder gb;

    @Override
    public void InitializeAndLoad() {
        gb=new GuiBuilder(mouse);
        this.addKeyListener(gb);
        this.addMouseListener(gb);
        this.addMouseMotionListener(gb);
        this.addMouseWheelListener(gb);
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
        
    }

    @Override
    public void Draw(Graphics g) {
        gb.Draw(batch);
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
