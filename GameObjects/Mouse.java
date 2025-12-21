package GameObjects;

import java.awt.MouseInfo;

import Display.Window;
import Spatial.Vector2;
import Textures.Texture2D;

public class Mouse extends Image{
    public Mouse(Texture2D texture){
        super(texture, null, null, null);
        setOffset(Vector2.zero());
    }

    @Override
    public void draw(Window window, Camera2D camera){
        //Ignore Camera, draw to exact cursor position
        java.awt.Point screenPosition = window.getLocationOnScreen();
        java.awt.Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        position = new Vector2(mousePosition.getX() - screenPosition.getX(), mousePosition.getY() - screenPosition.getY());
        super.draw(window, Camera2D.basic());
    }
}
