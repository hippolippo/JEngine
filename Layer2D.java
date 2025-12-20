import java.util.Arrays;

public class Layer2D implements RenderLayer{
    GameObject[] objs;

    public Layer2D(){
        // UNIMPLEMENTED - PLACEHOLDER
        objs = new GameObject[]{
            new RollingSquare()
        };
    }

    @Override
    public Iterable<GameObject> getSortedGameObjects() {
        return Arrays.asList(objs);
    }
    
}
