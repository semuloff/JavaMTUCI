public class Point3d extends Point2d {
    // Z coordinate.
    private double zCoord;

    // initialization constructor.
    public Point3d(double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }

    // default constructor.
    public Point3d() {
        super();
        zCoord = 0;
    }

    // return Z coordinate.
    public double getZ() {
        return zCoord;
    }

    // set Z coordinate's value
    public void setZ (double val) {
        zCoord = val;
    }

    // comparing the values of two Point3d objects.
    public boolean equals(Point3d point) {
        return getX() == point.getX() && getY() == point.getY() && getZ() == point.getZ();
    }

    // distance between two points.
    public double distanceTo(Point3d point) {
        return Math.round(Math.sqrt(
                Math.pow(point.getX() - getX(), 2)
                + Math.pow(point.getY() - getY(), 2)
                + Math.pow(point.getZ() - getZ(), 2)) * 100.0) / 100.0;
    }
}
