/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import WorldObjects.WorldObject;
import WorldObjects.towers.Bullet;
import WorldObjects.towers.Tower;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class Enemy extends AbstractEnemy{
    
    Animation a;
    Animation earthquake1, earthquake2, earthquake3, earthquake4, smog1, smog2, smog3, smog4;
    String bulletSprite;
    /**
     * 
     * @param Speed enemy speed
     * @param Health how much health the enemy has
     * @param Armor how much armor the enemy has
     * @param pos the position of the enemy
     * @param path the file path of the enemy
     * @param t the main Tile[][] array
     */
    public Enemy(double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t) {
        this(AbstractEnemy.GENERIC, Speed, Health, Armor, pos, path,t);
    }
    
    /**
     * 
     * @param type the kind of enemy
     * @param Speed enemy speed
     * @param Health how much health the enemy has
     * @param Armor how much armor the enemy has
     * @param pos the position of the enemy
     * @param path the file path of the enemy
     * @param t the main Tile[][] array
     */
    public Enemy(int type, double Speed, double Health, double Armor, Vector2 pos, String path, Tile[][] t){
        super(Speed, Health, Armor, pos, path, t);
        speed = Speed;
        targetPos=new Vector2(pos.getX(),pos.getY()-32);
        health = Health;
        armor = Armor;
        danger = 0;
        tiles = t;
        score=10;
        id=type;
        System.out.println("Enemy Created");
        bulletSprite="Game Resources/Sprites/particle.png";
        chooseAnimation();
        initializeAnimations();
        LoadStats();
    }
    
    private void chooseAnimation(){
        switch(id){
            case AbstractEnemy.ARSONIST: 
                a = new Animation(8, 5, this.position, 500);
                for(int i = 0; i <= 7; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Arsonist/arsonist";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case AbstractEnemy.EARTHQUACKE:
                a = new Animation(2, 5, this.position, 300);
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-1.png");
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-2.png");
                break;
            case AbstractEnemy.FIRE:
                a = new Animation(4, 5, this.position, 500);
                for(int i = 0; i <= 3; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Fire/fire";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case AbstractEnemy.FLOOD:
                a = new Animation(10, 5, this.position, 500);
                for(int i = 0; i <= 9; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Flood/flood";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case AbstractEnemy.GRAFITTI:
                a = new Animation(19, 5, this.position, 50);
                for(int i = 0; i <= 18; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/Grafitti/graffiti";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case AbstractEnemy.SMOG:
                a = new Animation(2, 5, this.position, 300);
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png");
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-2.png");
                break;
            case AbstractEnemy.WATER_POLUTION:
                a = new Animation(9, 5, this.position, 300);
                for(int i = 0; i <= 8; i++){
                    String s = "Game Resources/Sprites/SamSprites/Enemies/WaterPolution/waterPollution";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case AbstractEnemy.GANGS:
                a = new Animation(1, 5, this.position, Integer.MAX_VALUE);
                a.addCell("Game Resources/Sprites/Yestin/gang.png");
                break;
            case AbstractEnemy.EDUCATION:
                a = new Animation(20, 5, this.position, 300);
                a.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Bad Education/Bad Education.png");
                for(int i = 2; i <= 20; i++){
                    String s = "Game Resources/Sprites/Liam's Sprites/Enemies/Bad Education/BE1-";
                    s += i + ".png";
                    a.addCell(s);
                }
                break;
            case AbstractEnemy.TRASH:
                a = new Animation(1, 5, this.position, Integer.MAX_VALUE);
                a.addCell("Game Resources/Sprites/Yestin/trash.png");
                break;
            case AbstractEnemy.CRIMINAL:
                a = new Animation(2, 5, this.position, Integer.MAX_VALUE);
                a.addCell("Game Resources/Sprites/Yestin/criminal1.png");
                a.addCell("Game Resources/Sprites/Yestin/criminal2.png");
                break;
            default:
                a = new Animation(1, 5, this.position, Integer.MAX_VALUE);
                a.addCell("Game Resources/Sprites/PlaceHolderEnemy.png");
        }
    }
    
    @Override
    public void Draw(ImageCollection batch){
        super.Draw(batch);
        a.Draw(batch);
    }
    
        @Override
    public void Update(ArrayList<WorldObject> wol) {
        super.Update(wol);
        alterAnimationDirection();
    }
        
    private void alterAnimationDirection() {
        if (id == AbstractEnemy.EARTHQUACKE) {
            if(dir == 0){
                a = earthquake1;
            }
            if(dir == 1){
                a = earthquake3;
            }
            if(dir == 2){
                a = earthquake2;
            }
            if(dir == 3){
                a = earthquake4;
            }
        }else if(id == AbstractEnemy.SMOG){
            if(dir == 0){
                a = smog1;
            }
            if(dir == 1){
                a = smog3;
            }
            if(dir == 2){
                a = smog2;
            }
            if(dir == 3){
                a = smog4;
            }
        }
    }
    
    private void initializeAnimations() {
        //EATHQUAKES
        //Earthquack1
        earthquake1 = new Animation(2, 5, this.position, 300);
        earthquake1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-1.png");
        earthquake1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVU1-2.png");
        //Earthquake2
        earthquake2 = new Animation(2, 5, this.position, 300);
        earthquake2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVD1-1.png");
        earthquake2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVD1-2.png");
        //Earthquake3
        earthquake3 = new Animation(2, 5, this.position, 300);
        earthquake3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVR1-1.png");
        earthquake3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVR1-2.png");
        //Earthquake4
        earthquake4 = new Animation(2, 5, this.position, 300);
        earthquake4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVL1-1.png");
        earthquake4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Earthquake/earthquake monsterVL1-2.png");
        //SMOGS
        //Smog1
        smog1 = new Animation(2, 5, this.position, 300);
        smog1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVU1-1.png");
        smog1.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVU1-2.png");
        //Smog2
        smog2 = new Animation(2, 5, this.position, 300);
        smog2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVD1-1.png");
        smog2.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogVD1-2.png");
        //Smog3
        smog3 = new Animation(2, 5, this.position, 300);
        smog3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogR1-1.png");
        smog3.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogR1-2.png");
        //Smog4
        smog4 = new Animation(2, 5, this.position, 300);
        smog4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-1.png");
        smog4.addCell("Game Resources/Sprites/Liam's Sprites/Enemies/Smog/SmogH1-2.png");
    }
    @Override
    public Bullet setTowerBulletHitting(Tower t) {
        return new Bullet(bulletSprite, this.position.clone(), this.damage, 0, 0, 10, t);
        
    }

    @Override
    public void LoadStats() {
        switch(id){
            case AbstractEnemy.ARSONIST:
                load("Game Resources/Sprites/Bullets/firebullet.png",25,500);
                break;
            case AbstractEnemy.EARTHQUACKE:
                load(4,10);
                break;
            case AbstractEnemy.FIRE:
                load("Game Resources/Sprites/Bullets/firebullet.png",5,100);
                break;
            case AbstractEnemy.FLOOD:
                load(5,50);
                break;
            case AbstractEnemy.GRAFITTI:
                load(5,100);
                break;
            case AbstractEnemy.TRASH:
                load(4,100);
                break;
            case AbstractEnemy.SMOG:
                load("Game Resources/Sprites/Bullets/smogbullet.png",2,50);
                break;
            case AbstractEnemy.WATER_POLUTION:
                load("Game Resources/Sprites/Bullets/smogbullet.png",5,50);
                break;
            case AbstractEnemy.CRIMINAL:
                load(15,300);
                break;
            case AbstractEnemy.GANGS:
                load(10,200);
                break;
            case AbstractEnemy.EDUCATION:
                load(3,50);
                break;
            default:
                load(1,20);
                break;
        }
    }
    
    private void load(int damage, int fireSpeed){
        this.damage=damage;
        this.fireSpeed=fireSpeed;
    }
    private void load(String s, int damage, int fireSpeed){
        this.damage=damage;
        this.fireSpeed=fireSpeed;
        this.bulletSprite=s;
    }
}
