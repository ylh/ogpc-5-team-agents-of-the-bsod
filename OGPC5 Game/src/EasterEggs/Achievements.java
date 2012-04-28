/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EasterEggs;

import GUIStuff.Tile;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Vector2;
import WorldObjects.towers.*;
import java.awt.Color;
import ogpc5.game.CityGame;

/**
 *
 * @author RomulusAaron
 */
public class Achievements {
    //Over9000
    boolean over9000;
    //More than 50 roads
    boolean massTransit;
    int mt;
    //More than 50 Parks
    boolean greenThumb;
    int gt;
    //Happiness, Money, and Score combined over 100,000
    boolean MasterOfUniverse;
    //more than 50 houses
    boolean MassHousing;
    int mh;
    //more than 50 factories
    boolean HenryFord;
    int hf;
    //more than 50 memorials
    boolean honorTheDead;
    int htd;
    //more than 50 policeFire stations
    boolean cantBeTooSafe;
    int cbtf;
    //more than 50 recycling plants
    boolean loveTheEarth;
    int lte;
    //more than 50 waterPurification
    boolean freshWater;
    int fw;
    //more than 50 schools
    boolean TopOfTheClass;
    int totc;
    //sucessfully escaped BSoD;
    boolean masterKey;
    //More than 50 stores
    boolean FreeMarket;
    int FM;
    
    boolean GLaDOSWouldBeProud;
    double startTime;
    boolean hasBeenStarted;
    
   //BreadAndCircuses
    boolean breadAndCircus;
    
    Image2D Background=new Image2D("Game Resources/Sprites/bgtexture.png");
    
    AchievementPopup popup=new AchievementPopup();
    
    /**
     * Initializes all achievements to false
     */
    public Achievements(){
        over9000=false;
        massTransit=false;
        greenThumb=false;
        MasterOfUniverse=false;
        MassHousing=false;
        HenryFord=false;
        honorTheDead=false;
        cantBeTooSafe=false;
        loveTheEarth=false;
        freshWater=false;
        TopOfTheClass=false;
        masterKey=false;
        breadAndCircus=false;
        FreeMarket=false;
        hasBeenStarted=false;
        
        mt=1;
        gt=0;
        mh=0;
        hf=0;
        htd=0;
        cbtf=0;
        lte=0;
        fw=0;
        totc=0;
        FM=0;
    }
    
    /**
     * Determines if an achievement has been earned
     * @param theGame the main game
     * @param m the active Mouse
     */
    public void update(CityGame theGame, Mouse m){
        popup.update();
        if(theGame.score>9000 && !over9000){
            over9000=true;
            
        }
        if(theGame.score+theGame.happiness+theGame.money>=100000 && !MasterOfUniverse){
            MasterOfUniverse=true;
            popup.set("Game Resources/Sprites/August/Game Icon.png",
                    "Master of the Universe","Score+happiness+money=100000");
            
        }
        if(theGame.happiness>=999 && !breadAndCircus){
            breadAndCircus=true;
        }
        if(mt>=50 && !massTransit){
            massTransit=true;
            popup.set("Game Resources/Sprites/Roads/CurvedRoadRight.png",
                    "Mass Transit","Build 50 Roads");
        }
        if(gt>=20 && !greenThumb){
            greenThumb=true;
            popup.set("Game Resources/Sprites/Liam's Sprites/Towers/Park/park2-1.png",
                    "Green Thumb","Build 20 Parks");
        }
        if(mh>=20 && !MassHousing){
            MassHousing=true;
            popup.set("Game Resources/Sprites/Liam's Sprites/Towers/House/house1-1.png",
                    "Mass Housing","Build 20 Houses");
        }
        if(hf>=20 && !HenryFord){
            HenryFord=true;
            popup.set("Game Resources/Sprites/August/ACME Corp/0.png",
                    "Henry Ford","Build 20 Factories");
        }
        if(htd>=20 && !honorTheDead){
            honorTheDead=true;
            popup.set("Game Resources/Sprites/August/Memorial/00.png",
                    "Honor The Dead","Build 20 Monuments");
        }
        if(cbtf>=20 && !cantBeTooSafe){
            cantBeTooSafe=true;
            popup.set("Game Resources/Sprites/August/Police Station 32x32.png",
                    "Can't Be Too Safe","Build 20 Police Stations");
        }
        if(lte>=20 && !loveTheEarth){
            loveTheEarth=true;
            popup.set("Game Resources/Sprites/August/Recycling Center.png",
                    "Love The Earth","Build 20 Recycling Centers");
        }
        if(fw>=20 && !freshWater){
            freshWater=true;
            popup.set("Game Resources/Sprites/SamSprites/Towers/Water Purification/pureWater5.png",
                    "Fresh Water","Build 20 Water Purification Centers");
        }
        if(totc>=20 && !TopOfTheClass){
            TopOfTheClass=true;
            popup.set("Game Resources/Sprites/SamSprites/Towers/School/school.png",
                    "Top Of The Class","Build 20 Schools");
        }
        if(FM>=20 && !FreeMarket){
            FreeMarket=true;
            popup.set("Game Resources/Sprites/August/Supermarket 32x32.png",
                    "Free Market","Build 20 Stores");
        }
        if(System.currentTimeMillis()-startTime>=(1000*(15*3600)) && !GLaDOSWouldBeProud){
            this.GLaDOSWouldBeProud=true;
            popup.set("Game Resources/Sprites/Yestin/muralidy.png",
                    "GLaDOS Would Be Proud","Play for 15 minutes");
        }
        
    }
    
    /**
     * 
     * @return if started
     */
    public boolean isStarted(){
        return hasBeenStarted;
    }
    
    /**
     * Starts
     */
    public void start(){
        hasBeenStarted=true;
        startTime=System.currentTimeMillis();
    }
    
    /**
     * Contributes to counters for achievements
     * @param t a given Tile being built
     */
    public void addedATower(Tile t){
        if(t instanceof Road){
            mt++;
        }
        if(t instanceof GreenBelt){
            gt++;
        }
        if(t instanceof House){
            mh++;
        }
        if(t instanceof Factory){
            hf++;
        }
        if(t instanceof Monument){
            htd++;
        }
        if(t instanceof PoliceFire){
            cbtf++;
        }
        if(t instanceof RecyclingCenter)
        {
            lte++;
        }
        if(t instanceof WaterPurification){
            fw++;
        }
        if(t instanceof School){
            totc++;
        }
        if(t instanceof Store){
            FM++;
        }
    }
    
    /**
     * Draws
     * @param batch ImageCollection being drawn
     */
    public void Draw(ImageCollection batch){
        popup.draw(batch);
    }
    
    /**
     * Sets masterKey to true
     */
    public void masterKey(){
        if (!masterKey){
            masterKey=true;
        }
    }
    
    public void drawAchievement(boolean found, double x,double y,ImageCollection batch, String sprite, String name, String description){
        if (found){
            batch.Draw(new Image2D(sprite), new Vector2(x,y),1000);
            //draw the text
            batch.DrawString(new Vector2(x+20,y-10), "ACHIEVEMENT GET!", 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            batch.DrawString(new Vector2(x+20,y), name, 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            batch.DrawString(new Vector2(x+20,y+10), description, 
                Color.white, 1000, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            //draw the text's shadow
            batch.DrawString(new Vector2(x+19,y-9), "ACHIEVEMENT GET!", 
                Color.black, 999, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            batch.DrawString(new Vector2(x+19,y+1), name, 
                Color.black, 999, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
            batch.DrawString(new Vector2(x+19,y+11), description, 
                Color.black, 999, ImageCollection.FONT_DIALOG_INPUT, ImageCollection.FONT_BOLD_ITALIC, 10);
        }
        else{
            
        }
    }
}
