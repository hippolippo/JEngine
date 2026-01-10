package Drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * A class representing the color and stroke information used for drawing shapes and lines.
 * 
 * @author Caleb Haftel
 */
public class DrawStyle {

    public static final DrawStyle DEFAULT = new DrawStyle();
    public static final DrawStyle BLACK = new DrawStyle(Color.BLACK);

    private Stroke stroke;
    private Color color;
    private Color strokeColor;

    /**
     * Creates a new DrawStyle with the specified color, stroke color, and stroke information.
     * @param color the fill color object
     * @param strokeColor the stroke color object
     * @param stroke the stroke information object
    */
    public DrawStyle(Color color, Color strokeColor, Stroke stroke) {
        this.color = color;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
    }

    /**
     * Creates a new DrawStyle with the specified fill color and no stroke.
     * @param color the color to use for filling shapes
     */
    public DrawStyle(Color color) {
        this(color, null, null);
    }

    /**
     * Creates a default DrawStyle
     */
    public DrawStyle() {
        this(Color.WHITE);
    }

    /**
     * Applies the fill color to a Graphics2D object
     * @param graphics the Graphics2D object to apply the fill color to
    */
    void applyColor(Graphics2D graphics) {
        graphics.setColor(color);
    }

    /**
     * Applies the stroke style to a given Graphics2D object.
     * @param graphics the Graphics2D object to apply the stroke style to.
     * @return boolean describing whether the style has stroke information
    */
    boolean applyStrokeStyle(Graphics2D graphics) {
        if (stroke==null) {
            return false;
        } else {
            graphics.setStroke(stroke);
            graphics.setColor(strokeColor);
            return true;
        }
    }
}
