import java.awt.Graphics2D;
import java.util.ArrayList;

public class Renderer {

    ArrayList<RenderLayer> layers;

    public Renderer(){
        // UNIMPLEMENTED - PLACEHOLDER
        layers = new ArrayList<>();
        layers.add(new Layer2D());
    }

    public void Render(Window window, Graphics2D g){
        for(RenderLayer layer: layers){
            for(GameObject obj: layer.getSortedGameObjects()){
                obj.draw(window, g);
            }
        }
    }
}
