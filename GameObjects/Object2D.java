package GameObjects;

import Display.Window;
import Spatial.Angle;
import Spatial.Point2;
import Utilities.Lerper;

public abstract class Object2D extends GameObject{
    public Point2 position = Point2.zero();
    public Point2 scale = Point2.unit();
    public Angle rotation = Angle.zero();
    
    private Lerper<Point2> visualPosition = new Lerper<Point2>(position, position, 0);
    private Lerper<Point2> visualScale = new Lerper<Point2>(scale, scale, 0);
    private Lerper<Angle> visualRotation = new Lerper<Angle>(rotation, rotation, 0);


    public abstract void draw(Window window, Camera2D camera);

    public void setPosition(Point2 point, int delta){
        position = point;
        visualPosition.reroute(point, delta);
    }

    public void setPosition(Point2 point){
        setPosition(point, 0);
    }

    public void setScale(Point2 scale, int delta){
        this.scale = scale;
        visualScale.reroute(scale, delta);
    }

    public void setScale(Point2 scale){
        setScale(scale, 0);
    }

    public void setRotation(Angle rotation, int delta){
        this.rotation = rotation;
        visualRotation.reroute(rotation, delta);
    }

    public void setRotation(Angle rotation){
        setRotation(rotation, 0);
    }

    public Point2 getDrawPosition(){
        return visualPosition.get();
    }
    public Point2 getDrawScale(){
        return visualScale.get();
    }
    public Angle getDrawRotation(){
        return visualRotation.get();
    }

}
