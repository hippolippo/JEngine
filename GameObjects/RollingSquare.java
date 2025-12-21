package GameObjects;
import java.awt.MouseInfo;

import Display.Window;
import Spatial.Angle;
import Spatial.Vector2;

public class RollingSquare extends Image{

    public RollingSquare() {
        super("Assets/donald.png", new Vector2(0, 300), new Vector2(.25, .25), null);
        //super(new Vector2(0, 300), 50, Angle.zero(), Color.RED);
    }

    @Override
    public void draw(Window window, Camera2D camera){
        java.awt.Point screenPosition = window.getLocationOnScreen();
        int screenX = screenPosition.x;
        double mouseX = MouseInfo.getPointerInfo().getLocation().getX() - screenX;
        position = new Vector2(mouseX, position.getY());
        rotation = Angle.fromDegrees(-mouseX/2);
        super.draw(window, camera);
    } 
    
}
