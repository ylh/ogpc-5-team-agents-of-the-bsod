/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Enemies.Enemy;
import Enemies.EnemyNavigation;
import Enemies.Spawner;
import Enemies.taylorEnemy;
import GUIStuff.Button;
import Credits.CreditScreen;
import GUIStuff.Draggable;
import GUIStuff.MenuButton;
import GUIStuff.ScrollingBackground;
import GUIStuff.Tile;
import Game.Game;
import KylesTesting.OpeningAnimation;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.SoundFile;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import WorldObjects.towers.Bullet;
import WorldObjects.towers.Road;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Nekel_Seyew, tsutton14, and Peter Cowal
 */
public class CityGame extends Game {

    public static int globalCount = 0;
    ArrayList<WorldObject> allObjects;
    public Tile[][] tiles;
    public ArrayList<Tower> activeTiles;
    public ArrayList<Button> buttons;
    public ArrayList<Road> roads;
    public double money = 100;
    public double score;
    public double happiness;
    public double polution;
    public boolean invOpen = false;
    Image2D Background = new Image2D("Game Resources/Sprites/GUIs/Background.PNG");
    Image2D bgtexture = new Image2D("Game Resources/Sprites/bgtexture.png");
    ScrollingBackground menuClouds=new ScrollingBackground("Game Resources/Sprites/Title Screen/gray-dee-ent.png",0.25);
    Image2D menuBackground=new Image2D("Game Resources/Sprites/Title Screen/tauers.png");
    public int buttonPressed;
    public Draggable drag;
    //opening animation controlls
    boolean firstRun;
    boolean JAVA = true;
    boolean firstwait = false;
    boolean secondwait = false;
    boolean AOTBSOD = false;
    double startTime;
    double javaLength = 18 * 200;
    double aLength = 33 * 150;
    double first = 1000;
    double second = 1000;
    OpeningAnimation java;
    OpeningAnimation aotbsod;
    SoundFile aww;
    boolean awwStarted = false;
    //Main Menu
    boolean mainMenu = false;
    MenuButton creds;
    MenuButton start;
    SoundFile mainMenuSong;
    boolean turnOnMenuSong=true;
    //credits
    boolean credits;
    CreditScreen creditScreen;
    SoundFile creditSong;
    boolean turnOnCreditSong=true;
    //PreGame
    boolean makeFirstRoad = true;
    //Main game needed
    Tile BottomRoad;
    double UpdateAllStart;
    Tile selection;
    Spawner spawn;
    EnemyNavigation navigator;
    boolean resetEnemies = true;

    @Override
    public void InitializeAndLoad() {
        //Core features creation.
        firstRun = true;
        this.gameSpeed = 32;
        allObjects = new ArrayList<WorldObject>();
        activeTiles = new ArrayList<Tower>();
        roads = new ArrayList<Road>();
        tiles = new Tile[854 / 32][632 / 32];
        buttons = new ArrayList<Button>();
        drag=null;

        //Game Score Variables
        money = 0;
        score = 0;
        polution = 0;
        happiness = 0;

        //The buttons
        addButtons();
        
        //Makes the Grid non-null
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
            }
        }

        //Creating the opening animation.
        java = new OpeningAnimation(new Vector2(970 / 2, 640 / 2), OpeningAnimation.JAVA);
        aotbsod = new OpeningAnimation(new Vector2(970 / 2, 640 / 2), OpeningAnimation.AOTBSOD);
        startTime = System.currentTimeMillis();
        aww = new SoundFile("Game Resources/Sound/AwwComeon.wav", 1);

        //Creates the Main Menu
        creds = new MenuButton(new Vector2(485, 500), MenuButton.CREDITS);
        start = new MenuButton(new Vector2(485, 450), MenuButton.START);
        mainMenuSong= new SoundFile("Game Resources/Sound/urban towers.wav",3);

        //Creates the credits
        creditScreen = new CreditScreen();
        creditSong=new SoundFile("Game Resources/Sound/creditsong.wav",2);

        //For the main game
        UpdateAllStart = startTime;//Convienience, means nothing really....
        spawn = new Spawner((1000) * 100, this, new Vector2(13 * 32 + 16, 18 * 32 + 16));
        
        //navigator = new EnemyNavigation(tiles, "Default", 10, 10);
    }

    @Override
    public void UnloadContent() {
    }

    @Override
    public void run() {
//        super.Run();
        this.base.theGUI.setSize(new Dimension(970, 640));
        this.base.theGUI.setResizable(false);
        this.base.theGUI.setTitle("Urban Towers");
    }

    @Override
    public void Update() {
        globalCount = 0;
        double time = System.currentTimeMillis();
        if (firstRun) {
            if (JAVA) {
                if (time - startTime >= javaLength || keyboard.isKeyDown(KeyEvent.VK_SPACE) || mouse.isPressed(Mouse.LEFT_BUTTON)) {
                    JAVA = false;
                    firstwait = true;
                    startTime = time;
                }
            } else if (firstwait) {
                if (time - startTime >= first) {
                    firstwait = false;
                    AOTBSOD = true;
                    startTime = time;
                }
            } else if (AOTBSOD) {
                if (time - startTime >= 750 && !awwStarted) {
                    awwStarted = true;
                    aww.start();
                }
                if (time - startTime >= aLength || keyboard.isKeyDown(KeyEvent.VK_SPACE) || mouse.isPressed(Mouse.LEFT_BUTTON)) {
                    AOTBSOD = false;
                    secondwait = true;
                    startTime = time;
                }
            } else if (secondwait) {
                if (time - startTime >= second) {
                    firstRun = false;
                    secondwait = false;
                    mainMenu = true;
                    aww = new SoundFile("Game Resources/Sound/AwwComeon.wav", 1);
                    startTime=time;
                }
            }
        } else if (mainMenu) {
            creds.update(mouse);
            start.update(mouse);
            menuClouds.scroll();
            if(turnOnMenuSong && time-startTime>300){
                mainMenuSong.start();
                turnOnMenuSong=false;
            }
            if (start.isPressed(mouse)) {
                mainMenuSong.kill();
                mainMenuSong= new SoundFile("Game Resources/Sound/urban towers.wav",3);
                mainMenu = false;
                turnOnMenuSong=true;
            }
            if (creds.isPressed(mouse)) {
                mainMenuSong.kill();
                mainMenuSong= new SoundFile("Game Resources/Sound/urban towers.wav",3);
                turnOnMenuSong=true;
                mainMenu = false;
                credits = true;
                creditScreen.start();
                startTime=time;
            }
        } else if (credits) {
            if(turnOnCreditSong && time-startTime>300){
                creditSong.start();
                turnOnCreditSong=false;
            }
                creditScreen.update();
            if (creditScreen.isDone(keyboard, mouse)) {
                creditSong.kill();
                credits = false;
                turnOnCreditSong=true;
                creditSong=new SoundFile("Game Resources/Sound/creditsong.wav",2);
                creditScreen = new CreditScreen();
                mainMenu = true;
                startTime=time;
            }
        } else {

            if (makeFirstRoad) {
                int i = 13, j = 18;
                Vector2 roadPos = new Vector2((i * 32), (j * 32));
                tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
                Road.setNeighbors(tiles, i, j);
                roads.add((Road) tiles[i][j]);
                makeFirstRoad = false;
                BottomRoad = tiles[i][j];                
                //navigator.start();
            }
            spawn.update(keyboard);

            for (int i = 0; i < allObjects.size(); i++) {
                WorldObject wo = allObjects.get(i);
                wo.Update(allObjects);
                if (wo instanceof taylorEnemy) {
                    taylorEnemy e = (taylorEnemy) wo;
                    if(resetEnemies){
                        //e.setEnemyPath(navigator);
                        System.out.println("Set path!");
                        resetEnemies = false;
                    }
                    if (wo.getPosition().getY() < 0) {
                        allObjects.remove(wo);
                        score -= ((Enemy) wo).getScore();
                    }
                }
                if (wo instanceof Enemy) {
                    Enemy e = (Enemy) wo;
                    e.updatePath2(tiles);
                    if (e.isDead()) {
                        e.die(this);
                        allObjects.remove(e);
                        new SoundFile("Game Resources/Sound/destroy2.wav",1).start();
                    }
                }
                
                if (wo instanceof Bullet) {
                    Bullet b = (Bullet) wo;
                    //System.out.println("It's a bullet!");
                    if (b.HasReachedTarget()) {
                        b.HitTarget();
                        allObjects.remove(b);
                    }
                }
                globalCount++;
            }

            

            for (Button b : buttons) {
                b.glide();
                if (b.isPressed(mouse)) {
                    if (b.id()>-1 && drag==null){
                    drag=new Draggable(b.getPath(),mouse.location(),b.id());
                    }
                    else if (b.id()==-1){
                        invOpen=false;
                    }
                }
                globalCount++;
            }
            for (Button b : buttons) {

                b.setOpen(invOpen);
                globalCount++;
            }
            if (buttonPressed >= 0 && drag==null){
                
            }
            //Needed because we can't have it in the loop
            if (buttonPressed == -1) {
                invOpen = false;
            }
            if (drag != null) {
                drag.update(mouse);
            }
            if (!mouse.isPressed(Mouse.LEFT_BUTTON)) {
                if(drag!=null){
                    int x=((int)mouse.location().getX())/32;
                    int y=((int)mouse.location().getY())/32;
                    if (insideBounds(x,y)&&!(tiles[x][y] instanceof Road) && !(tiles[x][y] instanceof Tower) 
                            && x<tiles.length && y<tiles[0].length && x>-1 && y>-1) {
                        if (money > -5000) {
                            tiles[x][y] = drag.getTower(new Rect(x, y, 32, 32));
                            this.activeTiles.add((Tower) tiles[x][y]);
                            money -= ((Tower) tiles[x][y]).getCost();
                            new SoundFile("Game Resources/Sound/build.wav", 1).start();
                        }
                    }
                }
                drag = null;
            }
            
            
            //ORDER HERE COUTNS!!!
            double thisLoopTime = System.currentTimeMillis();
            boolean updateAll=false;
            if (thisLoopTime - this.UpdateAllStart >= 30000) {
                updateAll = true;
                UpdateAllStart = thisLoopTime;
            }
            for (Tower t : activeTiles) {
                t.Update(allObjects);

                //For updating score, happiness, polution, and money
                if (updateAll) {
                    t.updateGameStats(this);
                }

                globalCount++;
            }
            if(updateAll){
                score=getScore();
                updateAll=false;
            }
            
            
            if (keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
                int i = 0;
            }
            //Mouse update methods in grid, the 832 should be most left pixel of the grid
            if (mouse.isPressed(Mouse.LEFT_BUTTON) && insideBounds(mouse.location())) {
                try {
                    if (drag == null) {
                        invOpen = true;
                    }
                    int x = (int) mouse.location().getX();
                    int y = (int) mouse.location().getY();

                    int i = x / 32;
                    int j = y / 32;

                    Rect b = new Rect(new Vector2(i * 32, j * 32), 32, 32);

                    if (selection == null) {
                        selection = tiles[i][j];
                        selection.select(tiles);

                    } else {
                        selection.unselect(tiles);
                        selection = tiles[i][j];
                        selection.select(tiles);
                        //invOpen = false;
                    }

                    if (b.contains(x, y) && keyboard.isKeyDown('r') && mouse.isPressed(Mouse.LEFT_BUTTON)&&keyboard.isKeyUp('d') && money > -5000) {
                        if (!(tiles[i][j] instanceof Road)) {
                            Vector2 roadPos = new Vector2((i * 32), (j * 32));
                            tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
                            Road.setNeighbors(tiles, i, j);
                            roads.add((Road) tiles[i][j]);
                            resetEnemies = true;
                            money -= 5;
                            selection = null;
                            
                        }
                    }
                    if (b.contains(x, y) && keyboard.isKeyDown('d') && mouse.isPressed(Mouse.LEFT_BUTTON) && keyboard.isKeyUp('r') && money> -5000) {
                        if (!(tiles[i][j] == BottomRoad) && (tiles[i][j] instanceof Road)) {
                            new SoundFile("Game Resources/Sound/destroy1.wav",1).start();
                            roads.remove(tiles[i][j]);
                            Road.setNeighbors(tiles, i, j);
                            tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
                            Road.setNeighbors(tiles, i, j);
                            resetEnemies = true;
                            money -= 5;
                            selection = null;
                        }
                        if ((tiles[i][j] instanceof Tile) && !(tiles[i][j] == BottomRoad)) {
                            if (activeTiles.contains(tiles[i][j])) {
                                new SoundFile("Game Resources/Sound/destroy1.wav",1).start();
                                activeTiles.remove(tiles[i][j]);
                                money -= 5;
                                selection = null;
                                Road.setNeighbors(tiles, i, j);//just to be safe
                                tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
                                resetEnemies = true;
                            } else {
                                //DO NOTHING!!!!
                            }
                        }
                    }
                } catch (Exception e) {
                    if(e instanceof ArrayIndexOutOfBoundsException){
                        e.printStackTrace();
                    }else{
                        e.printStackTrace();
                    }
                }
            }//End of mouse pressed
        }// End of Main Game Else
    }

    @Override
    public void Draw(Graphics g) {
        if (firstRun) {
            this.setBackground(Color.black);
            if (JAVA) {
                java.draw(batch);
            } else if (AOTBSOD) {
                aotbsod.draw(batch);
            }
        } else if (mainMenu) {
            batch.Draw(menuBackground,new Vector2(485,320),10);
            creds.draw(batch);
            start.draw(batch);
            menuClouds.draw(batch); 
        } else if (credits) {
            creditScreen.draw(batch);
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
            if (drag!=null) drag.draw(batch);

            //money drawing
            if (money < 0) {
                batch.DrawString(new Vector2(850, 30), "Money: $" + money, Color.ORANGE, 10, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            } else {
                batch.DrawString(new Vector2(850, 30), "Money: $" + money, Color.black, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            }
            //Polution
            batch.DrawString(new Vector2(850, 45), "Pollution: " + polution, Color.ORANGE, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 10);
            
            //Score
            if (score < 0) {
                batch.DrawString(new Vector2(850, 75), "Score: " + score, Color.ORANGE, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            } else {
                batch.DrawString(new Vector2(850, 75), "Score: " + score, Color.black, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            }
            //Happiness
            if (happiness < 0) {
                batch.DrawString(new Vector2(850, 60), "Happiness: " + happiness, Color.ORANGE, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            } else {
                batch.DrawString(new Vector2(850, 60), "Happiness: " + happiness, Color.black, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            }

            batch.DrawString(new Vector2(835, 600), "Next Round: " + (int) this.spawn.getRemainingTime() / 1000 + " s", Color.black, 10);

            //the Background Grid
            batch.Draw(Background, new Vector2(835 / 2, 611 / 2), 1);
            batch.Draw(bgtexture, new Vector2(970 / 2, 611 / 2), 0);

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
            //Pure Debugging
            //System.out.println(globalCount);
        }//End of Main Game Else
    }

    /**
     * Sort of self explanatory
     * @return the score.
     */
    private double getScore() {
        return score + (money +happiness + polution)/3;
    }
    
    private void adjustInPath(boolean deleting, int x, int y) {
        if (!deleting) {
            if (tiles[x][y] instanceof Road) {
                Road r = (Road) tiles[x][y];
                if (r.getInPath()) {
                    if (x + 1 < tiles.length) {
                        if (tiles[x + 1][y] instanceof Road) {
                            Road r2 = (Road) tiles[x + 1][y];
                            r2.setInPath(true);
                        }
                    }
                    if (x - 1 >= 0) {
                        if (tiles[x - 1][y] instanceof Road) {
                            Road r2 = (Road) tiles[x - 1][y];
                            r2.setInPath(true);
                        }
                    }
                    if (y + 1 < tiles[0].length) {
                        if (tiles[x][y + 1] instanceof Road) {
                            Road r2 = (Road) tiles[x][y + 1];
                            r2.setInPath(true);
                        }
                    }
                    if (y - 1 >= 0) {
                        if (tiles[x][y - 1] instanceof Road) {
                            Road r2 = (Road) tiles[x][y - 1];
                            r2.setInPath(true);
                        }
                    }

                }
            }
        }
        if (deleting) {
            if (tiles[x][y] instanceof Road) {
                Road r = (Road) tiles[x][y];
                if (x + 1 < tiles.length) {
                    if (tiles[x + 1][y] instanceof Road) {
                        Road r2 = (Road) tiles[x + 1][y];
                        if (stillPath(x + 2, y) || stillPath(x + 1, y - 1) || stillPath(x + 1, y + 1)) {
                            r2.setInPath(true);
                        } else {
                            r2.setInPath(false);
                        }
                    }
                }
                if (x - 1 >= 0) {
                    if (tiles[x - 1][y] instanceof Road) {
                        Road r2 = (Road) tiles[x - 1][y];
                        if (stillPath(x - 2, y) || stillPath(x + 1, y - 1) || stillPath(x + 1, y + 1)) {
                            r2.setInPath(true);
                        } else {
                            r2.setInPath(false);
                        }
                    }
                }
                if (y + 1 < tiles[0].length) {
                    if (tiles[x][y + 1] instanceof Road) {
                        Road r2 = (Road) tiles[x][y + 1];
                        if (stillPath(x + 1, y) || stillPath(x - 1, y) || stillPath(x, y + 2)) {
                            r2.setInPath(true);
                        } else {
                            r2.setInPath(false);
                        }
                    }
                }
                if (y - 1 >= 0) {
                    if (tiles[x][y - 1] instanceof Road) {
                        Road r2 = (Road) tiles[x][y - 1];
                        if (stillPath(x + 1, y) || stillPath(x - 1, y) || stillPath(x, y - 2)) {
                            r2.setInPath(true);
                        } else {
                            r2.setInPath(false);
                        }
                    }
                }
            }
        }
    }
    
        public boolean stillPath(int x, int y){
        if(x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length){
            if(tiles[x][y] instanceof Road){
                Road r1;
                Road r2;
                Road r3;
                Road r4;
                boolean path1 = false, path2 = false, path3 = false, path4 = false;
                if(x - 1 >= 0){
                    r1 = (Road)tiles[x-1][y];
                    path1 = r1.getInPath();
                }
                if(x + 1 < tiles.length){
                    r2 = (Road)tiles[x+1][y];
                    path2 = r2.getInPath();
                }   
                if(y - 1 >= 0){
                    r3 = (Road)tiles[x][y-1];
                    path3 = r3.getInPath();
                }   
                if(y + 1 < tiles[0].length){
                    r4 = (Road)tiles[x][y+1];
                    path4 = r4.getInPath();
                }   
                if(path1 || path2 || path3 || path4){
                    return true;
                }
            }
        }
        return false;
    }

    public void addToWorldObjects(WorldObject wo) {
        allObjects.add(wo);
    }

    public static Rect getRectangle(Image2D i2d) {
        return new Rect((int) (i2d.getPosition().getX() - i2d.getIconWidth() / 2),
                (int) (i2d.getPosition().getY() - i2d.getIconHeight() / 2),
                i2d.getIconWidth(), i2d.getIconHeight());
    }

    private void save() {
        String path = "Game Resources/Saved Files/";
        File allSavedFiles = new File(path + "SavedFilesHead.txt");
    }

    private void load() {
        String path = "Game Resources/Saved Files/";
        File allSavedFiles = new File(path + "SavedFilesHead.txt");
    }
    public void addButtons(){
        //factory
        buttons.add(new Button(Tower.FACTORY, "Game Resources/Sprites/SamSprites/Towers/Factory/3factory32x32.png", 880, 100));
        //button 2
        buttons.add(new Button(Tower.GREEN_BELT, "Game Resources/Sprites/Liam's Sprites/Towers/Park/park2-1.png", 920,100));
        //button 3
        buttons.add(new Button(Tower.HOUSE, "Game Resources/Sprites/Liam's Sprites/Towers/House/house1-1.png", 880, 140));
        //button 4
        buttons.add(new Button(Tower.MONUMENT, "Game Resources/Sprites/SamSprites/Towers/Monuments/monument0.png", 920, 140));
        //button 5
        buttons.add(new Button(Tower.POLICE_FIRE_STATION, "Game Resources/Sprites/August/Police Station 32x32.png", 880, 180));
        //button 6
        buttons.add(new Button(Tower.RECYCLING_CENTER, "Game Resources/Sprites/August/Recycling Center.png", 920, 180));
        //button 7
        buttons.add(new Button(Tower.STORE, "Game Resources/Sprites/August/Supermarket 32x32.png", 880, 220));
        //button 8
        //buttons.add(new Button(Tower.WATER_PURIFICATION_CENTER, "Game Resources/Sprites/Liam's Sprites/Towers/Police/save2.png", 920, 220));
        //delete
        buttons.add(new Button(-1, "Game Resources/Sprites/GUIS/deleteButton.png", 900, 260));

    }
    
    public boolean insideBounds(int x, int y){
        return (x>=0 && x<830) && (y>=0 && y<600);
    }
    public boolean insideBounds(Vector2 pos){
        int x=(int)pos.getX();
        int y=(int)pos.getY();
        return insideBounds(x,y);
    }
}
