/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;

/**
 * The simplest pathfinding AI that you will ever see.
 * @author pcowal15
 */
public class EnemyNavigation2 {
    int[][]visits;
    int x;
    int y;
    
    double lastVisit;
    /**
     * A constructor that parses an array of tiles into an array of integers
     * @param t The tile array containing all of the data pertaining to roads
     */
    
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
    /**
     * Similar to the constructor, but this method retains any data it already carries.
     * @param t The tile array containing all of the data pertaining to roads
     */
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
    /**
     * This method adds one to the current location of the enemy.
     * @param pos The position of the enemy
     */
    public void update(Vector2 pos){
        x=(int)pos.getX()/32;
        y=(int)pos.getY()/32;
        visits[x][y] +=2;
    }
    /**
     * Takes the enemy's current position and gives it the direction of the
     * adjacent cell that has been visited the least.
     * @param pos The enemy's position
     * @param dir The enemy's direction
     * @return 0=up,1=right,2=down,3=left
     */
    public int decide(Vector2 pos, int dir) {
        x = (int) pos.getX() / 32;
        y = (int) pos.getY() / 32;
        int min = 10000;
        int minDir = -1;
        int n = 0;
        int[] values = new int[4];
        /**
         * If it's off the road, it'll go up
         */
        if (visits[x][y] > 9000) {
            return 0;
        } else {
            /**
             * Get the values of the adjacent squares
             */
            try {
                values[0] = visits[x][y - 1];
                if (values[0] < 9000) {
                    n++;
                }
            } catch (Exception e) {
                //GO HERE
                values[0] = -1000000;
            }
            try {
                values[1] = visits[x + 1][y];
                if (values[1] < 9000) {
                    n++;
                }
            } catch (Exception e) {
                //DON'T GO HERE
                values[1] = 1000000;
            }
            try {
                values[2] = visits[x][y + 1];
                if (values[2] < 9000) {
                    n++;
                }
            } catch (Exception e) {
                //DON'T GO HERE
                values[2] = 1000000;
            }
            try {
                values[3] = visits[x - 1][y];
                if (values[3] < 9000) {
                    n++;
                }
            } catch (Exception e) {
                //DON'T GO HERE
                values[3] = 1000000;
            }
            /**
             * If there are 4 adjacent squares and I haven't been here before much, go straight
             */
            if (n == 4 && visits[x][y] < 4) {
                visits[x][y] -= 1;
                return dir;
            } else {
                /**
                 * Pick the square that's been visited the least
                 */
                n = 0;
                for (int i = 0; i < 4; i++) {
                    if (values[i] < min) {
                        min = values[i];
                        minDir = i;
                        n++;
                    } else if (values[i] == min) {
                        n++;
                        double m = (double) n;
                        if (Math.random() <= 1 / m) {
                            minDir = i;
                        }
                    }
                }
                return minDir;
            }
        }
        /* Directions:
         *   0 
         * 3<^>1
         *   2
         */
    }
}
