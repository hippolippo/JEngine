package Drawing;

import java.awt.geom.AffineTransform;

import Utilities.Lerpable;

public class LerpableTransform extends AffineTransform implements Lerpable<LerpableTransform>{

    public LerpableTransform(AffineTransform Tx){
        super(Tx);
    }

    public LerpableTransform(){
        super();
    }

    public LerpableTransform(double m00, double m10, double m01, double m11, double m02, double m12){
        super(m00, m10, m01, m11, m02, m12);
    }

    @Override
    public LerpableTransform lerp(LerpableTransform other, double amount) {
        return new LerpableTransform(
            Lerpable.lerpDouble(getScaleX(), other.getScaleX(), amount),
            Lerpable.lerpDouble(getShearY(), other.getShearY(), amount),
            Lerpable.lerpDouble(getShearX(), other.getShearX(), amount),
            Lerpable.lerpDouble(getScaleY(), other.getScaleY(), amount),
            Lerpable.lerpDouble(getTranslateX(), other.getTranslateX(), amount),
            Lerpable.lerpDouble(getTranslateY(), other.getTranslateY(), amount)
        );
    }
    
}
