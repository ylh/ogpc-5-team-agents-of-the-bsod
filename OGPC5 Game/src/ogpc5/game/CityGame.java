/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Game.Game;
import java.awt.Graphics;
import java.io.File;

/**
 *
 * @author Nekel_Seyew
 */
public class CityGame extends Game{

    @Override
    public void InitializeAndLoad() {
        
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
