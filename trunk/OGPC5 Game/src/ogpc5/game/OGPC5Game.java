/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Game.GameBase;

/**
 *
 * @author RomulusAaron
 */
public class OGPC5Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GameBase(new CityGame(), "Agents of the BSoD").Run();
    }
}
