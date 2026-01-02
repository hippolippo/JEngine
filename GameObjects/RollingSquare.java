package GameObjects;

import Display.Window;
import Spatial.Angle;
import Spatial.Point2;

public class RollingSquare extends Image{

    public RollingSquare() {
        super("Assets/donald.png", new Point2(0, 0), new Point2(.25, .25), null);
        //super(new Vector2(0, 300), 50, Angle.zero(), Color.RED);
    }

    @Override
    public void draw(Window window, Camera2D camera){
        super.draw(window, camera);
    } 

    @Override
    public void update(int delta){
        double mouseX = inputManager.mousePosition().getX();
        setPosition(new Point2(mouseX, getPosition().getY()), delta);
        setRotation(Angle.fromDegrees(-mouseX/2+50), delta);
        if(inputManager.keyPressed(65)){
            setScale(new Point2(getScale().getX()*-1, getScale().getY()), 200);
        }
    }
    
}
