package GameObjects;

import Drawing.Texture2DDrawer;
import Drawing.Texture2DDrawer.HorizontalOffset;
import Drawing.Texture2DDrawer.VerticalOffset;
import Spatial.Angle;
import Spatial.Point2;
import Textures.Texture2D;

public class Image extends Object2D{

    public Image(Texture2D texture, Point2 position, Point2 scale, Angle rotation){
        Texture2DDrawer drawer = new Texture2DDrawer(this, texture);
        drawer.setOffset(HorizontalOffset.Center, VerticalOffset.Middle);
        this.drawer = drawer;
        setPosition(position == null ? Point2.zero() : position);
        setScale(scale == null ? new Point2(1,1) : scale);
        setRotation(rotation == null ? Angle.zero() : rotation);
    }

    public Image(String path, Point2 position, Point2 scale, Angle rotation){
        this(new Texture2D(path), position, scale, rotation);
    }

    public void update(int delta) {
        // pass
    }  
}
