/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Enemies.Enemy;
import Enemies.EnemyNavigation;
import Enemies.Spawner;
import GUIStuff.Button;
import GUIStuff.CreditScreen;
import GUIStuff.Draggable;
import GUIStuff.MenuButton;
import GUIStuff.Tile;
import Game.Game;
import KylesTesting.OpeningAnimation;
import Utilities.Image2D;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.SoundFile;
import Utilities.Vector2;
import WorldObjects.WorldObject;
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
    public ArrayList<Tile> activeTiles;
    public ArrayList<Button> buttons;
    public ArrayList<Road> roads;
    public double money;
    public double score;
    public double happiness;
    public double polution;
    public boolean invOpen = false;
    Image2D Background = new Image2D("Game Resources/Sprites/GUIs/Background.PNG");
    Image2D bgtexture = new Image2D("Game Resources/Sprites/bgtexture.png");
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
    //credits
    boolean credits;
    CreditScreen creditScreen;
    //PreGame
    boolean makeFirstRoad = true;
    //Main game needed
    Tile BottomRoad;
    double UpdateAllStart;
    Tile selection;
    Spawner spawn;
    //I've added a comment!!!
    EnemyNavigation navigator;
    boolean resetEnemies = true;

    @Override
    public void InitializeAndLoad() {
        //Core features creation.
        firstRun = true;
        this.gameSpeed = 32;
        allObjects = new ArrayList<WorldObject>();
        activeTiles = new ArrayList<Tile>();
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
        buttons.add(new Button(1, "Game Resources/Sprites/Liam's Sprites/Towers/House/house1-1.png", new Vector2(900, 100), new Vector2(900, -40), 0.1));
        buttons.add(new Button(2, "Game Resources/Sprites/Liam's Sprites/Towers/Police/save2.png", new Vector2(900, 200), new Vector2(900, -40), 0.1));
        buttons.add(new Button(3, "Game Resources/Sprites/GUIS/deleteButton.png", new Vector2(900, 300), new Vector2(900, -40), 0.1));

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

        //Creates the Main Menu
        creds = new MenuButton(new Vector2(970 / 2, 300), MenuButton.CREDITS);
        start = new MenuButton(new Vector2(970 / 2, 500), MenuButton.START);
        aww = new SoundFile("Game Resources/Sound/AwwComeon.wav", 1);

        //Creates the credits
        creditScreen = new CreditScreen();

        //For the main game
        UpdateAllStart = startTime;//Convienience, means nothing really....
        spawn = new Spawner((1000) * 5, this, new Vector2(13 * 32 + 16, 18 * 32 + 16));
        
        navigator = new EnemyNavigation(tiles, "Default", 10, 10);
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
        if (firstRun) {
            double time = System.currentTimeMillis();
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
                }
            }
        } else if (mainMenu) {
            creds.update(mouse);
            start.update(mouse);
            if (start.isPressed(mouse)) {
                mainMenu = false;
            }
            if (creds.isPressed(mouse)) {
                mainMenu = false;
                credits = true;
                creditScreen.start();
            }
        } else if (credits) {
            creditScreen.update();
            if (creditScreen.isDone(keyboard, mouse)) {
                credits = false;
                creditScreen = new CreditScreen();
                mainMenu = true;
            }
        } else {

            if (makeFirstRoad) {
                int i = 13, j = 18;
                Vector2 roadPos = new Vector2((i * 32), (j * 32));
                tiles[i][j] = new Road(roadPos, Road.returnSprite(Road.setRoadShape(tiles, i, j)));
                Road.setNeighbors(tiles, i, j);
                roads.add((Road) tiles[i][j]);                
                navigator.start();
                makeFirstRoad = false;
                BottomRoad = tiles[i][j];
            }
            spawn.update();

            for (int i = 0; i < allObjects.size(); i++) {
                WorldObject wo = allObjects.get(i);
                wo.Update(allObjects);
                if (wo instanceof Enemy) {
                    Enemy e = (Enemy) wo;
                    if(resetEnemies){
                        e.setEnemyPath(navigator);
                        System.out.println("Set path!");
                        resetEnemies = false;
                    }
                    if (wo.getPosition().getY() < 0) {
                        allObjects.remove(wo);
                        score -= ((Enemy) wo).getScore();
                    }
                }
                globalCount++;
            }

            /*
             * Just code for getting view into the game without breaking anything when debugging.
             */
            if (keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
                int i = 0;
            }

            buttonPressed = 0;

            for (Button b : buttons) {
                b.glide();
                if (b.isPressed(mouse)) {
                    invOpen = false;

                    buttonPressed = b.id();
                }
                globalCount++;
            }
            for (Button b : buttons) {

                b.setOpen(invOpen);
                globalCount++;
            }
            if (buttonPressed == 1) {
                drag = new Draggable("Game Resources/Sprites/Liam's Sprites/Towers/House/house1-1.png", mouse.location().clone(),Tower.HOUSE);

            }
            if (buttonPressed == 2) {
                drag = new Draggable("Game Resources/Sprites/Liam's Sprites/Towers/Police/save2.png", mouse.location().clone(), Tower.POLICE_FIRE_STATION);
            }
            //Needed because we can't have it in the loop
            if (drag != null) {
                drag.update(mouse);
            }


            if (!mouse.isPressed(Mouse.LEFT_BUTTON)) {
                drag = null;
            }
            double thisLoopTime = System.currentTimeMillis();
            for (Tile t : activeTiles) {
                t.Update(allObjects);

                //For updating score, happiness, polution, and money
                if (thisLoopTime - this.UpdateAllStart >= 30000) {
                    ((Tower) t).updateGameStats(this);
                }

                globalCount++;
            }

            if (mouse.isPressed(Mouse.LEFT_BUTTON) && mouse.location().getX() < 854) {
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
                            navigator.recalculatePath(i, j, false);
                            resetEnemies = true;
                            //activeTiles.add(tiles[i][j]);
                            if ((i + 1) >= tiles.length) {
                                //ADD SPAWNER CODE
                            }
                            money -= 5;
                            selection = null;
                        }
                    }
                    if (b.contains(x, y) && keyboard.isKeyDown('d') && mouse.isPressed(Mouse.LEFT_BUTTON)) {
                        if (!(tiles[i][j] == BottomRoad) && (tiles[i][j] instanceof Road)) {
                            //activeTiles.remove(tiles[i][j]);
                            roads.remove(tiles[i][j]);
                            Road.setNeighbors(tiles, i, j);
                            tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
                            Road.setNeighbors(tiles, i, j);
                            navigator.recalculatePath(i, j, true);
                            resetEnemies = true;
//                        resetPaths();
                            money -= 5;
                            selection = null;
                        }
                        if ((tiles[i][j] instanceof Tile) && !(tiles[i][j] == BottomRoad)) {
                            if (activeTiles.contains(tiles[i][j])) {
                                activeTiles.remove(tiles[i][j]);
                                money -= 5;
                                selection = null;
                                Road.setNeighbors(tiles, i, j);//just to be safe
                                tiles[i][j] = new Tile(new Vector2(i * 32, j * 32));
                                navigator.recalculatePath(i, j, true);
                            } else {
                                //DO NOTHING!!!!
                            }
                        }
                    }
                } catch (Exception e) {
                    if(e instanceof ArrayIndexOutOfBoundsException){
                        //Do nothing
                    }else{
                        e.printStackTrace();
                    }
                }
            }//End of mouse pressed

            //Updates the score
            score = this.getScore();
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
            creds.draw(batch);
            start.draw(batch);
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
                batch.DrawString(new Vector2(850, 30), "Money: $" + money, Color.red, 10);
            } else {
                batch.DrawString(new Vector2(850, 30), "Money: $" + money, Color.black, 10);
            }
            //Polution
            batch.DrawString(new Vector2(850, 45), "Polution: " + polution, Color.red, 10);
            //Score
            if (score < 0) {
                batch.DrawString(new Vector2(850, 75), "Score: " + score, Color.red, 10);
            } else {
                batch.DrawString(new Vector2(850, 75), "Score: " + score, Color.black, 10);
            }
            //Happiness
            if (happiness < 0) {
                batch.DrawString(new Vector2(850, 60), "Happiness: " + happiness, Color.red, 10);
            } else {
                batch.DrawString(new Vector2(850, 60), "Happiness: " + happiness, Color.black, 10);
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
            System.out.println(globalCount);
        }//End of Main Game Else
    }

//    private void resetPaths() {
//        for (WorldObject b : allObjects) {
//            if (b instanceof Enemy) {
//                Enemy e = (Enemy) b;
//                e.updatePath();
//            }
//        }
//    }

    /**
     * Sort of self explanatory
     * @return the score.
     */
    private double getScore() {
        return score + (money - polution) * happiness * 0.1;
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
}
