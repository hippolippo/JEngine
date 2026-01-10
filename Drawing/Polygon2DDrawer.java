package Drawing;

import java.awt.geom.AffineTransform;

import GameObjects.Camera2D;
import GameObjects.Object2D;
import Spatial.Point2;

public class Polygon2DDrawer implements Drawer2D{
    Object2D gameObject;
    Point2[] points;
    DrawStyle style;


    public Polygon2DDrawer(Object2D gameObject, Point2[] points, DrawStyle style){
        this.gameObject = gameObject;
        this.points = points;
        this.style = style;
    }
    
    public void draw(DrawSurface surface, Camera2D camera) {
        if (points == null) return;
        AffineTransform transform = camera.getAffineTransformForObject(gameObject, surface);
        surface.drawPolygon(points, transform, style);
    }

}
