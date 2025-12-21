package GameObjects;

import Spatial.Angle;
import Spatial.Vector2;

public class Camera2D {
    Vector2 position = new Vector2(0, 0);
    Vector2 scale = new Vector2(1, 1);
    Angle rotation = Angle.zero();
    private final static Camera2D basic = new Camera2D();

    public Camera2D(Vector2 position, Vector2 scale, Angle rotation){
        if(position != null) this.position = position;
        if(scale != null) this.scale = scale;
        if(rotation != null) this.rotation = rotation;
    }

    public Camera2D(){}

    public static Camera2D basic(){
        return basic;
    }

}
