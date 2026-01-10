package GameObjects;

import java.awt.geom.AffineTransform;

import Drawing.DrawSurface;
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

    /**
     * Gets the affine transform for the specified object in the context of the camera.
     * @param object the object to get the transform for
     * @param windowWidth the width of the window
     * @param windowHeight the height of the window
     * @return the affine transform for the object
     */
    public AffineTransform getAffineTransformForObject(Object2D object, double windowWidth, double windowHeight) {
        AffineTransform transform = new AffineTransform();
        // Center
        transform.translate(windowWidth / 2.0, windowHeight / 2.0);
        // Camera
        transform.concatenate(getVisualTransform());
        // Object
        transform.concatenate(object.getVisualTransform());
        return transform;
    }
    public AffineTransform getAffineTransformForObject(Object2D object, DrawSurface surface) {
        return getAffineTransformForObject(object, surface.getWidth(), surface.getHeight());
    }

}
