package GameObjects;

import Display.Window;
import Drawing.Drawer2D;
import Drawing.Null2DDrawer;
import Spatial.Angle;
import Spatial.Point2;
import Utilities.Lerper;

public abstract class Object2D extends GameObject{
    private Point2 position = Point2.zero();
    private Point2 scale = Point2.unit();
    private Angle rotation = Angle.zero();
    
    private Lerper<Point2> visualPosition = new Lerper<Point2>(position, position, 0);
    private Lerper<Point2> visualScale = new Lerper<Point2>(scale, scale, 0);
    private Lerper<Angle> visualRotation = new Lerper<Angle>(rotation, rotation, 0);
    
    protected Drawer2D drawer = new Null2DDrawer();

    public void draw(Window window, Camera2D camera){
        drawer.draw(window, camera);
    }

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

    public Point2 getPosition(){
        return position;
    }

    public Point2 getScale(){
        return scale;
    }

    public Angle getRotation(){
        return rotation;
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
