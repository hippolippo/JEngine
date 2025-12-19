import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
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

        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        running = false;
    }
  
    public void run(){
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        
        while(running){
            Graphics g = bs.getDrawGraphics();
            renderer.Render(this, g);
            g.dispose();
            bs.show();
        }
    }
}
