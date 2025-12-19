import java.util.Arrays;

public class Layer2D implements RenderLayer{
    Square[] objs;

    public Layer2D(){
        // UNIMPLEMENTED - PLACEHOLDER
        objs = new Square[]{
            new RollingSquare()
        };
    }

    @Override
    public Iterable<GameObject> getSortedGameObjects() {
        return Arrays.asList(objs);
    }
    
}
