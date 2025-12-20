import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

public class Window extends Canvas implements Runnable{
    private boolean running = true;
    private String title;
    private boolean fullscreen;
    private Thread thread;
    private JFrame frame;
    private Renderer renderer;
    
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
                    Graphics2D g = (Graphics2D)bs.getDrawGraphics();
                    RenderingHints hints = new RenderingHints(
                        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
                    );
                    g.setRenderingHints(hints);
                    g.setColor(java.awt.Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    renderer.Render(this, g);
                    g.dispose();
                    bs.show();
                } catch(IllegalStateException e) {
                    //only occurs during fullscreen switch, can skip frame
                }
            }
        }
    }
}
