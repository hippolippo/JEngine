package Utilities;

/**
 * Manages the change in {@link Lerpable} objects over time including on the fly rerouting
 * 
 * @author Joel Haftel
 */
public class Lerper<T extends Lerpable<T>>{
    private T destination;
    private T previous;
    private long startTime;
    private int duration;

    private long lastPolled;
    private T lastReturned;

    /**
     * Creates a Lerper that performs a linear interpolation starting at the current time between two objects
     * @param start The source object to interpolate from
     * @param end The destination object to interpolate to
     * @param duration The length of the interpolation in milliseconds >=0
     */
    public Lerper(T start, T end, int duration){
        this(start, end, duration, System.currentTimeMillis());
    }

    /**
     * Creates a Lerper that performs a linear interpolation starting at a specified time between two objects.
     * If the specified time is before the current time, the interpolation will return positions starting partially through the interpolation.
     * If the specified time is after the current time, the interpolation will return the start position until the startTime is reached.
     * If using a constructor with a specified startTime and duration, and only querying or rerouting with a specified current time, any time unit can be used.
     * @param start The source object to interpolate from
     * @param end The destination object to interpolate to
     * @param duration The length of the interpolation in milliseconds >=0
     * @param startTime The start time of the interpolation in milliseconds as provided by <code>System.currentTimeMillis()</code>
     */
    public Lerper(T start, T end, int duration, long startTime){
        this.previous = start;
        this.destination = end;
        this.duration = duration;
        this.startTime = startTime;
    }

    /**
     * Provides the lerped value at a specified point in time.
     * If using a constructor with a specified startTime and duration, and only querying or rerouting with a specified current time, any time unit can be used.
     * @param now The time to find the lerped value at in milliseconds as provided by <code>System.currentTimeMillis()</code>
     * @return The lerped value for the specified point in time
     */
    public T get(long now){
        lastPolled = now;
        if(now >= startTime+duration) lastReturned = destination;
        else if(now < startTime) lastReturned = previous;
        else lastReturned = Lerpable.lerp(previous, destination, ((double)(now-startTime))/duration);
        return lastReturned;
    }

    /**
     * Provides the lerped value for the current time.
     * @return The lerped value for the current time
     */
    public T get(){
        return get(System.currentTimeMillis());
    }

    /**
     * Changes the lerp to route from the last queried position to a new destination with a new duration starting from a specified time.
     * This effectively cancels the existing lerp if it hasn't finished at the last queried state and lerps to the new state from there.
     * If using a constructor with a specified startTime and duration, and only querying or rerouting with a specified current time, any time unit can be used.
     * @param end New destination object to interpolate to
     * @param duration The amount of time in milliseconds (counting from <code>now</code>) for the lerp to take
     * @param now The current time in milliseconds as returned by <code>System.currentTimeMillis()</code>
     */
    public void reroute(T end, int duration, long now){
        this.previous = lastReturned;
        this.destination = end;
        this.duration = (int)(now-lastPolled+duration);
        this.startTime = lastPolled;
    }

    /**
     * Changes the lerp to route from the last queried position to a new destination with a new duration.
     * This effectively cancel the existing lerp if it hasn't finished at the last queried state and lerps to the new state from there.
     * @param end New destination object to interpolate to
     * @param duration THe amount of time in milliseconds (counting from now) for the new lerp to take
     */
    public void reroute(T end, int duration){
        reroute(end, duration, System.currentTimeMillis());
    }
}
