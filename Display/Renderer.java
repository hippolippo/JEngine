package Display;
import java.util.ArrayList;

import Drawing.DrawSurface;

public class Renderer {

    ArrayList<RenderLayer> layers;

    public Renderer(){
        // UNIMPLEMENTED - PLACEHOLDER
        layers = new ArrayList<>();
        layers.add(new Layer2D());
    }

    public void Render(DrawSurface surface){
        for(RenderLayer layer: layers){
            layer.draw(surface);
        }
    }
}
