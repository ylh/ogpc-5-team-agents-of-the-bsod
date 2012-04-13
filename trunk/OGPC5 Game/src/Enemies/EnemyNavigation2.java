/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;

/**
 *
 * @author pcowal15
 */
public class EnemyNavigation2 {
    int[][]visits;
    int x;
    int y;
    
    double lastVisit;
    
    public EnemyNavigation2(Tile[][] t){
        visits=new int[t.length][t[0].length];
        for(int i=0;i < t.length; i++){
            for(int j=0;j<t[0].length; j++){
                if (t[i][j] instanceof Road ){
                    visits[i][j]=0;
                }
                else{
                    visits[i][j]=9001;
                }
            }
        }
    }
    public void reset(Tile[][] t){
        for(int i=0;i < t.length; i++){
            for(int j=0;j<t[0].length; j++){
               
                if (t[i][j] instanceof Road){
                    //Vegeta, what does the scouter say about his power level?!?
                    if (visits[i][j]>9000){
                        //IT'S OVER NINE THOUSANDDDDDD!!!1!11!!!1!!!!!!111!!!one!!
                        visits[i][j]=0;
                    }
                    //9000? There's no way that could be right!!!1!1
                }
                else{
                    
                    visits[i][j]=9001;
                }
            }
        }
    }
    public void update(Vector2 pos){
        x=(int)pos.getX()/32;
        y=(int)pos.getY()/32;
        visits[x][y] +=1;
    }
    public int decide(Vector2 pos){
        x= (int)pos.getX()/32;
        y= (int)pos.getY()/32;
        int min=10000;
        int minDir=-1;
        int n=0;
        int[] values=new int[4];
        try{
            values[0]=visits[x][y-1];
        }catch(Exception e){
            //GO HERE
            values[0]=-1000000;
        }
        try{
            values[1]=visits[x+1][y];
        }catch(Exception e){
            //DON'T GO HERE
            values[1]=1000000;
        }
        try{
            values[2]=visits[x][y+1];
        }catch(Exception e){
            //DON'T GO HERE
            values[2]=1000000;
        }
        try{
            values[3]=visits[x-1][y];
        }catch(Exception e){
            //DON'T GO HERE
            values[3]=1000000;
        }
        
        for(int i=0;i<4;i++){
            if (values[i]<min){
                min=values[i];
                minDir=i;
                n++;
            }
            else if (values[i]==min){
                n++;
                double m=(double)n;
                if (Math.random()<=1/m){
                    minDir=i;
                }
            }
        }
        return minDir;
        /* Directions:
         *   0 
         * 3<^>1
         *   2
         */ 
    }
    
}
