/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EasterEggs;

import GUIStuff.Tile;
import Utilities.Mouse;
import WorldObjects.towers.*;
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
    
    public void update(CityGame theGame, Mouse m){
        if(theGame.score>9000){
            over9000=true;
        }
        if(theGame.score+theGame.happiness+theGame.money>100000){
            MasterOfUniverse=true;
        }
        if(theGame.happiness>=999){
            breadAndCircus=true;
        }
        if(mt>=50){
            massTransit=true;
        }
        if(gt>=50){
            greenThumb=true;
        }
        if(mh>=50){
            MassHousing=true;
        }
        if(hf>=50){
            HenryFord=true;
        }
        if(htd>=50){
            honorTheDead=true;
        }
        if(cbtf>=50){
            cantBeTooSafe=true;
        }
        if(lte>=50){
            loveTheEarth=true;
        }
        if(fw>=50){
            freshWater=true;
        }
        if(totc>=50){
            TopOfTheClass=true;
        }
        if(FM>=50){
            FreeMarket=true;
        }
        if(System.currentTimeMillis()-startTime>=(1000*(15*3600))){
            this.GLaDOSWouldBeProud=true;
        }
        
    }
    
    public boolean isStarted(){
        return hasBeenStarted;
    }
    public void start(){
        hasBeenStarted=true;
        startTime=System.currentTimeMillis();
    }
    
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
    
    public void masterKey(){
        masterKey=true;
    }
}
