package Drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * A class representing the style used for drawing 2D objects.
 * 
 * @author Caleb Haftel
 */
public class DrawStyle {

    public static final DrawStyle DEFAULT = new DrawStyle();
    public static final DrawStyle BLACK = new DrawStyle(Color.BLACK);

    private Stroke stroke;
    private Color color;
    private Color outlineColor;

    /**
     * Creates a new DrawStyle with the specified color, outline color, and stroke.
     * @param color the color to use for filling shapes
     * @param outlineColor the color to use for object's outlines
     * @param stroke the stroke to use for object's outlines
    */
    public DrawStyle(Color color, Color outlineColor, Stroke stroke) {
        this.color = color;
        this.outlineColor=outlineColor;
        this.stroke = stroke;
    }

    /**
     * Creates a new DrawStyle with the specified fill color. Outline color and stroke are set to null.
     * @param color the color to use for filling shapes
     */
    public DrawStyle(Color color) {
        this(color, null, null);
    }

    /**
     * Creates a new DrawStyle with default white fill color. Outline color and stroke are set to null.
     */
    public DrawStyle() {
        this(Color.WHITE);
    }

    /**
     * Applies the style to the given Graphics2D object to prepare it for filling shapes.
     * @param graphics the Graphics2D object to apply the fill style to
    */
    void applyFillStyle(Graphics2D graphics) {
        graphics.setColor(color);
    }

    /**
     * Applies the style to the given Graphics2D object to prepare it for outlining shapes.
     * @param graphics the Graphics2D object to apply the stroke style to.
     * @return true if the objects has a stroke, false if it doesn't.
    */
    boolean applyStrokeStyle(Graphics2D graphics) {
        if (stroke==null) {
            return false;
        } else {
            graphics.setStroke(stroke);
            graphics.setColor(outlineColor);
            return true;
        }
    }
}
