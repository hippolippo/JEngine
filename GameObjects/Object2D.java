package GameObjects;

import Display.Window;
import Spatial.Vector2;

public abstract class Object2D extends GameObject{
    public Vector2 position = new Vector2(0,0);
    public abstract void draw(Window window, Camera2D camera);
}
