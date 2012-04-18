package Enemies;

import GUIStuff.Tile;
import Utilities.Vector2;
import WorldObjects.towers.Road;

/**
 *
 * @author tsutton14
 */
public class RevisedEnemyNavigation extends Thread {

    Tile[][] tiles;
    Vector2 startPos;
    int direction;
    private final int UP = 0;
    private final int RIGHT = 1;
    private final int LEFT = 2;
    private final int DOWN = 3;
    boolean deleting;

    public RevisedEnemyNavigation(Tile[][] tiles, Vector2 startPos, int direction, boolean deleting) {
        this.tiles = tiles;
        this.startPos = startPos;
        this.direction = direction;
        this.deleting = deleting;
    }

    @Override
    public void run() {
        checkInPath();
    }

    public void checkInPath() {
        int x = (int) startPos.getX();
        int y = (int) startPos.getY();
        if (!deleting) {
            if (direction == UP) {
                if (y - 1 >= 0) {
                    for (int i = y - 1; i >= 0; i--) {
                        if (i >= 0) {
                            if (tiles[x][i] instanceof Road) {
                                Road r = (Road) tiles[x][i];
                                if (!r.getInPath()) {
                                    r.setInPath(true);
                                    if (i - 1 >= 0) {
                                        if (tiles[x][i - 1] instanceof Road) {
                                            needBranch(x, i - 1, UP);
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (direction == RIGHT) {
                if (x + 1 >= 0) {
                    for (int i = x + 1; i < tiles.length; i++) {
                        if (i < tiles.length) {
                            if (tiles[i][y] instanceof Road) {
                                Road r = (Road) tiles[i][y];
                                if (!r.getInPath()) {
                                    r.setInPath(true);
                                    if (i + 1 < tiles.length) {
                                        if (tiles[i + 1][y] instanceof Road) {
                                            needBranch(i + 1, y, RIGHT);
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }

                        }
                    }
                }
            }
            if (direction == DOWN) {
                if (y + 1 < tiles[0].length) {
                    for (int i = y + 1; i < tiles[0].length; i++) {
                        if (i >= 0) {
                            if (tiles[x][i] instanceof Road) {
                                Road r = (Road) tiles[x][i];
                                if (!r.getInPath()) {
                                    r.setInPath(true);
                                    if (i + 1 < tiles[0].length) {
                                        if (tiles[x][i + 1] instanceof Road) {
                                            needBranch(x, i + 1, DOWN);
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }

                        }
                    }
                }
            }
            if (direction == LEFT) {
                if (x - 1 >= 0) {
                    for (int i = x - 1; i >= 0; i--) {
                        if (i >= 0) {
                            if (tiles[i][y] instanceof Road) {
                                Road r = (Road) tiles[i][y];
                                if (!r.getInPath()) {
                                    r.setInPath(true);
                                    if (i - 1 >= 0) {
                                        if (tiles[i - 1][y] instanceof Road) {
                                            needBranch(i - 1, y, LEFT);
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (direction == UP) {
                if (y - 1 >= 0) {
                    for (int i = y - 1; i >= 0; i--) {
                        if (i >= 0) {
                            if (tiles[x][i] instanceof Road) {
                                Road r = (Road) tiles[x][i];
                                if (!stillPath(x, i)) {
                                    r.setInPath(false);
                                    if (i - 1 >= 0) {
                                        if (tiles[x][i - 1] instanceof Road) {
                                            needBranch(x, i - 1, UP);
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (direction == RIGHT) {
                if (x + 1 >= 0) {
                    for (int i = x + 1; i < tiles.length; i++) {
                        if (i < tiles.length) {
                            if (tiles[i][y] instanceof Road) {
                                Road r = (Road) tiles[i][y];
                                if (!stillPath(i, y)) {
                                    r.setInPath(false);
                                    if (i + 1 < tiles.length) {
                                        if (tiles[i + 1][y] instanceof Road) {
                                            needBranch(i + 1, y, RIGHT);
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (direction == DOWN) {
                if (y + 1 < tiles[0].length) {
                    for (int i = y + 1; i < tiles[0].length; i++) {
                        if (i < tiles[0].length) {
                            if (tiles[x][i] instanceof Road) {
                                Road r = (Road) tiles[x][i];
                                if (!stillPath(x, i)) {
                                    r.setInPath(false);
                                    if (i + 1 < tiles[0].length) {
                                        if (tiles[x][i + 1] instanceof Road) {
                                            needBranch(x, i + 1, DOWN);
                                        } else {
                                            break;
                                        }

                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (direction == LEFT) {
                if (x - 1 >= 0) {
                    for (int i = x - 1; i >= 0; i--) {
                        if (i >= 0) {
                            if (tiles[i][y] instanceof Road) {
                                Road r = (Road) tiles[i][y];
                                if (!stillPath(i, y)) {
                                    r.setInPath(false);
                                    if (i - 1 >= 0) {
                                        if (tiles[i - 1][y] instanceof Road) {
                                            needBranch(i - 1, y, LEFT);
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void needBranch(int x, int y, int currentDirection) {
        if (currentDirection == UP) {
            if (tiles[x + 1][y] instanceof Road) {
                Road r = (Road) tiles[x + 1][y];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT, false);
                    }

                } else {
                    if (r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT, true);
                    }
                }
            }
            if (tiles[x - 1][y] instanceof Road) {
                Road r = (Road) tiles[x - 1][y];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT, false);
                    }
                } else {
                    if (r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT, true);
                    }
                }
            }
            if (tiles[x][y + 1] instanceof Road) {
                Road r = (Road) tiles[x][y + 1];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN, false);
                    } else {
                        if (r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN, true);
                        }
                    }
                }
            }
        }
        if (currentDirection == RIGHT) {
            if (tiles[x - 1][y] instanceof Road) {
                Road r = (Road) tiles[x - 1][y];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT, false);
                    }
                } else {
                    if (r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT, true);
                    }
                }
            }
            if (tiles[x][y + 1] instanceof Road) {
                Road r = (Road) tiles[x][y + 1];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN, false);
                    } else {
                        if (r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN, true);
                        }
                    }
                }
            }
        }
        if (tiles[x][y - 1] instanceof Road) {
            Road r = (Road) tiles[x][y - 1];
            if (!deleting) {
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP, false);
                }
            } else {
                if (r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP, true);
                }
            }
        }
        if (currentDirection == DOWN) {
            if (tiles[x - 1][y] instanceof Road) {
                Road r = (Road) tiles[x - 1][y];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT, false);
                    }
                } else {
                    if (r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT, true);
                    }
                }
            }
            if (tiles[x][y - 1] instanceof Road) {
                Road r = (Road) tiles[x][y - 1];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP, false);
                    }
                } else {
                    if (r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP, true);
                    }
                }
            }
            if (tiles[x + 1][y] instanceof Road) {
                Road r = (Road) tiles[x + 1][y];
                if (!deleting) {
                    if (!r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT, false);
                    }

                } else {
                    if (r.getInPath()) {
                        RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT, true);
                    }
                }
            }
            if (currentDirection == LEFT) {
                if (tiles[x][y - 1] instanceof Road) {
                    Road r = (Road) tiles[x][y - 1];
                    if (!deleting) {
                        if (!r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP, false);
                        }
                    } else {
                        if (r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP, true);
                        }
                    }
                }
                if (tiles[x + 1][y] instanceof Road) {
                    Road r = (Road) tiles[x + 1][y];
                    if (!deleting) {
                        if (!r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT, false);
                        }

                    } else {
                        if (r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT, true);
                        }
                    }
                }
                if (tiles[x][y + 1] instanceof Road) {
                    Road r = (Road) tiles[x][y + 1];
                    if (!deleting) {
                        if (!r.getInPath()) {
                            RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN, false);
                        } else {
                            if (r.getInPath()) {
                                RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN, true);
                            }
                        }
                    }
                }
            }
        }
    }
    
        public boolean stillPath(int x, int y){
        if(x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length){
            if(tiles[x][y] instanceof Road){
                Road r1;
                Road r2;
                Road r3;
                Road r4;
                boolean path1 = false, path2 = false, path3 = false, path4 = false;
                if(x - 1 >= 0){
                    r1 = (Road)tiles[x-1][y];
                    path1 = r1.getInPath();
                }
                if(x + 1 < tiles.length){
                    r2 = (Road)tiles[x+1][y];
                    path2 = r2.getInPath();
                }   
                if(y - 1 >= 0){
                    r3 = (Road)tiles[x][y-1];
                    path3 = r3.getInPath();
                }   
                if(y + 1 < tiles[0].length){
                    r4 = (Road)tiles[x][y+1];
                    path4 = r4.getInPath();
                }   
                if(path1 || path2 || path3 || path4){
                    return true;
                }
            }
        }
        return false;
    }
}