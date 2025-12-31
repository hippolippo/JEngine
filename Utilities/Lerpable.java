package Utilities;

public interface Lerpable<T extends Lerpable<T>>{
    T lerp(T other, double amount);

    public static <U extends Lerpable<U>> U lerp(U first, U other, double amount){
        return first.lerp(other, amount);
    }

    public static double lerpDouble(double first, double second, double amount){
        return first*(1-amount)+second*amount;
    }
}
