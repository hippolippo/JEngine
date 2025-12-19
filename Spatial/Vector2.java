package Spatial;

import java.awt.Point;

public final class Vector2 {
    final double x;
    final double y;
    final double magnitude;
    final Angle direction;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
        this.magnitude = Math.sqrt(x*x+y*y);
        // TODO: MAKE THIS WORK vvv
        this.direction = Angle.fromRadians(Math.atan(x/y));
    }

    public Vector2(double magnitude, Angle direction){
        this.x = magnitude*Math.sin(direction.getRadians());
        this.y = magnitude*Math.cos(direction.getRadians());
        this.magnitude = magnitude;
        this.direction = direction;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public Angle getDirection(){
        return direction;
    }

    public Point toScreenPoint(){
        return new Point((int)x,(int)y);
    }

    public Vector2 add(Vector2 other){
        return add(this, other);
    }

    public Vector2 subtract(Vector2 other){
        return subtract(this, other);
    }

    public Vector2 negative(){
        return negative(this);
    }

    public Vector2 multiply(double scalar){
        return multiply(this, scalar);
    }

    public Vector2 rotate(Angle angle){
        return rotate(this, angle);
    }

    public static Vector2 add(Vector2 first, Vector2 second){
        return new Vector2(first.x+second.x, first.y+second.y);
    }

    public static Vector2 subtract(Vector2 first, Vector2 second){
        return new Vector2(first.x-second.x, first.y-second.y);
    }

    public static Vector2 negative(Vector2 first){
        return new Vector2(-first.x, -first.y);
    }

    public static Vector2 multiply(Vector2 first, double scalar){
        return new Vector2(first.x*scalar, first.y*scalar);
    }

    public static Vector2 rotate(Vector2 first, Angle angle){
        return new Vector2(first.getMagnitude(), first.getDirection().add(angle));
    }

    public static Vector2 normalize(Vector2 first){
        return new Vector2(1, first.getDirection());
    }
}
