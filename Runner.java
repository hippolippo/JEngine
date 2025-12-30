
import Display.Layer2D;
import Display.Renderer;
import Display.Window;
import Game.GameManager;

public class Runner {
    public static void main(String[] args){
        Renderer renderer = new Renderer();
        Window window = new Window("Rolling Square Demo", false, renderer);
        window.start();
        window.setMouseVisibility(false);
        GameManager gm = new GameManager(window,60);
        gm.subscribe(Layer2D.mouse);
        gm.subscribe(Layer2D.rs);
        gm.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        window.setFullscreen(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        window.setFullscreen(false);
    }
}
