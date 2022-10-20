public class Point2d {
    // X coordinate.
    private double xCoord;

    // Y coordinate.
    private double yCoord;

    // initialization constructor.
    public Point2d (double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    // default constructor.
    public Point2d () {
        xCoord = 0;
        yCoord = 0;
    }

    // return X coordinate.
    public double getX() {
        return xCoord;
    }

    // return Y coordinate.
    public double getY() {
        return yCoord;
    }

    // set X coordinate's value
    public void setX (double val) {
        xCoord = val;
    }

    // set Y coordinate's value
    public void setY (double val) {
        yCoord = val;
    }
}
