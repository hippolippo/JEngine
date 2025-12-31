package Utilities;

public class Lerper<T extends Lerpable<T>>{
    T destination;
    T previous;
    long startTime;
    int duration;

    long lastPolled;
    T lastReturned;

    public Lerper(T start, T end, int duration){
        this(start, end, duration, System.currentTimeMillis());
    }

    public Lerper(T start, T end, int duration, long startTime){
        this.previous = start;
        this.destination = end;
        this.duration = duration;
        this.startTime = startTime;
    }

    public T get(long now){
        lastPolled = now;
        if(now >= startTime+duration) lastReturned = destination;
        else lastReturned = Lerpable.lerp(previous, destination, ((double)(now-startTime))/duration);
        return lastReturned;
    }

    public T get(){
        return get(System.currentTimeMillis());
    }

    public void reroute(T end, int duration, long now){
        this.previous = lastReturned;
        this.destination = end;
        this.duration = (int)(now-lastPolled+duration);
        this.startTime = lastPolled;
    }

    public void reroute(T end, int duration){
        reroute(end, duration, System.currentTimeMillis());
    }
}
