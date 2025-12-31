package GameObjects;

import Display.Window;
import Spatial.Vector2;
import Textures.Texture2D;

public class Mouse extends Image{
    public Mouse(Texture2D texture){
        super(texture, null, null, null);
        setOffset(Vector2.zero());
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
