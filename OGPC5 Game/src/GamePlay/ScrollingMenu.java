/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.ImageCollection;
import Utilities.Vector2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Nekel-Seyew
 */
public interface ScrollingMenu {
    public void Draw(ImageCollection batch);
    public void GiveWheelEvent(MouseWheelEvent e);
    public Object giveMouseEvent(MouseEvent e);
    public void setOffset(Vector2 offset);
}
