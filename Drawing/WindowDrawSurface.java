package Drawing;

import java.awt.Graphics2D;
import Display.Window;

public class WindowDrawSurface extends DrawSurface {
    Window window;

    public WindowDrawSurface(Graphics2D graphics, Window window) {
        super(graphics);
        this.window = window;
    }

    @Override
    public double getWidth() {
        return window.getSizeX();
    }

    @Override
    public double getHeight() {
        return window.getSizeY();
    }
}
