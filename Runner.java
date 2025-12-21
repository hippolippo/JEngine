import Display.Renderer;
import Display.Window;

public class Runner {
    public static void main(String[] args){
        Renderer renderer = new Renderer();
        Window window = new Window("Rolling Square Demo", false, renderer);
        window.start();
        window.setMouseVisibility(false);
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
