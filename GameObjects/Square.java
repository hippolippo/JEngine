package GameObjects;

import java.awt.BasicStroke;
import java.awt.Color;
import Drawing.DrawStyle;
import Drawing.Polygon2DDrawer;
import Spatial.Angle;
import Spatial.Point2;

/**
    * A class representing a 2D square object.
    * @author Caleb Haftel
*/
public class Square extends Object2D{

    /**
     * Creates a new Square with the specified position, size, scale, and rotation.
     * @param position the position of the square
     * @param size the size of the square (length of one side)
     * @param scale the scale of the square (default is 1,1)
     * @param rotation the rotation of the square (default is 0 degrees)
     */
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
