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

    @Override
    public LerpableTransform lerp(LerpableTransform other, double amount) {
        return other;
    }
    
}
