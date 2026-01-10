package Drawing;

import Display.Window;

/**
 * A class representing a drawing surface for the window, adding specialized getWidth and getHeight methods.
 * 
 * @author Caleb Haftel
 */
public class WindowDrawSurface extends DrawSurface {
    Window window;

    /**
     * Creates a WindowDrawSurface that is associated with the given window.
     * @param window the window to associate with this draw surface
     */
    public WindowDrawSurface(Window window) {
        super(null);
        this.window = window;
    }

    /**
     * Get the width of the drawing surface.
     * @return drawing surface width
     */
    @Override
    public double getWidth() {
        return window.getWidth();
    }

    /**
     * Get the height of the drawing surface.
     * @return drawing surface height
     */
    @Override
    public double getHeight() {
        return window.getHeight();
    }
}
