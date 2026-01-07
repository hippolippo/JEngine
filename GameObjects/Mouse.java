package GameObjects;

import Display.Window;
import Drawing.Texture2DDrawer;
import Spatial.Angle;
import Spatial.Point2;
import Spatial.Vector2;
import Textures.Texture2D;

public class Mouse extends Image{
    public Mouse(Texture2D texture){
        super(texture, null, null, null);
        ((Texture2DDrawer)drawer).setOffset(Vector2.zero());
    }

    @Override
    public void update(int delta){
        double mouseY = inputManager.mousePosition().getY();
        setRotation(Angle.fromDegrees(mouseY/2), delta);
        setPosition(inputManager.mousePosition());
        if(inputManager.keyPressed(65)){
            setScale(new Point2(getScale().getX()*-1, getScale().getY()), 200);
        }
    }

    @Override
    public void draw(Window window, Camera2D camera){
        //Ignore Camera, draw to exact cursor position
        if(inputManager.liveMouseInWindow()){
            setPosition(inputManager.liveMousePosition());
            super.draw(window, Camera2D.basic());
        }
    }
}
