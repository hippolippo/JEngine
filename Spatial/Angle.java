package Spatial;

import Utilities.Lerpable;

/**
 * A class representing an angle
 * 
 * @author Joel Haftel
 */
public final class Angle implements Lerpable<Angle> {
    private final double measurement;
    private final static Angle zero = new Angle(0);

    private Angle(double measurement){
        this.measurement = measurement;
    }
    
    /**
     * Get the measurement of the angle in radians.
     * @return The measurement of the angle in radians.
     */
    public double getRadians(){
        return Math.TAU*measurement;
    }

    /**
     * Get the measurement of the angle in degrees.
     * @return The measurement of the angle in degrees.
     */
    public double getDegrees(){
        return 2*180*measurement;
    }

    /**
     * Get the measurement of the angle as a percent.
     * @return The measurement of the angle as a percent of rotations where 0 is 0 degrees and 1 is a full rotation or 360 degrees.
     */
    public double getPercent(){
        return measurement;
    }

    /**
     * Provides a new angle representing the current angle rotated by a specific number of degrees.
     * @param degrees Degrees to rotate by.
     * @return New angle representing rotated angle.
     */
    public Angle rotateDegrees(double degrees){
        return new Angle(measurement + degrees/360);
    }

    /**
     * Provides a new angle representing the current angle rotated by a specific number of radians.
     * @param radians Radians to rotate by.
     * @return New angle representing rotated angle.
     */
    public Angle rotateRadians(double radians){
        return new Angle(measurement + radians/Math.TAU);
    }

    /**
     * Provides a new angle representing the current angle rotation by a specific percent.
     * @param percent Rotation amount as a percent (0 is 0 Degrees, 0.5 is 180 Degrees, etc.)
     * @return New angle representing rotated angle.
     */
    public Angle rotatePercent(double percent){
        return new Angle(measurement+percent);
    }

    /**
     * Provides a new angle representing the current angle added with another.
     * @param other The angle to add with this angle.
     * @return New angle representing sum of both angles.
     */
    public Angle add(Angle other){
        return add(this, other);
    }
    
    /**
     * Provides a new angle representing the current angle normalized to be between 0-360 degrees.
     * An angle with value -30 degrees would become 330 degrees. An angle with value 400 degrees would become 40 degrees.
     * @return Normalized angle object
     */
    public Angle normalize(){
        double normalized = measurement%1;
        if(normalized<0) normalized++;
        return new Angle(normalized);
    }

    /**
     * Provides an angle with zero rotation.
     * @return Angle with zero rotation.
     */
    public static Angle zero(){
        return zero;
    }

    /**
     * Provides a new angle object given a degree measurement.
     * @param degrees The degree measurement of the angle.
     * @return A new angle with specified degree measurement.
     */
    public static Angle fromDegrees(double degrees){
        return zero.rotateDegrees(degrees);
    }

    /**
     * Provides a new angle object given a radian measurement.
     * @param radians The radian measurement of the angle.
     * @return A new angle with specified radian measurement.
     */
    public static Angle fromRadians(double radians){
        return zero.rotateRadians(radians);
    }

    /**
     * Provides a new angle object given a percent rotation.
     * @param percent The percent rotation of the angle (0 is 0 Degrees, 0.5 is 180 Degrees, etc.)
     * @return A new angle with specified degree measurement.
     */
    public static Angle fromPercent(double percent){
        return zero.rotatePercent(percent);
    }

    /**
     * Provides a new angle representing two angles added together.
     * @param first Angle to be added.
     * @param second Angle to be added.
     * @return New angle representing the angles measurements added.
     */
    public static Angle add(Angle first, Angle second){
        return new Angle(first.measurement+second.measurement);
    }

    @Override
    public Angle lerp(Angle other, double amount) {
        return new Angle(Lerpable.lerpDouble(measurement, other.measurement, amount));
    }
}
