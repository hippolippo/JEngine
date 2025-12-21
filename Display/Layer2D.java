package Display;
import java.awt.Graphics2D;
import java.util.Arrays;

import GameObjects.Camera2D;
import GameObjects.Object2D;
import GameObjects.RollingSquare;
import GameObjects.Mouse;
import Textures.Texture2D;

public class Layer2D implements RenderLayer{
    Object2D[] objs;
    Camera2D camera;

    public Layer2D(){
        camera = new Camera2D();
        // UNIMPLEMENTED - PLACEHOLDER
        objs = new Object2D[]{
            new RollingSquare(), new Mouse(new Texture2D("Assets/cursor.png"))
        };
    }

    public Iterable<Object2D> getSortedGameObjects() {
        return Arrays.asList(objs);
    }

    @Override
    public void draw(Window window, Graphics2D g) {
        for(Object2D obj: getSortedGameObjects()){
            if(obj.isHidden()) continue;
            obj.draw(window, camera);
        }
    }
    
}
