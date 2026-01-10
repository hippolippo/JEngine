package Drawing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Spatial.Point2;
import Textures.Texture2D;

/**
 * A class representing a drawing surface for 2D graphics.
 * 
 * @author Caleb Haftel
 */
public abstract class DrawSurface {
    protected Graphics2D graphics;

    /**
     * Creates a new draw surface that draws to the given Graphics2D object.
     * @param graphics the Graphics2D object to draw to
     */
    public DrawSurface(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * Draws a 2D image with a style and transformation applied.
     * @param texture the texture to draw
     * @param transform the transformation to apply
     * @param style the drawing style to use
     */
    public void drawImage2D(Texture2D texture, AffineTransform transform, DrawStyle style) {
        graphics.drawImage(texture.getImage(), transform, null);
    }

    /**
     * Draws a 2D image with a transformation applied.
     * @param texture the texture to draw
     * @param transform the transformation to apply
     */
    public void drawImage2D(Texture2D texture, AffineTransform transform) {
        drawImage2D(texture, transform, DrawStyle.DEFAULT);
    }

    /**
     * Draws a polygon defined by the given points, applying the specified transformation and style.
     * @param points the points that define the polygon
     * @param transform the transformation to apply
     * @param style the drawing style to use
     */
    public void drawPolygon(Point2[] points, AffineTransform transform, DrawStyle style) {
        
        if (style == null) {
            style = DrawStyle.DEFAULT;
        }
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        if (transform == null) {
            for (int i = 0; i<points.length; i+=1) {
                xPoints[i] = (int)points[i].getX();
                yPoints[i] = (int)points[i].getY();
            }
        } else {
            double[] xYArray = new double[points.length*2];
            for (int i = 0; i<points.length*2; i+=2) {
                xYArray[i] = points[i/2].getX();
                xYArray[i+1] = points[i/2].getY();
                
            }
            
            double[] movedPoints = new double[points.length*2];
            
            transform.transform(xYArray, 0, movedPoints, 0, points.length);
            
            for (int i = 0; i<points.length*2; i+=2) {
                xPoints[i/2] = (int)movedPoints[i];
                yPoints[i/2] = (int)movedPoints[i+1];
            }
        }

        style.applyColor(graphics);
        graphics.fillPolygon(xPoints, yPoints, points.length);
        if (style.applyStrokeStyle(graphics)) {
            graphics.drawPolygon(xPoints, yPoints, points.length);
        }
    }

    /**
     * Draws a polygon defined by the given points, applying the specified transformation.
     * @param points the points that define the polygon
     * @param transform the transformation to apply
     */
    public void drawPolygon(Point2[] points, AffineTransform transform) {
        drawPolygon(points, transform, DrawStyle.BLACK);
    }

    /**
     * Draws a polygon defined by the given points with the specified style.
     * @param points the points that define the polygon
     * @param style the drawing style to use
     */
    public void drawPolygon(Point2[] points, DrawStyle style) {
        drawPolygon(points, null, style);
    }
    
    /**
     * Draws a polygon defined by the given points.
     * @param points the points that define the polygon
     */
    public void drawPolygon(Point2[] points) {
        drawPolygon(points, DrawStyle.BLACK);
    }

    /**
     * Replaces the underlying Graphics2D object used for drawing.
     * @param graphics the Graphics2D object to use
     */
    public void updateGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * Gets the width of the drawing surface.
     * @return the width of the drawing surface
     */
    public abstract double getWidth();

    /**
     * Gets the height of the drawing surface.
     * @return the height of the drawing surface
     */
    public abstract double getHeight();
}
