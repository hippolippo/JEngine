import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Spatial.Angle;
import Spatial.Vector2;

public class Image implements GameObject{

    public enum HorizontalOffset{
        Left, Center, Right
    };

    public enum VerticalOffset{
        Top, Middle, Bottom
    };

    java.awt.Image image;
    Vector2 position;
    Vector2 scale;
    Vector2 offset;
    Angle rotation;
    

    public Image(java.awt.Image image, Vector2 position, Vector2 scale, Angle rotation){
        this.image = image;
        this.offset = new Vector2((double)image.getWidth(null)/2,(double)image.getHeight(null)/2);
        this.position = position == null ? Vector2.zero() : position;
        this.scale = scale == null ? Vector2.zero() : scale;
        this.rotation = rotation == null ? Angle.zero() : rotation;
    }

    public Image(String path, Vector2 position, Vector2 scale, Angle rotation){
        this(read(path), position, scale, rotation);
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
                xoffset = image.getWidth(null);
                break;
            default:
                // Center
                xoffset = (double)image.getWidth(null)/2;
                break;
        }

        double yoffset;
        switch (vertical) {
            case Top:
                yoffset = 0;
                break;
            case Bottom:
                yoffset = image.getHeight(null);
                break;
            default:
                // Middle
                yoffset = (double)image.getHeight(null)/2;
                break;
        }

        setOffset(new Vector2(xoffset, yoffset));
    }

    private static java.awt.Image read(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void draw(Window window, Graphics2D g) {
        if(image == null) return;

        AffineTransform transform = new AffineTransform();
        transform.translate(position.getX()-offset.getX()*scale.getX(), position.getY()-offset.getY()*scale.getY());
        transform.scale(scale.getX(), scale.getY());
        transform.rotate(-rotation.getRadians(), offset.getX(), offset.getY());
        g.drawImage(image, transform, null);
    }
    
}
