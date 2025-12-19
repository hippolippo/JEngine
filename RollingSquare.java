import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import Spatial.Angle;
import Spatial.Vector2;

public class RollingSquare extends Square{

    public RollingSquare() {
        super(new Vector2(0, 300), 50, Angle.zero(), Color.RED);
    }

    @Override
    public void draw(Graphics g){
        double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
        position = new Vector2(mouseX, position.getY());
        rotation = Angle.fromDegrees(-mouseX/2);
        super.draw(g);
    } 
    
}
