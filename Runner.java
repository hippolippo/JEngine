
import Display.Layer2D;
import Display.Renderer;
import Display.Window;
import Game.GameManager;

public class Runner {
    public static void main(String[] args){
        System.setProperty("sun.awt.noerasebackground", "true");
        Renderer renderer = new Renderer();
        Window window = new Window("Rolling Square Demo", false, renderer);
        GameManager gm = new GameManager(window,60);
        gm.subscribe(Layer2D.mouse);
        gm.start();
        window.start();
        window.setMouseVisibility(false);
    }
}
