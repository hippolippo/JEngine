package Drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class DrawStyle {

    public static final DrawStyle DEFAULT = new DrawStyle();

    private Stroke stroke;
    private Color color;
    private Color outlineColor;

    public DrawStyle(Color color, Color outlineColor, Stroke stroke) {
        this.color = color;
        this.outlineColor=outlineColor;
        this.stroke = stroke;
    }
    public DrawStyle(Color color) {
        this(color, color, null);
    }
    public DrawStyle() {
        this(Color.WHITE);
    }


    void applyStyle(Graphics2D graphics) {
        graphics.setColor(color);
    }

    boolean applyStroke(Graphics2D graphics) {
        if (stroke==null) {
            return false;
        } else {
            graphics.setStroke(stroke);
            graphics.setColor(outlineColor);
            return true;
        }
    }
}
