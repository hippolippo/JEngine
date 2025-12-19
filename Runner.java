public class Runner {
    public static void main(String[] args){
        Renderer renderer = new Renderer();
        Window window = new Window("Rolling Square Demo", false, renderer);
        window.start();
    }
}
