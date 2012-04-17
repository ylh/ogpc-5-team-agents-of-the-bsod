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

    public RevisedEnemyNavigation(Tile[][] tiles, Vector2 startPos, int direction) {
        this.tiles = tiles;
        this.startPos = startPos;
        this.direction = direction;
    }

    @Override
    public void run() {
        checkInPath();
    }

    public void checkInPath() {
        int x = (int) startPos.getX();
        int y = (int) startPos.getY();
        if (direction == UP) {
            if (y - 1 >= 0) {
                for (int i = y - 1; i >= 0; i--) {
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
        if (direction == RIGHT) {
            if (x + 1 >= 0) {
                for (int i = x + 1; i < tiles.length; i++) {
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
        if (direction == DOWN) {
            if (y + 1 < tiles[0].length) {
                for (int i = y + 1; i >= 0; i++) {
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
        if (direction == LEFT) {
            if (x - 1 >= 0) {
                for (int i = x - 1; i < tiles.length; i++) {
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

    public void needBranch(int x, int y, int currentDirection) {
        if (currentDirection == UP) {
            if (tiles[x + 1][y] instanceof Road) {
                Road r = (Road) tiles[x + 1][y];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT);
                }
            }
            if (tiles[x - 1][y] instanceof Road) {
                Road r = (Road) tiles[x - 1][y];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT);
                }
            }
            if (tiles[x][y + 1] instanceof Road) {
                Road r = (Road) tiles[x][y + 1];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN);
                }
            }
        }
        if (currentDirection == RIGHT) {
            if (tiles[x - 1][y] instanceof Road) {
                Road r = (Road) tiles[x - 1][y];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT);
                }
            }
            if (tiles[x][y + 1] instanceof Road) {
                Road r = (Road) tiles[x][y + 1];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN);
                }
            }
            if (tiles[x][y - 1] instanceof Road) {
                Road r = (Road) tiles[x][y - 1];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP);
                }
            }
        }
        if (currentDirection == DOWN) {
            if (tiles[x - 1][y] instanceof Road) {
                Road r = (Road) tiles[x - 1][y];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x - 1, y), LEFT);
                }
            }
            if (tiles[x][y - 1] instanceof Road) {
                Road r = (Road) tiles[x][y - 1];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP);
                }
            }
            if (tiles[x + 1][y] instanceof Road) {
                Road r = (Road) tiles[x + 1][y];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT);
                }
            }
        }
        if (currentDirection == LEFT) {
            if (tiles[x][y - 1] instanceof Road) {
                Road r = (Road) tiles[x][y - 1];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y - 1), UP);
                }
            }
            if (tiles[x + 1][y] instanceof Road) {
                Road r = (Road) tiles[x + 1][y];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x + 1, y), RIGHT);
                }
            }
            if (tiles[x][y + 1] instanceof Road) {
                Road r = (Road) tiles[x][y + 1];
                if (!r.getInPath()) {
                    RevisedEnemyNavigation nav = new RevisedEnemyNavigation(tiles, new Vector2(x, y + 1), DOWN);
                }
            }
        }
    }
}
