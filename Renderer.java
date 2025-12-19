import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

public class Renderer {
    public void Render(Window window, Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, window.getWidth(), window.getHeight());
        
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        g.setColor(Color.GREEN);
        g.fillOval((int)mousePos.getX()-50, (int)mousePos.getY()-50, 100, 100); // Example Shape
    }
}
