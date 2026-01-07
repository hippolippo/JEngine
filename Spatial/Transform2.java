package Spatial;

import Drawing.LerpableTransform;

public class Transform2 {
    private Point2 position;
    private Point2 scale;
    private Angle rotation;
    private LerpableTransform affine;
    private LerpableTransform localAffine;
    private int version;
    private int lastParentVersion;
    private Transform2 parent;
    private boolean flag;

    public Transform2(Point2 position, Point2 scale, Angle rotation, Transform2 parent){
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        this.parent = parent;
        updateGlobalMatrix();
    }

    private void updateLocalMatrix(){
        localAffine = new LerpableTransform();
        localAffine.translate(position.getX(), -position.getY());
        localAffine.rotate(rotation.getRadians());
        localAffine.scale(scale.getX(), scale.getY());
    }

    private void updateGlobalMatrix(){
        if(localAffine == null || flag){
            updateLocalMatrix();
            flag = false;
        }
        if(parent != null){
            affine = new LerpableTransform(parent.getAffineTransform());
            affine.concatenate(localAffine);
            lastParentVersion = parent.getVersion();
        }else{
            affine = localAffine;
        }
        version++;
    }

    public void ensureUpdated(){
        if(affine == null || flag || (parent != null && lastParentVersion != parent.getVersion())){
            updateGlobalMatrix();
        }
    }

    public LerpableTransform getAffineTransform(){
        ensureUpdated();
        return affine;
    }

    public int getVersion(){
        ensureUpdated();
        return version;
    }

    public Point2 getWorldPosition() {
        LerpableTransform transform = getAffineTransform();
        return new Point2(transform.getTranslateX(), transform.getTranslateY());
    }

    public Point2 transform(Point2 point) {
        LerpableTransform transform = getAffineTransform();
        double[] coordinates = new double[2];
        transform.transform(new double[]{point.getX(), -point.getY()}, 0, coordinates, 0, 1);
        return new Point2(coordinates[0], -coordinates[1]);
    }

    public Point2 getPosition(){
        return position;
    }

    public Point2 getScale(){
        return scale;
    }

    public Angle getRotation(){
        return rotation;
    }

    public void setPosition(Point2 position){
        this.position = position;
        flag = true;
    }

    public void setScale(Point2 scale){
        this.scale = scale;
        flag = true;
    }

    public void setRotation(Angle rotation){
        this.rotation = rotation;
        flag = true;
    }
}
