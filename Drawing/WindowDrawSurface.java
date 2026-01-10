package Drawing;

import java.awt.Graphics2D;
import Display.Window;

/**
 * A class representing a drawing surface for the window, adding specialized getWidth and getHeight methods.
 * 
 * @author Caleb Haftel
 */
public class WindowDrawSurface extends DrawSurface {
    Window window;

    /**
     * Makes a new WindowDrawSurface that draws to the given Graphics2D object and is associated with the given window.
     * @param graphics the Graphics2D object to draw to
     * @param window the window to associate with this draw surface
     */
    public WindowDrawSurface(Graphics2D graphics, Window window) {
        super(graphics);
        this.window = window;
    }

    /**
     * Gets the width of the drawing surface.
     * @return the width of the drawing surface
     */
    @Override
    public double getWidth() {
        return window.getSizeX();
    }

    /**
     * Gets the height of the drawing surface.
     * @return the height of the drawing surface
     */
    @Override
    public double getHeight() {
        return window.getSizeY();
    }
}
