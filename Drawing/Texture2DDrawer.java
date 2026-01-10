package Drawing;

import java.awt.geom.AffineTransform;

import GameObjects.Camera2D;
import GameObjects.Object2D;
import Spatial.Point2;
import Textures.Texture2D;

public class Texture2DDrawer implements Drawer2D{
    Object2D gameObject;
    Texture2D texture;
    Point2 offset;

    public Texture2DDrawer(Object2D gameObject, Texture2D texture){
        this.gameObject = gameObject;
        this.texture = texture;
        setOffset(Point2.zero());
    }

    public void setOffset(Point2 offset){
        this.offset = offset;
    }

    public void setOffset(HorizontalOffset horizontal, VerticalOffset vertical){
        double xoffset;
        switch (horizontal) {
            case Left:
                xoffset = 0;
                break;
            case Right:
                xoffset = texture.width();
                break;
            default:
                // Center
                xoffset = ((double)texture.width())/2;
                break;
        }

        double yoffset;
        switch (vertical) {
            case Top:
                yoffset = 0;
                break;
            case Bottom:
                yoffset = texture.height();
                break;
            default:
                // Middle
                yoffset = ((double)texture.height())/2;
                break;
        }

        setOffset(new Point2(xoffset, yoffset));
    }

    public enum HorizontalOffset{
        Left, Center, Right
    };

    public enum VerticalOffset{
        Top, Middle, Bottom
    };

    public void draw(DrawSurface surface, Camera2D camera) {
        
        if (texture.isNull()) return;
        
        AffineTransform transform = camera.getAffineTransformForObject(gameObject, surface);
        
        transform.translate(-offset.getX(), -offset.getY());
        surface.drawImage2D(texture, transform);
        
    }
}
