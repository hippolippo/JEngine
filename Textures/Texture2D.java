package Textures;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Image;

public class Texture2D {
    Image image;

    public Texture2D(java.awt.Image image){
        this.image = image;
    }

    public Texture2D(String path){
        this(read(path));
    }

    private static java.awt.Image read(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }

    public int width(){
        return image.getWidth(null);
    }

    public int height(){
        return image.getHeight(null);
    }

    public boolean isNull(){
        return image == null;
    }

    public Image getImage(){
        return image;
    }
}
