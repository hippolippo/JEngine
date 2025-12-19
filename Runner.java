public class Runner {
    public static void main(String[] args){
        Renderer renderer = new Renderer();
        Window window = new Window("First Window", false, renderer);
        window.start();
    }
}
