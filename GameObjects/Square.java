package GameObjects;

import java.awt.BasicStroke;
import java.awt.Color;
import Drawing.DrawStyle;
import Drawing.Polygon2DDrawer;
import Spatial.Angle;
import Spatial.Point2;

public class Square extends Object2D{
    public Square(Point2 position, double size, Point2 scale, Angle rotation){
        Polygon2DDrawer drawer = new Polygon2DDrawer(this, new Point2[]{new Point2(size/2, size/2), new Point2(size/2, size/-2), new Point2(size/-2, size/-2), new Point2(size/-2, size/2)}, new DrawStyle(Color.ORANGE, Color.BLACK, new BasicStroke(10)));
        this.drawer = drawer;
        setPosition(position == null ? Point2.zero() : position);
        setScale(scale == null ? new Point2(1,1) : scale);
        setRotation(rotation == null ? Angle.zero() : rotation);
    }

    public void update(int delta) {
        // pass
    }  
}
