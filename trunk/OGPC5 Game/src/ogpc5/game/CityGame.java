/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Enemies.Enemy;
import Enemies.Spawner;
import GUIStuff.Button;
import GUIStuff.Tile;
import Game.Game;
import KylesTesting.OpeningAnimation;
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
 * @author Nekel_Seyew, tsutton14, and Peter Cowal
 */
public class CityGame extends Game{
    
    public static int globalCount=0;
    
    ArrayList<WorldObject> allObjects;
    public Tile[][] tiles;
    public ArrayList<Tile> activeTiles;
    public ArrayList<Button> buttons;
    public ArrayList<Road> roads;
    public double money;
    public double score;
    public boolean invOpen=false;
    Image2D Background= new Image2D("Game Resources/Sprites/GUIs/Background.PNG");
    
    //opening animation controlls
    boolean firstRun;
    boolean JAVA=true;
    boolean firstwait=false;
    boolean secondwait=false;
    boolean AOTBSOD=false;
    double start;
    double javaLength=18*100;
    double aLength=33*75;
    double first=1000;
    double second=1000;
    OpeningAnimation java;
    OpeningAnimation aotbsod;
    
    
    Tile selection;

    @Override
    public void InitializeAndLoad() {
        firstRun=true;
        this.gameSpeed=32;
        allObjects= new ArrayList<WorldObject>();
        activeTiles= new ArrayList<Tile>();
        roads= new ArrayList<Road>();
        tiles= new Tile[854/32][632/32];
        buttons=new ArrayList<Button>();
        
        buttons.add(new Button(new Vector2(900,100),new Vector2(900,-40),0.1));
        
        buttons.add(new Button(new Vector2(900,200),new Vector2(900,-40),0.1));
        
        buttons.add(new Button(new Vector2(900,300),new Vector2(900,-40),0.1));
        
        for(int i=0; i<tiles.length; i++){
            for(int j=0; j<tiles[i].length; j++){
                tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
            }
        }
        java=new OpeningAnimation(new Vector2(970/2,640/2), OpeningAnimation.JAVA);
        aotbsod=new OpeningAnimation(new Vector2(970/2,640/2), OpeningAnimation.AOTBSOD);
        start=System.currentTimeMillis();
        
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
        globalCount=0;
        
        if(firstRun){
            double time=System.currentTimeMillis();
            if(JAVA){
                if(time-start>=javaLength){
                    JAVA=false;
                    firstwait=true;
                    start=time;
                }
            }else if(firstwait){
                if(time-start>=first){
                    firstwait=false;
                    AOTBSOD=true;
                    start=time;
                }
            }
            else if(AOTBSOD){
                if(time-start>=aLength){
                    AOTBSOD=false;
                    secondwait=true;
                    start=time;
                }
            }else if(secondwait){
                if(time-start>=second){
                    firstRun=false;
                    secondwait=false;
                }
            }
        } else {
            //Makes it really slow...
//        if(firstRun){
//            int i=13, j=18;
//            Vector2 roadPos = new Vector2((i * 32), (j * 32));
//            tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
//            Road.setNeighbors(tiles, i, j);
//            activeTiles.add(tiles[i][j]);
//            firstRun=false;
//        }
//        firstRun=false;

            for (WorldObject wo : allObjects) {
                wo.Update(allObjects);
                globalCount++;
            }

            for (Button b : buttons) {
                b.glide();
                if (b.isPressed(mouse)) {
                    invOpen = false;
                    selection = null;
                }
                globalCount++;
            }
            for (Button b : buttons) {

                b.setOpen(invOpen);
                globalCount++;
            }

            for (Tile t : activeTiles) {
                t.Update(allObjects);
                globalCount++;
            }

            if (mouse.isPressed(Mouse.LEFT_BUTTON) && mouse.location().getX() < 854) {
                invOpen = true;
                int x = (int) mouse.location().getX();
                int y = (int) mouse.location().getY();

                int i = x / 32;
                int j = y / 32;

                Rect b = new Rect(new Vector2(i * 32, j * 32), 32, 32);

                if (selection == null) {
                    selection = (Tile) tiles[i][j];
                    selection.select();

                } else {
                    selection.unselect();
                    selection = (Tile) tiles[i][j];
                    selection.select();
                    //invOpen = false;
                }

                if (b.contains(x, y) && keyboard.isKeyDown('r') && mouse.isPressed(Mouse.LEFT_BUTTON)) {
                    if (!(tiles[i][j] instanceof Road)) {
                        Vector2 roadPos = new Vector2((i * 32), (j * 32));
                        tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
                        Road.setNeighbors(tiles, i, j);
                        roads.add((Road) tiles[i][j]);
                        //activeTiles.add(tiles[i][j]);
                        if ((i + 1) >= tiles.length) {
                            //ADD SPAWNER CODE
                        }
                    }
                }
                if (b.contains(x, y) && keyboard.isKeyDown('d') && mouse.isPressed(Mouse.LEFT_BUTTON)) {
                    //activeTiles.remove(tiles[i][j]);
                    roads.remove(tiles[i][j]);
                    Road.setNeighbors(tiles, i, j);
                    tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
                    resetPaths();
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
    }

    @Override
    public void Draw(Graphics g) {
        if(firstRun){
            this.setBackground(Color.black);
            if(JAVA){
                java.draw(batch);
            }else if(AOTBSOD){
                aotbsod.draw(batch);
            }
        } else {
            this.setBackground(Color.white);
            for (WorldObject wo : allObjects) {
                wo.Draw(batch);
                globalCount++;
            }
            for (Button b : buttons) {
                b.draw(batch);
                globalCount++;
            }


            batch.Draw(Background, new Vector2(835 / 2, 611 / 2), 0);

            for (Tile t : activeTiles) {
                t.Draw(batch);
                globalCount++;
            }

            for (Road r : roads) {
                r.Draw(batch);
                globalCount++;
            }

            if (selection != null) {
                selection.Draw(batch);
            }
            System.out.println(globalCount);
        }
    }
    
    private void resetPaths(){
        for(WorldObject b: allObjects){
            if (b instanceof Enemy){
                Enemy e = (Enemy)b;
                e.setEnemyPath(tiles);
            }
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
