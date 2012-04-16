package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;
import java.util.ArrayList;

/**
 *
 * @author tsutton14
 */
public class RevisedEnemyNavigation extends Thread {
    
    Tile[][] tiles;
    Vector2 goalPos;
    ArrayList<Road> pathRoads = new ArrayList<Road>();
    
    public RevisedEnemyNavigation(Tile[][] tiles, Vector2 goalPos){
        this.tiles = tiles;
        this.goalPos = goalPos;
    }
    
    @Override
    public void run(){
        
    }
    
    public void checkInPath(Vector2 startRoad){
        int x = (int)startRoad.getX();
        int y = (int)startRoad.getY();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if(tiles[i][j] instanceof Road){
                    Road r1 = (Road)tiles[i][j];
                    r1.setInPath(false);
                    if(pathRoads.contains(r1)){
                        pathRoads.remove(r1);
                    }
                }
            }
        }
        if(tiles[x][y] instanceof Road){
            Road r = (Road)tiles[x][y];
            r.setInPath(true);
            pathRoads.add(r);
            if (x < tiles.length) {
                if (isRoad(new Vector2(startRoad.getX()+1, startRoad.getY()))) {
                    Road r2 = (Road)tiles[x+1][y];
                    r2.setInPath(true);
                    pathRoads.add(r2);
                }
            }
            if (x > 0) {
                if (isRoad(new Vector2(startRoad.getX()-1, startRoad.getY()))) {
                    Road r2 = (Road)tiles[x-1][y];
                    r2.setInPath(true);
                    pathRoads.add(r2);
                }
            }
            if (y < tiles[0].length) {
                if (isRoad(new Vector2(startRoad.getX(), startRoad.getY()+1))) {
                    Road r2 = (Road)tiles[x][y+1];
                    r2.setInPath(true);
                    pathRoads.add(r2);
                }
            }
            if (y > 0) {
                if (isRoad(new Vector2(startRoad.getX(), startRoad.getY()-1))) {
                    Road r2 = (Road)tiles[x][y-1];
                    r2.setInPath(true);
                    pathRoads.add(r2);
                }
            }
        }
        
        for(Road r3 : pathRoads){
            Vector2 v = new Vector2((int)r3.getPosition().getX()/32,(int)r3.getPosition().getY()/32);
            checkInPath(v);
        }
    }
    
    private boolean isRoad(Vector2 targetPos){
        if(tiles[(int)targetPos.getX()][(int)targetPos.getY()] instanceof Road){
            return true;
        }
        else return false;
    }
    
}
