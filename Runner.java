
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
        GameManager gm = new GameManager(window,20);
        gm.subscribe(Layer2D.mouse);
        gm.subscribe(Layer2D.rs);
        gm.start();
    }
}
