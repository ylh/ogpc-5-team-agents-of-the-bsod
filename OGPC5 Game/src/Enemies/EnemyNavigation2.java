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
    public void update(Vector2 pos){
        x=(int)pos.getX()/32;
        y=(int)pos.getY()/32;
        visits[x][y] +=1;
    }
    public int decide(Vector2 pos){
        x= (int)pos.getX()/32;
        y= (int)pos.getY()/32;
        int min=9001;
        int minDir=-1;
        int[] values=new int[4];
        try{
            values[0]=visits[x][y-1];
        }catch(Exception e){
            values[0]=1000000;
        }
        try{
            values[1]=visits[x+1][y];
        }catch(Exception e){
            values[1]=1000000;
        }
        try{
            values[2]=visits[x][y+1];
        }catch(Exception e){
            values[2]=1000000;
        }
        try{
            values[3]=visits[x-1][y];
        }catch(Exception e){
            values[3]=1000000;
        }
        
        for(int i=0;i<4;i++){
            if (values[i]<min){
                min=values[i];
                minDir=i;
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
