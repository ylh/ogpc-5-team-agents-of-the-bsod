/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Enemies.Spawner;
import GUIStuff.Button;
import GUIStuff.Tile;
import Game.Game;
import Utilities.Image2D;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import Utilities.KeyBoard;
import WorldObjects.WorldObject;
import WorldObjects.towers.Road;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Nekel_Seyew, Tsutton14, and Peter Cowal
 */
public class CityGame extends Game{
    
    ArrayList<WorldObject> allObjects;
    public Tile[][] tiles;
    public ArrayList<Tile> activeTiles;
    public ArrayList<Button> buttons;
    public double money;
    public double score;
    public boolean invOpen=false;
    Image2D Background= new Image2D("Game Resources/Sprites/GUIs/Background.PNG");
    
    //LOOK FOR ROAD STUFF IN ROAD CLASS
    
    
    Tile selection;

    @Override
    public void InitializeAndLoad() {
        this.gameSpeed=32;
        allObjects= new ArrayList<WorldObject>();
        activeTiles= new ArrayList<Tile>();
        tiles= new Tile[854/32][632/32];
        buttons=new ArrayList<Button>();
        
        buttons.add(new Button(new Vector2(900,100),new Vector2(900,-20),0.1));
        
        buttons.add(new Button(new Vector2(900,200),new Vector2(900,-20),0.1));
        
        buttons.add(new Button(new Vector2(900,300),new Vector2(900,-20),0.1));
        
        for(int i=0; i<tiles.length; i++){
            for(int j=0; j<tiles[i].length; j++){
                tiles[i][j]=new Tile(new Vector2(i*32, j*32));
            }
        }
        
    }

    @Override
    public void UnloadContent() {
        
    }
    
    @Override
    public void run(){
//        super.Run();
        this.base.theGUI.setSize(new Dimension(970,640));
        this.base.theGUI.setResizable(false);
    }

    @Override
    public void Update() {
        
        for(WorldObject wo: allObjects){
            wo.Update(allObjects);
        }
        
        for(Button b : buttons){
            b.glide();
            if (b.isPressed(mouse)){
                invOpen = false;
                selection=null;
            }
            
        }
        for(Button b : buttons){
            
            b.setOpen(invOpen);
            
        }
        for(Tile t: activeTiles){
            t.Update(allObjects);
        }
        
        if (mouse.isPressed(Mouse.LEFT_BUTTON)&&mouse.location().getX()<854) {
            invOpen = true;
            int x = (int) mouse.location().getX();
            int y = (int) mouse.location().getY();

            int i = x / 32;
            int j = y / 32;

            Rect b = new Rect(new Vector2(i * 32, j * 32), 32, 32);

            if (selection == null) {
                selection = (Tile)tiles[i][j];
                selection.select();
                
            } else {
                selection.unselect();
                selection =(Tile)tiles[i][j];
                selection.select();
                //invOpen = false;
            }

            if (b.contains(x, y) && keyboard.isKeyDown('r') && mouse.isPressed(Mouse.LEFT_BUTTON)) {
                if (!(tiles[i][j] instanceof Road)) {
                    Vector2 roadPos = new Vector2((i * 32), (j * 32));
                    tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
                    Road.setNeighbors(tiles, i, j);
                    activeTiles.add(tiles[i][j]);
                    if ((i + 1) >= tiles.length) {
                        //ADD SPAWNER CODE
                    }
                }
            }
        }
        
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[i].length; j++) {
//                Rect b = new Rect(new Vector2(i * 32, j * 32), 32, 32);
//                double x= mouse.location().getX();
//                double y=mouse.location().getY();
//                if (b.contains(x, y) && keyboard.isKeyDown('r') && mouse.isPressed(Mouse.LEFT_BUTTON)) {
//                    if (!(tiles[i][j] instanceof Road)) {
//                        Vector2 roadPos = new Vector2(i * 32, j * 32);
//                        tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
//                        Road.setNeighbors(tiles, i, j);
//                        if ((i + 1) >= tiles.length) {
//                            //spawnController.add();
//                        }
//                    }
//                }
//
//                if (mouse.isPressed(Mouse.LEFT_BUTTON) && b.contains(x, y)) {
//                    if (selection == null) {
//                        selection = (Tile) tiles[i][j];
//                        selection.select();
//                        invOpen = true;
//                    } else {
//                        selection.unselect();
//                        selection = (Tile) tiles[i][j];
//                        selection.select();
//                        invOpen = false;
//                    }
//                }
//                
//                tiles[i][j].Update(allObjects);
//
//            }
//        }
    }

    @Override
    public void Draw(Graphics g) {
        for(WorldObject wo: allObjects){
            wo.Draw(batch);
        }
        for (Button b: buttons){
            b.draw(batch);
        }
        batch.Draw(Background, new Vector2(835/2,611/2), 0);
        
        for(Tile t: activeTiles){
            t.Draw(batch);
        }
        if(selection!=null){
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
        String path="Game Resources/Saved Files/";
        File allSavedFiles= new File(path+"SavedFilesHead.txt");
    }
    private void load(){
        String path="Game Resources/Saved Files/";
        File allSavedFiles= new File(path+"SavedFilesHead.txt");
    }

}
