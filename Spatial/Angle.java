package Spatial;

public final class Angle {
    private final double measurement;
    private final static Angle zero = new Angle(0);

    private Angle(double measurement){
        measurement %= 1;
        if(measurement < 0) measurement+=1;
        this.measurement = measurement;
    }
    
    public double getRadians(){
        return Math.TAU*measurement;
    }

    public double getDegrees(){
        return 2*180*measurement;
    }

    public double getPercent(){
        return measurement;
    }

    public Angle rotateDegrees(double degrees){
        return new Angle(measurement + degrees/360);
    }

    public Angle rotateRadians(double radians){
        return new Angle(measurement + radians/Math.TAU);
    }

    public Angle rotatePercent(double percent){
        return new Angle(measurement+percent);
    }

    public Angle add(Angle other){
        return add(this, other);
    }

    public static Angle zero(){
        return zero;
    }

    public static Angle fromDegrees(double degrees){
        return zero.rotateDegrees(degrees);
    }

    public static Angle fromRadians(double radians){
        return zero.rotateRadians(radians);
    }

    public static Angle fromPercent(double percent){
        return zero.rotatePercent(percent);
    }

    public static Angle add(Angle first, Angle second){
        return new Angle(first.measurement+second.measurement);
    }
}
