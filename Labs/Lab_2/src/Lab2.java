import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[][] data = new double[3][3];
        double value;

        // data input
        for (int iteration = 0; iteration < 3; iteration++) {
            System.out.println("\nEnter the coordinates of the point " +
                    "according to X,Y,Z respectively, separated by a enter for Point #" + (iteration + 1));

            for (int iteration2 = 0; iteration2 < 3; iteration2++) {
                System.out.print(">> ");
                value = scan.nextDouble();
                data[iteration][iteration2] = value;
            }
        }

        // object creation.
        Point3d pointOne = new Point3d(data[0][0], data[0][1], data[0][2]);
        Point3d pointTwo = new Point3d(data[1][0], data[1][1], data[1][2]);
        Point3d pointThree = new Point3d(data[2][0], data[2][1], data[2][2]);

        // sides.
        double AB = pointOne.distanceTo(pointTwo);
        double BC = pointTwo.distanceTo(pointThree);
        double AC = pointOne.distanceTo(pointThree);

        if (pointOne.equals(pointTwo) || pointTwo.equals(pointThree) || pointOne.equals(pointThree))
            System.out.println("\nDots match! ");
        else
            System.out.println("\nResult: " + computeArea(AB, BC, AC));
    }

    // square.
    public static double computeArea(double A, double B, double C) {
        double p = (A + B + C) / 2;
        return Math.sqrt(p * (p - A) * (p - B) * (p - C));
    }
}
