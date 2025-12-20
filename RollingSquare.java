import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.io.IOException;

import Spatial.Angle;
import Spatial.Vector2;

public class RollingSquare extends Image{

    public RollingSquare() {
        super("donald.png", new Vector2(0, 300), new Vector2(.25, .25), null);
        //super(new Vector2(0, 300), 50, Angle.zero(), Color.RED);
    }

    @Override
    public void draw(Window window, Graphics2D g){
        java.awt.Point screenPosition = window.getLocationOnScreen();
        int screenX = screenPosition.x;
        double mouseX = MouseInfo.getPointerInfo().getLocation().getX() - screenX;
        position = new Vector2(mouseX, position.getY());
        rotation = Angle.fromDegrees(-mouseX/2);
        super.draw(window, g);
    } 
    
}
