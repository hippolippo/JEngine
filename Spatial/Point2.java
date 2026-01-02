package Spatial;

import Utilities.Lerpable;

public class Point2 implements Lerpable<Point2>{

    final double x;
    final double y;
    final static Point2 zero = new Point2(0,0);
    final static Point2 unit = new Point2(1,1);

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public java.awt.Point toScreenPoint(){
        return new java.awt.Point((int)x,(int)y);
    }

    public Point2 add(Point2 other){
        return add(this, other);
    }

    public Point2 subtract(Point2 other){
        return subtract(this, other);
    }

    public Point2 negative(){
        return negative(this);
    }

    public Point2 multiply(double scalar){
        return multiply(this, scalar);
    }

    public Point2 hadamard(Point2 other){
        return hadamard(this, other);
    }

    public static Point2 add(Point2 first, Point2 second){
        return new Point2(first.x+second.x, first.y+second.y);
    }

    public static Point2 subtract(Point2 first, Point2 second){
        return new Point2(first.x-second.x, first.y-second.y);
    }

    public static Point2 negative(Point2 first){
        return new Point2(-first.x, -first.y);
    }

    public static Point2 multiply(Point2 first, double scalar){
        return new Point2(first.x*scalar, first.y*scalar);
    }

    public static Point2 hadamard(Point2 first, Point2 second){
        return new Point2(first.getX()*second.getX(), first.getY()*second.getY());
    }

    public static Point2 zero(){
        return zero;
    }

    public static Point2 unit(){
        return unit;
    }

    @Override
    public Point2 lerp(Point2 other, double amount) {
        return new Point2(Lerpable.lerpDouble(x, other.x, amount), Lerpable.lerpDouble(y, other.y, amount));
    }
}
