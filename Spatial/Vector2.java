package Spatial;

public class Vector2 extends Point2 {

    final double magnitude;
    final Angle direction;
    final static Vector2 zero = new Vector2(0,0);
    final static Vector2 unit = new Vector2(1,1);

    public Vector2(double x, double y){
        super(x,y);
        this.magnitude = Math.sqrt(x*x+y*y);
        // TODO: MAKE THIS WORK vvv
        this.direction = Angle.fromRadians(Math.atan(x/y));
    }

    public Vector2(double magnitude, Angle direction){
        super(magnitude*Math.sin(direction.getRadians()), magnitude*Math.cos(direction.getRadians()));
        this.magnitude = magnitude;
        this.direction = direction;
    }

    public Vector2(Point2 point){
        this(point.x, point.y);
    }

    public double getMagnitude(){
        return magnitude;
    }

    public static double getMagnitude(Point2 point){
        return new Vector2(point).getMagnitude();
    }

    public Angle getDirection(){
        return direction;
    }

    public static Angle getDirection(Point2 point){
        return new Vector2(point).getDirection();
    }

    public Vector2 add(Point2 other){
        return add(this, other);
    }

    public Vector2 subtract(Point2 other){
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

    public static Vector2 add(Point2 first, Point2 second){
        return new Vector2(first.x+second.x, first.y+second.y);
    }

    public static Vector2 subtract(Point2 first, Point2 second){
        return new Vector2(first.x-second.x, first.y-second.y);
    }

    public static Vector2 negative(Point2 first){
        return new Vector2(-first.x, -first.y);
    }

    public static Vector2 multiply(Point2 first, double scalar){
        return new Vector2(first.x*scalar, first.y*scalar);
    }

    public static Vector2 rotate(Vector2 first, Angle angle){
        return new Vector2(first.getMagnitude(), first.getDirection().add(angle));
    }

    public static Vector2 normalize(Vector2 first){
        return new Vector2(1, first.getDirection());
    }

    public static Vector2 zero(){
        return zero;
    }

    public static Vector2 unit(){
        return unit;
    }
}
