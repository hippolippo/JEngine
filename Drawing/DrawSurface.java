package Drawing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Spatial.Point2;
import Textures.Texture2D;

public abstract class DrawSurface {
    private Graphics2D graphics;

    public DrawSurface(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawImage2D(Texture2D texture, AffineTransform transform) {
        drawImage2D(texture, transform, DrawStyle.DEFAULT);
    }

    public void drawImage2D(Texture2D texture, AffineTransform transform, DrawStyle style) {
        graphics.drawImage(texture.getImage(), transform, null);
    }

    public void drawPolygon(Point2[] points, AffineTransform transform) {
        drawPolygon(points, transform, DrawStyle.DEFAULT);
    }

    public void drawPolygon(Point2[] points) {
        drawPolygon(points, DrawStyle.DEFAULT);
    }
    public void updateGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }
    public void drawPolygon(Point2[] points, DrawStyle style) {
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i<points.length; i+=1) {
            xPoints[i] = (int)points[i].getX();
            yPoints[i] = (int)points[i].getY();
        }
        if (style.applyStroke(graphics)) {
            graphics.drawPolygon(xPoints, yPoints, points.length);
        }
        style.applyStyle(graphics);
        graphics.fillPolygon(xPoints, yPoints, points.length);
    }

    public void drawPolygon(Point2[] points, AffineTransform transform, DrawStyle style) {
        
        if (style == null) {
            style = DrawStyle.DEFAULT;
        }
        double[] xYArray = new double[points.length*2];
        for (int i = 0; i<points.length*2; i+=2) {
            xYArray[i] = points[i/2].getX();
            xYArray[i+1] = points[i/2].getY();
            
        }
        
        double[] movedPoints = new double[points.length*2];
        
        transform.transform(xYArray, 0, movedPoints, 0, points.length);
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i<points.length*2; i+=2) {
            xPoints[i/2] = (int)movedPoints[i];
            yPoints[i/2] = (int)movedPoints[i+1];
        }
        
        
        if (style.applyStroke(graphics)) {
            graphics.drawPolygon(xPoints, yPoints, points.length);
        }
        style.applyStyle(graphics);
        graphics.fillPolygon(xPoints, yPoints, points.length);
    }


    public abstract double getWidth();
    public abstract double getHeight();
}
