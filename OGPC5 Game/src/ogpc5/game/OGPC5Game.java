/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogpc5.game;

import Game.GameBase;
import Utilities.Image2D;

/**
 *
 * @author Nekel_Seyew
 */
public class OGPC5Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        GameBase g=new GameBase(new CityGame(), "Agents of the BSoD");
        g.theGUI.setIconImage(new Image2D("Game Resources/Sprites/August/Game Icon.png").getImage());
        g.Run();
    }
}
