package Display;
import javax.swing.JFrame;

import Textures.Texture2D;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window extends Canvas implements Runnable{
    private boolean running = true;
    private String title;
    private boolean fullscreen;
    private Thread thread;
    private JFrame frame;
    private Renderer renderer;
    private Graphics2D graphics;
    
    public Window(String title, boolean fullscreen, Renderer renderer){
        this.title = title;
        this.fullscreen = fullscreen;
        this.renderer = renderer;
    }

    public void start(){
        frame = new JFrame(title);
        if(fullscreen){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }else{
            this.setSize(800,600);
        }
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        thread = new Thread(this);
        thread.start();
    }

    public void setFullscreen(boolean enable) {
        // 1. If the state is already correct, do nothing
        if (this.fullscreen == enable) return;
    
        synchronized(this){
            this.fullscreen = enable;
            frame.dispose();
            if (enable) {
                frame.setUndecorated(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                frame.setUndecorated(false);
                frame.setExtendedState(JFrame.NORMAL);
                this.setSize(800, 600);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        
            frame.setVisible(true);
            this.createBufferStrategy(2);
            this.requestFocus();
        }
    }

    public void setMouseVisibility(boolean enable) {
        if(enable){
            frame.getContentPane().setCursor(Cursor.getDefaultCursor());
        }else{
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

            // Set the blank cursor to the JFrame.
            frame.getContentPane().setCursor(blankCursor);
        }
    }

    public void stop(){
        running = false;
    }
  
    public void run(){
        this.createBufferStrategy(2);
        
        while(running){
            synchronized(this) {
                BufferStrategy bs = this.getBufferStrategy();
                if(bs == null) continue;

                try{
                    graphics = (Graphics2D)bs.getDrawGraphics();
                    // Set antialiasing polygon hints
                    graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                
                    // 3. Antialiasing: Keeps the edges of the rotated image container smooth
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                        RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics.setColor(java.awt.Color.WHITE);
                    graphics.fillRect(0, 0, getWidth(), getHeight());
                    renderer.Render(this, graphics);
                    graphics.dispose();
                    bs.show();
                    // Wait a short period to avoid excessive resource usage
                    Thread.sleep(2);
                } catch(Exception e) {
                    //Safe to ignore
                }
            }
        }
    }

    public void drawImage2D(Texture2D texture, AffineTransform transform) {
        graphics.drawImage(texture.getImage(), transform, null);
    }

    public void setColor(Color color) {
        graphics.setColor(color);
    }

    public void drawPolygon(Polygon shape) {
        graphics.drawPolygon(shape);
    }

    public void fillPolygon(Polygon shape) {
        graphics.fillPolygon(shape);
    }

    
}
