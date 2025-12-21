package GameObjects;

import Display.Window;
import Spatial.Vector2;

public interface Object2D extends GameObject{
    public Vector2 position = new Vector2(0,0);
    public void draw(Window window, Camera2D camera);
}
