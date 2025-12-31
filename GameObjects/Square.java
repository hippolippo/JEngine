package GameObjects;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

import Display.Window;
import Spatial.Angle;
import Spatial.Vector2;

public class Square extends Object2D{
    Vector2 position;
    double size;
    Angle rotation;
    Color color;
    boolean hidden = false;

    public Square(Vector2 position, double size, Angle rotation, Color color){
        this.position = position;
        this.size = size;
        this.rotation = rotation;
        this.color = color;
    }

    public void draw(Window window, Camera2D camera) {
        Point p1 = position.add(new Vector2(size, rotation.rotateDegrees(45))).toScreenPoint();
        Point p2 = position.add(new Vector2(size, rotation.rotateDegrees(135))).toScreenPoint();
        Point p3 = position.add(new Vector2(size, rotation.rotateDegrees(225))).toScreenPoint();
        Point p4 = position.add(new Vector2(size, rotation.rotateDegrees(315))).toScreenPoint();

        Polygon shape = new Polygon(new int[]{p1.x,p2.x,p3.x,p4.x}, new int[]{p1.y,p2.y,p3.y,p4.y}, 4);
        window.setColor(color);
        window.drawPolygon(shape);
        window.fillPolygon(shape);
        
    }

    public void update(int delta) {
        // pass
    }
    
}
