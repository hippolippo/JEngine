package GameObjects;
import java.awt.geom.AffineTransform;

import Display.Window;
import Spatial.Angle;
import Spatial.Vector2;
import Textures.Texture2D;

public class Image implements Object2D{

    public enum HorizontalOffset{
        Left, Center, Right
    };

    public enum VerticalOffset{
        Top, Middle, Bottom
    };

    Texture2D texture;
    Vector2 position;
    Vector2 scale;
    Vector2 offset;
    Angle rotation;
    boolean hidden = false;
    

    public Image(Texture2D texture, Vector2 position, Vector2 scale, Angle rotation){
        this.texture = texture;
        this.offset = new Vector2(((double)texture.width())/2,((double)texture.height())/2);
        this.position = position == null ? Vector2.zero() : position;
        this.scale = scale == null ? new Vector2(1,1) : scale;
        this.rotation = rotation == null ? Angle.zero() : rotation;
    }

    public Image(String path, Vector2 position, Vector2 scale, Angle rotation){
        this(new Texture2D(path), position, scale, rotation);
    }

    public void setOffset(Vector2 offset){
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

        setOffset(new Vector2(xoffset, yoffset));
    }

    @Override
    public void draw(Window window, Camera2D camera) {
        if(texture.isNull()) return;

        AffineTransform transform = new AffineTransform();
        transform.translate(position.getX()-offset.getX()*scale.getX(), position.getY()-offset.getY()*scale.getY());
        transform.scale(scale.getX(), scale.getY());
        transform.rotate(-rotation.getRadians(), offset.getX(), offset.getY());
        window.drawImage2D(texture, transform);
    }

    @Override
    public void setHidden(boolean enable) {
        hidden = enable;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }
    
}
