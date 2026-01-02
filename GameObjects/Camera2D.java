package GameObjects;

import Spatial.Angle;
import Spatial.Point2;

public class Camera2D extends Object2D{

    private final static Camera2D basic = new Camera2D();

    public Camera2D(Point2 position, Point2 scale, Angle rotation){
        if(position != null) setPosition(position);
        if(scale != null) setScale(scale);
        if(rotation != null) setRotation(rotation);
    }

    public Camera2D(){
        // pass
    }

    public static Camera2D basic(){
        return basic;
    }

    @Override
    public void update(int delta) {
        // pass
    }

}
