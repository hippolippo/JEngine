import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import Spatial.Angle;
import Spatial.Vector2;

public class Square implements GameObject{
    Vector2 position;
    double size;
    Angle rotation;
    Color color;

    public Square(Vector2 position, double size, Angle rotation, Color color){
        this.position = position;
        this.size = size;
        this.rotation = rotation;
        this.color = color;
    }

    @Override
    public void draw(Window window, Graphics2D g) {
        Point p1 = position.add(new Vector2(size, rotation.rotateDegrees(45))).toScreenPoint();
        Point p2 = position.add(new Vector2(size, rotation.rotateDegrees(135))).toScreenPoint();
        Point p3 = position.add(new Vector2(size, rotation.rotateDegrees(225))).toScreenPoint();
        Point p4 = position.add(new Vector2(size, rotation.rotateDegrees(315))).toScreenPoint();

        Polygon shape = new Polygon(new int[]{p1.x,p2.x,p3.x,p4.x}, new int[]{p1.y,p2.y,p3.y,p4.y}, 4);
        g.setColor(color);
        g.drawPolygon(shape);
        g.fillPolygon(shape);
    }
    
}
