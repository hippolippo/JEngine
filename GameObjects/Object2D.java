package GameObjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Display.Window;
import Drawing.Drawer2D;
import Drawing.LerpableTransform;
import Drawing.Null2DDrawer;
import Spatial.Angle;
import Spatial.Point2;
import Utilities.Lerper;
import Spatial.Transform2;

public abstract class Object2D extends GameObject{

    // Transformation
    private Transform2 transform = new Transform2(Point2.zero(), Point2.unit(), Angle.zero(), null);

    public void setPosition(Point2 point, int delta){
        transform.setPosition(point);
    }

    public void setPosition(Point2 point){
        setPosition(point, 0);
    }

    public void setScale(Point2 scale, int delta){
        transform.setScale(scale);
    }

    public void setScale(Point2 scale){
        setScale(scale, 0);
    }

    public void setRotation(Angle rotation, int delta){
        transform.setRotation(rotation);
    }

    public void setRotation(Angle rotation){
        setRotation(rotation, 0);
    }

    public Point2 getPosition(){
        return transform.getPosition();
    }

    public Point2 getScale(){
        return transform.getScale();
    }

    public Angle getRotation(){
        return transform.getRotation();
    }
    
    // Drawing
    private int drawPriority = 0;
    private boolean orderDirtyFlag = true;
    private ArrayList<Object2D> renderOrder = new ArrayList<>();
    private Lerper<LerpableTransform> visualTransform = new Lerper<LerpableTransform>(new LerpableTransform(transform.getAffineTransform()), new LerpableTransform(transform.getAffineTransform()), 0);
    private boolean interpolateMotion = true;
    
    protected Drawer2D drawer = new Null2DDrawer();

    public LerpableTransform getVisualTransform(){
        return visualTransform.get();
    }

    public void flagDrawOrder(){
        orderDirtyFlag = true;
    }

    public void setDrawPriority(int priority){
        drawPriority = priority;
        if(parent != null){
            parent.flagDrawOrder();
        }
    }

    public int getDrawPriority(){
        return drawPriority;
    }

    public void tick(int delta){
        update(delta);
        if(interpolateMotion){
            visualTransform.reroute(transform.getAffineTransform(), delta);
        } else {
            visualTransform.reroute(transform.getAffineTransform(), 0);
        }
    }

    public void draw(Window window, Camera2D camera){

        if(orderDirtyFlag){
            renderOrder.clear();
            renderOrder.addAll(children);
            renderOrder.sort((o1, o2) -> Integer.compare(o2.getDrawPriority(), o1.getDrawPriority()));
            orderDirtyFlag = false;
        }

        boolean drawnSelf = false;
        for(int i = 0; i < renderOrder.size(); i++){
            Object2D obj = renderOrder.get(i);
            if(!drawnSelf && obj.getDrawPriority() > 0){
                drawnSelf = true;
                drawer.draw(window, camera);
            }
            if(!obj.isHidden()){
                obj.draw(window, camera);
            }
        }
        if(!drawnSelf){
            drawer.draw(window, camera);
            drawnSelf = true;
        }
        
    }

    // Ancestry
    private Object2D parent = null;
    private HashSet<Object2D> children = new HashSet<>();

    @Override
    public Object2D getParent(){
        return parent;
    }

    @Override
    public Iterator<GameObject> getChildren(){
        return children.stream().map(obj2 -> (GameObject) obj2).iterator();
    }

    public void setParent(Object2D parent){
        transform = new Transform2(getPosition(), getScale(), getRotation(), parent.transform);
        if(this.parent != null){
            this.parent.removeChild(this);
        }
        if(parent != null){
            parent.addChild(this);
        }
        this.parent = parent;
    }

    private void addChild(Object2D child){
        children.add(child);
        flagDrawOrder();
    }

    private void removeChild(Object2D child){
        children.remove(child);
        flagDrawOrder();
    }
}
