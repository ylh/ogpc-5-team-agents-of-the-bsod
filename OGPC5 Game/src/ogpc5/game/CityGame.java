/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Enemies.AbstractEnemy;
import Enemies.EnemyNavigation;
import Enemies.Spawner;
import Enemies.Enemy;
import GUIStuff.Button;
import Credits.CreditScreen;
import EasterEggs.Achievements;
import EasterEggs.BlueScreen;
import GUIStuff.Draggable;
import GUIStuff.EndScreen;
import GUIStuff.MenuButton;
import GUIStuff.ScrollingBackground;
import GUIStuff.Tile;
import GUIStuff.Tutorial;
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
import WorldObjects.towers.Monument;
import WorldObjects.towers.Road;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;

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
    //Tutorial
    boolean startTutorial=false;
    boolean tutorial=false;
    MenuButton skip;
    MenuButton yes;
    Tutorial tut;
    //PreGame
    boolean makeFirstRoad = true;
    
    //Main game needed
    Tile BottomRoad;
    double UpdateAllStart;
    Tile selection;
    Spawner spawn;
    EnemyNavigation navigator;
    boolean resetEnemies = true;
    boolean placingRoads=false;
    boolean deleting=false;
    public boolean moneyBonus=false;
    int lives;
    
    //musac;
    boolean mFirst;
    boolean mSecond;
    boolean mThird;
    boolean mFouth;
    boolean mFifth;
    SoundFile sFirst;
    SoundFile sSecond;
    SoundFile sThird;
    SoundFile sFourth;
    SoundFile sFifth;
    
    //EndGame
    EndScreen loseEnd;
    boolean loose;
    
    //EasterEggs
    BlueScreen bs;
    boolean blueScreen;
    boolean achievementScreen;
    Achievements achievements;
    
    /**
     * Initializes game
     */
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
        //opening animation controlls
        JAVA = true;
        firstwait = false;
        secondwait = false;
        AOTBSOD = false;
        javaLength = 18 * 200;
        aLength = 33 * 150;
        first = 1000;
        second = 1000;
        awwStarted = false;
        java = new OpeningAnimation(new Vector2(970 / 2, 640 / 2), OpeningAnimation.JAVA);
        aotbsod = new OpeningAnimation(new Vector2(970 / 2, 640 / 2), OpeningAnimation.AOTBSOD);
        startTime = System.currentTimeMillis();
        aww = new SoundFile("Game Resources/Sound/AwwComeon.wav", 1);

        //Creates the Main Menu
        mainMenu = false;
        turnOnMenuSong=true;
        creds = new MenuButton(new Vector2(485, 500), MenuButton.CREDITS);
        start = new MenuButton(new Vector2(485, 450), MenuButton.START);
        mainMenuSong= new SoundFile("Game Resources/Sound/urban towers.wav",3);

        //Creates the credits
        turnOnCreditSong=true;
        tutorial=false;
        creditScreen = new CreditScreen();
        creditSong=new SoundFile("Game Resources/Sound/creditsong.wav",2);

        //For the main game
        makeFirstRoad = true;
        UpdateAllStart = startTime;//Convienience, means nothing really....
        spawn = new Spawner((1000) * 45, this, new Vector2(13 * 32 + 16, 18 * 32 + 16));
        resetEnemies = true;
        placingRoads=false;
        deleting=false;
        moneyBonus=false;
        lives=20;
        
        //TutorialStart
        startTutorial=false;
        skip = new MenuButton(new Vector2(485, 500), MenuButton.SKIP);
        yes = new MenuButton(new Vector2(485, 350), MenuButton.YES);
        tut= new Tutorial();
        
        //EndScreen
        loseEnd=new EndScreen();
        loose=false;
        
        //Easter Eggs
        bs=new BlueScreen();
        blueScreen=false;
        achievementScreen=false;
        achievements=new Achievements();
        
        //musac;
         mFirst=true;
        mSecond=false;
        mThird=false;
        mFouth=false;
        mFifth=false;
        sFirst= new SoundFile("Game Resources/Sound/Thane/Based.wav",1);
        sSecond=new SoundFile("Game Resources/Sound/Thane/Danger.wav",1);
        sThird=new SoundFile("Game Resources/Sound/Thane/Song2.wav",1);
        sFourth=new SoundFile("Game Resources/Sound/Thane/SyncDie.wav",1);
        sFifth=new SoundFile("Game Resources/Sound/Thane/Yes.wav",1);
    }

    /**
     * Does nothing
     */
    @Override
    public void UnloadContent() {
    }

    /**
     * Sets frame vitals
     */
    @Override
    public void run() {
//        super.Run();
        this.base.theGUI.setSize(new Dimension(970, 640));
        this.base.theGUI.setResizable(false);
        this.base.theGUI.setTitle("Urban Towers");
    }

    /**
     * Updates all logic in the game;  The main game loop
     */
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
                startTutorial=true;
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
        }else if(startTutorial){
            yes.update(mouse);
            skip.update(mouse);
            if(skip.isPressed(mouse)){
                startTutorial=false;
            }
            if(yes.isPressed(mouse)){
                tutorial=true;
                startTutorial=false;
            }
        }else if(tutorial){
            tut.Update(keyboard, mouse);
            if(tut.isDone()){
                tutorial=false;
            }
        }else if(blueScreen){
            endAllMusic();
            if(!bs.hasStarted()){
                bs.start();
            }
            bs.Update(keyboard);
            if(bs.returnToGame()){
                blueScreen=false;
                bs= new BlueScreen();
                resetMusic();
                achievements.masterKey();
            }
            if(bs.goToStart()){
                blueScreen=false;
                endAllMusic();
                reset();
            }
        } else if(loose){
            if(!loseEnd.hasStarted()){
                loseEnd.start();
            }
            loseEnd.Update(keyboard);
            if(loseEnd.restart()){
                endAllMusic();
                reset();
            }
        }else {
            if(!achievements.isStarted()){
                achievements.start();
            }
            else{
                achievements.update(this, mouse);
            }
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
            
            //music
            if(!sFifth.isAlive()&&mFirst){
                sFirst.start();
                sFifth=new SoundFile("Game Resources/Sound/Thane/Yes.wav",1);
                mFirst=false;
                mSecond=true;
            }else if(!sFirst.isAlive() && mSecond){
                mSecond=false;
                sSecond.start();
                sFirst= new SoundFile("Game Resources/Sound/Thane/Based.wav",1);
                mThird=true;
            }else if(!sSecond.isAlive() && mThird){
                mThird=false;
                sThird.start();
                sSecond=new SoundFile("Game Resources/Sound/Thane/Danger.wav",1);
                mFouth=true;
            }else if(!sThird.isAlive() && mFouth){
                mFouth=false;
                sFourth.start();
                sThird=new SoundFile("Game Resources/Sound/Thane/Song2.wav",1);
                mFifth=true;
            }else if(!sFourth.isAlive() && mFifth){
                mFifth=false;
                sFifth.start();
                sFourth=new SoundFile("Game Resources/Sound/Thane/SyncDie.wav",1);
                mFirst=true;
            }
            
            spawn.update(keyboard);
            
            spawn.setSpawnProbabilities((int)(score+polution/3),activeTiles);
            
            if(lives<=0){
                loose=true;
            }

            for (int i = 0; i < allObjects.size(); i++) {
                WorldObject wo = allObjects.get(i);
                wo.Update(allObjects);
                if (wo instanceof Enemy) {
                    Enemy e = (Enemy) wo;
                    if(resetEnemies){
                        //e.setEnemyPath(navigator);
                        System.out.println("Set path!");
                        resetEnemies = false;
                    }
                    if (wo.getPosition().getY() < 0) {
                        allObjects.remove(wo);
                        score -= ((AbstractEnemy) wo).getScore();
                        lives--;
                    }
                    e.shoot(allObjects, activeTiles);
                }
                if (wo instanceof AbstractEnemy) {
                    AbstractEnemy e = (AbstractEnemy) wo;
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
                if (b.isPressed(mouse) && drag==null) {
                    if (b.id()>-1 && b.id()<99){
                    drag=new Draggable(b.getPath(),mouse.location(),b.id());
                    placingRoads=false;
                    deleting=false;
                    }
                    else if (b.id()==-1){
                        invOpen=false;
                    }
                    else if (b.id()==99){
                        placingRoads=!placingRoads;
                        deleting=false;
                    }
                    else if (b.id()==100){
                        placingRoads=false;
                        deleting=!deleting;
                    }
                }
                globalCount++;
            }
            for (Button b : buttons) {

                b.setOpen(invOpen);
                if (b.id()==99) b.toggle(placingRoads);
                if (b.id()==100) b.toggle(deleting);
                globalCount++;
            }
            
            //Needed because we can't have it in the loop
            
            
            
            if (drag != null) {
                drag.update(mouse);
            }
            if (!mouse.isPressed(Mouse.LEFT_BUTTON)) {
                if (drag != null) {
                    if (insideBounds(mouse.location())) {
                        for (Tower T : activeTiles) {
                            T.unselect(tiles);
                        }

                        int x = ((int) mouse.location().getX()) / 32;
                        int y = ((int) mouse.location().getY()) / 32;
                        if (insideBounds(x, y) && !(tiles[x][y] instanceof Road) && !(tiles[x][y] instanceof Tower)) {
                            Tower tower=drag.getTower(new Rect(x, y, 32, 32));
                            if(((x==0 && y==0) || (x==0 && y==19) || (x==26 && y==0) || (x==26 && y==19)) && tower instanceof Monument){
                                blueScreen=true;
                            }
                            if (money -tower.getCost() >= -500) {
                                tiles[x][y] = tower;
                                this.activeTiles.add((Tower) tiles[x][y]);
                                money -= ((Tower) tiles[x][y]).getCost();
                                new SoundFile("Game Resources/Sound/build.wav", 1).start();
                                achievements.addedATower(tower);
                                if (selection != null) {
                                    selection.unselect(tiles);
                                    selection = tiles[x][y];
                                    selection.select(tiles);
                                }
                            }
                        }
                    }
                }
                drag = null;
            }
            
            //determines moneyBonus;
            for (Tile[] t : tiles) {
                if (t[0] instanceof Road) {
                    moneyBonus = true;
                }
            }
            //ORDER HERE COUTNS!!!
            double thisLoopTime = System.currentTimeMillis();
            boolean updateAll=false;
            if (thisLoopTime - this.UpdateAllStart >= (1000) * 5) {
                updateAll = true;
                UpdateAllStart = thisLoopTime;
            }
            for (int k = 0; k < activeTiles.size(); k++) {
                Tower t = activeTiles.get(k);
                t.Update(allObjects);
                if (updateAll) {
                    t.updateGameStats(this);
                }
                if (t.isDead()) {
                    activeTiles.remove(t);
                    int x=(int)t.getPosition().getX()/32;
                    int y=(int)t.getPosition().getY()/32;
                    tiles[x][y]= new Tile(new Vector2(x * 32, y * 32));
                    new SoundFile("Game Resources/Sound/destroy2.wav",1).start();
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
            if (mouse.isPressed(Mouse.LEFT_BUTTON) && !insideBounds(mouse.location())){
                if (drag == null && buttons.get(0).canOpen()) {
                        //invOpen = true;
                    }  
            }
            invOpen=!insideBounds(mouse.location());
            
            //Mouse update methods in grid, the 832 should be most left pixel of the grid
            if (mouse.isPressed(Mouse.LEFT_BUTTON) && insideBounds(mouse.location())) {
                try {
                    
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
                    //road adding
                    if (b.contains(x, y) && (keyboard.isKeyDown('r')||placingRoads) && mouse.isPressed(Mouse.LEFT_BUTTON)&&keyboard.isKeyUp('d') && money > -500) {
                        if (!(tiles[i][j] instanceof Road)&& !(tiles[i][j] instanceof Tower) && (money -5 >= -500)) {
                            new SoundFile("Game Resources/Sound/road.wav",1).start();
                            Vector2 roadPos = new Vector2((i * 32), (j * 32));
                            tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
                            Road.setNeighbors(tiles, i, j);
                            roads.add((Road) tiles[i][j]);
                            resetEnemies = true;
                            money -= 5;
                            selection = null;
                            achievements.addedATower(tiles[i][j]);
                        }
                    }
                    if (b.contains(x, y) && (keyboard.isKeyDown('d')||deleting) && mouse.isPressed(Mouse.LEFT_BUTTON) && keyboard.isKeyUp('r') && money> -500) {
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

    /**
     * Draws all batches
     * @param g Graphics object.
     */
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
        }else if(startTutorial){
            skip.draw(batch);
            yes.draw(batch);
            batch.DrawString(new Vector2(50,100), "Would you like a tutorial?", Color.CYAN, 5, ImageCollection.FONT_DIALOG, ImageCollection.FONT_NORMAL, 28);
        }else if(tutorial){
            tut.Draw(batch);
        }else if(blueScreen){
            bs.Draw(batch);
        }else if(loose){
            this.setBackground(Color.black);
            loseEnd.Draw(batch);
        }else {
            this.setBackground(Color.white);
            for (WorldObject wo : allObjects) {
                wo.Draw(batch);
                globalCount++;
            }
            //draw the achievements
            achievements.Draw(batch);
            
            for (Button b : buttons) {
                b.draw(batch);
                globalCount++;
            }
            if (drag!=null) drag.draw(batch);

            //money drawing
            if (money < 0) {
                batch.DrawString(new Vector2(850, 30), "Money: $" + (int)money, Color.ORANGE, 10, ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            } else {
                batch.DrawString(new Vector2(850, 30), "Money: $" + (int)money, Color.black, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            }
            //Polution
            batch.DrawString(new Vector2(850, 45), "Pollution: " + (int)polution, Color.ORANGE, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 10);
            
            //Score
            if (score < 0) {
                batch.DrawString(new Vector2(850, 75), "Score: " + (int)score, Color.ORANGE, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            } else {
                batch.DrawString(new Vector2(850, 75), "Score: " + (int)score, Color.black, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            }
            //Happiness
            if (happiness < 0) {
                batch.DrawString(new Vector2(850, 60), "Happiness: " + (int)happiness, Color.ORANGE, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            } else {
                batch.DrawString(new Vector2(850, 60), "Happiness: " + (int)happiness, Color.black, 10,ImageCollection.FONT_SERIF, ImageCollection.FONT_NORMAL, 12);
            }
            //Lives Remaining
            batch.DrawString(new Vector2(835,580), "Chances Left: "+this.lives, Color.black, 10);
            // next round
            batch.DrawString(new Vector2(835, 600), "Next Round: " + (int) this.spawn.getRemainingTime() / 1000 + " s", Color.black, 10);

            //the Background Grid
            batch.Draw(Background, new Vector2(835 / 2, 611 / 2), 1);
            batch.Draw(bgtexture, new Vector2(970 / 2, 611 / 2), 0);
            
            if (selection != null) {
                selection.Draw(batch);
            }

            for (Tile t : activeTiles) {
                if(t.isSelected()){
                    t.unselect(tiles);
                    t.Draw(batch);
                    t.select(tiles);
                }else{
                    t.Draw(batch);
                }
                globalCount++;
            }

            for (Road r : roads) {
                if(r.isSelected()){
                    r.unselect(tiles);
                    r.Draw(batch);
                    r.select(tiles);
                }else{
                    r.Draw(batch);
                }
                
                globalCount++;
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
        polution=Math.max(Math.min(polution*0.85,2000),0);
        happiness=Math.max(Math.min(happiness*0.85,999),-999);
        return Math.max(score + (money +happiness - polution)/4,0);
        
    }

    /**
     * Adds a given object to the list of all objects
     * @param wo a WorldObject to add
     */
    public void addToWorldObjects(WorldObject wo) {
        allObjects.add(wo);
    }

    /**
     * Returns rectangular bounding
     * @param i2d the image who bounding is being returned
     * @return the Rect representing the bounding
     */
    public static Rect getRectangle(Image2D i2d) {
        return new Rect((int) (i2d.getPosition().getX() - i2d.getIconWidth() / 2),
                (int) (i2d.getPosition().getY() - i2d.getIconHeight() / 2),
                i2d.getIconWidth(), i2d.getIconHeight());
    }

    /**
     * Saves
     */
    private void save() {
        String path = "Game Resources/Saved Files/";
        File allSavedFiles = new File(path + "SavedFilesHead.txt");
    }

    /**
     * Loads
     */
    private void load() {
        String path = "Game Resources/Saved Files/";
        File allSavedFiles = new File(path + "SavedFilesHead.txt");
    }
    
    /**
     * Adds all buttons to a list of buttons for easy access
     */
    public void addButtons(){
        //factory
        buttons.add(new Button(Tower.FACTORY, "Game Resources/Sprites/August/ACME Corp/0.png", 880, 100));
        //button 2
        buttons.add(new Button(Tower.GREEN_BELT, "Game Resources/Sprites/Liam's Sprites/Towers/Park/park2-1.png", 920,100));
        //button 3
        buttons.add(new Button(Tower.HOUSE, "Game Resources/Sprites/Liam's Sprites/Towers/House/house1-1.png", 880, 140));
        //button 4
        buttons.add(new Button(Tower.MONUMENT, "Game Resources/Sprites/August/Memorial/00.png", 920, 140));
        //button 5
        buttons.add(new Button(Tower.POLICE_FIRE_STATION, "Game Resources/Sprites/August/Police Station 32x32.png", 880, 180));
        //button 6
        buttons.add(new Button(Tower.RECYCLING_CENTER, "Game Resources/Sprites/August/Recycling Center.png", 920, 180));
        //button 7
        buttons.add(new Button(Tower.STORE, "Game Resources/Sprites/August/Supermarket 32x32.png", 880, 220));
        //button 8
        buttons.add(new Button(99, "Game Resources/Sprites/Roads/CurvedRoadRightDown.png", 920, 260));
        //delete
        buttons.add(new Button(100, "Game Resources/Sprites/GUIS/deleteButton.png", 900, 300));
        //button 9?
        buttons.add(new Button(Tower.SCHOOL, "Game Resources/Sprites/SamSprites/Towers/School/school0.png",920,220));
        // waterPurification. wooh
        buttons.add(new Button(Tower.WATER_PURIFICATION_CENTER,"Game Resources/Sprites/SamSprites/Towers/Water Purification/pureWater5.png",880,260));

    }
    
    /**
     * Determines if a point is inside the play area
     * @param x x value of the point
     * @param y y value of the point
     * @return if the point is inside the play area
     */
    public boolean insideBounds(int x, int y){
        return (x>=0 && x<830) && (y>=0 && y<600);
    }
    
    /**
     * Determines if a point is inside the play area based upon a Vector2
     * @param pos the point's position
     * @return if the point is inside the play area
     */
    public boolean insideBounds(Vector2 pos){
        int x=(int)pos.getX();
        int y=(int)pos.getY();
        return insideBounds(x,y);
    }
    
    /**
     * Builds a given Tile
     * @param t the main Tile[][] array
     * @param add the Tile to add to the array
     * @param x the horizontal position of the grid square
     * @param y the vertical position of the grid square
     */
    public void build(Tile[][] t, Tile add, int x, int y){
        selection.unselect(t);
        t[x][y].unselect(t);
        t[x][y]=add;
        add.select(t);
        selection=add;
    }

    
    public void resetMusic(){
        mFirst=true;
        mSecond=false;
        mThird=false;
        mFouth=false;
        mFifth=false;
        sFirst= new SoundFile("Game Resources/Sound/Thane/Based.wav",1);
        sSecond=new SoundFile("Game Resources/Sound/Thane/Danger.wav",1);
        sThird=new SoundFile("Game Resources/Sound/Thane/Song2.wav",1);
        sFourth=new SoundFile("Game Resources/Sound/Thane/SyncDie.wav",1);
        sFifth=new SoundFile("Game Resources/Sound/Thane/Yes.wav",1);
    }
    
    public void endAllMusic(){
        if(sFirst.isAlive()){
            sFirst.kill();
        }
        if(sSecond.isAlive()){
            sSecond.kill();
        }
        if(sThird.isAlive()){
            sThird.kill();
        }
        if(sFourth.isAlive()){
            sFourth.kill();
        }
        if(sFifth.isAlive()){
            sFifth.kill();
        }
    }
}
