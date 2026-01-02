package Utilities;

/**
 * Describes an object that can be linearly interpolated between two values
 * 
 * @author Joel Haftel
 */
public interface Lerpable<T extends Lerpable<T>>{

    /**
     * Performs a linear interpolation between this and another object of the same type
     * @param other The destination object to interpolate to
     * @param amount The linear interpolation progress from 0-1
     * @return A new object representing the interpolation
     */
    T lerp(T other, double amount);

    /**
     * Performs a linear interpolation between two Lerpable objects of the same type
     * @param <U> The type of the objects being interpolated
     * @param first The source object to interpolate from
     * @param other The destination object to interpolate to
     * @param amount The linear interpolation progress from 0-1
     * @return A new object representing the interpolation
     */
    public static <U extends Lerpable<U>> U lerp(U first, U other, double amount){
        return first.lerp(other, amount);
    }

    /**
     * Performs a linear interpolation between two doubles
     * @param first The source double to interpolate from
     * @param second The destination double to interpolate to
     * @param amount The linear interpolation progress from 0-1
     * @return A double between <code>first</code> and <code>second</code> representing the interpolation
     */
    public static double lerpDouble(double first, double second, double amount){
        return first*(1-amount)+second*amount;
    }
}
