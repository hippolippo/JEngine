package GameObjects;

import Display.Layer2D;
import Spatial.Angle;
import Spatial.Point2;

public class RollingSquare extends Image{

    public RollingSquare() {
        super("Assets/donald.png", new Point2(0, -100), new Point2(.25, .25), null);
        setParent(Layer2D.mouse);
        //super(new Vector2(0, 300), 50, Angle.zero(), Color.RED);
    }

    @Override
    public void update(int delta){
        double mouseX = inputManager.mousePosition().getX();
        setRotation(Angle.fromDegrees(mouseX/2), delta);
        setPosition(getPosition());
        //setScale(getScale());
        if(inputManager.keyPressed(65)){
            setScale(new Point2(getScale().getX()*-1, getScale().getY()), 200);
        }
    }
    
}
