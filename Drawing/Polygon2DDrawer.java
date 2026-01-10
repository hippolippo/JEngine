package Drawing;

import java.awt.geom.AffineTransform;

import GameObjects.Camera2D;
import GameObjects.Object2D;
import Spatial.Point2;

/**
 * A class that game objects can use to draw 2D polygons.
 * 
 * @author Caleb Haftel
 */
public class Polygon2DDrawer implements Drawer2D{
    Object2D gameObject;
    Point2[] points;
    DrawStyle style;

    /**
     * Creates a new Polygon2DDrawer for a game object with the specified points and style.
     * @param gameObject the game object to draw
     * @param points the points that define the polygon
     * @param style the drawing style to use
     */
    public Polygon2DDrawer(Object2D gameObject, Point2[] points, DrawStyle style){
        this.gameObject = gameObject;
        this.points = points;
        this.style = style;
    }
    
    /**
     * Draws the polygon on the given draw surface using the specified camera.
     * @param surface the draw surface to draw on
     * @param camera the camera to use for transformation
    */
    @Override
    public void draw(DrawSurface surface, Camera2D camera) {
        if (points == null) return;
        AffineTransform transform = camera.getAffineTransformForObject(gameObject, surface);
        surface.drawPolygon(points, transform, style);
    }

}
