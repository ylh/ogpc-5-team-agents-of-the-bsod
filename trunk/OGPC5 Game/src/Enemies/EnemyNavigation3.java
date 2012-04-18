/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;

/**
 * An experimental recursive algorithm for pathfinding that doesn't exactly work...
 * @author pcowal15
 */
public class EnemyNavigation3 {
    int[][]values;
    
    public EnemyNavigation3( Tile[][]t){
        values=new int[t.length][t[0].length];
        for(int i=0;i < t.length; i++){
            for(int j=0;j<t[0].length; j++){
                if (t[i][j] instanceof Road ){
                    if (j==0){
                        values[i][j]=0;
                    }
                    else{
                        values[i][j]=1000;
                    }
                }
                else{
                    values[i][j]=9001;
                }
            }
        }
        for(int i=0; i<t[0].length;i++){
            if (values[i][0] < 9000){
            setValue(i,0);
            }
        }
    }
    public void setValue(int x,int y){
        int value=values[x][y];
        int min=value;
        try{
        if (values[x+1][y]<min) min=values[x+1][y];
        }
        catch(Exception e){
        }
        try{
        if (values[x][y-1]<min) min=values[x][y-1];
        }
        catch(Exception e){
        }
        try{
        if (values[x-1][y]<min) min=values[x-1][y];
        }
        catch(Exception e){
        }
        try{
        if (values[x][y+1]<min) min=values[x][y+1];
        }
        catch(Exception e){
        }
        if (min<value){
            value=min+1;
            values[x][y]=value;
            try {
                if (values[x + 1][y] > value && values[x+1][y]<9000) {
                    setValue(x+1,y);
                }
            } catch (Exception e) {
            }
            try {
                if (values[x][y - 1] > value && values[x][y-1]<9000) {
                    setValue(x,y-1);
                }
            } catch (Exception e) {
            }
            try {
                if (values[x - 1][y] > value && values[x-1][y]<9000) {
                    setValue(x-1,y);
                }
            } catch (Exception e) {
            }
            try {
                if (values[x][y + 1] > value && values[x][y+1]<9000) {
                    setValue(x,y+1);
                }
            } catch (Exception e) {
            }
        }
    }
    public int decide(Vector2 pos){
        int x= (int)pos.getX()/32;
        int y= (int)pos.getY()/32;
        int min=10000;
        int minDir=-1;
        int n=0;
        int[] q=new int[4];
        try{
            q[0]=values[x][y-1];
        }catch(Exception e){
            //GO HERE
            q[0]=-1000000;
        }
        try{
            q[1]=values[x+1][y];
        }catch(Exception e){
            //DON'T GO HERE
            q[1]=1000000;
        }
        try{
            q[2]=values[x][y+1];
        }catch(Exception e){
            //DON'T GO HERE
            q[2]=1000000;
        }
        try{
            q[3]=values[x-1][y];
        }catch(Exception e){
            //DON'T GO HERE
            q[3]=1000000;
        }
        
        for(int i=0;i<4;i++){
            if (q[i]<min){
                min=q[i];
                minDir=i;
                n++;
            }
            else if (q[i]==min){
                n++;
                double m=(double)n;
                if (Math.random()<=1/m){
                    minDir=i;
                }
            }
        }
        return minDir;
    }
}