package com.lessons.Lesson_9;

public class Transport {
    private float speed;
    private int weight;
    private String color;
    private byte[] coordinate;

    // class constructors.
    // this constructor will work if we create an instance of the class without parameters.
    protected Transport() {
        this.setValues(0.0f, 0, "White", new byte[]{0,0,0});
    }

    // this constructor will work if we create an instance of a class with parameters.
    protected Transport(float speed, int weight, String color, byte[] coordinate) {
        this.setValues(speed, weight, color, coordinate);
    }

    protected void printValues() {
        System.out.println("Speed: " + speed);
        System.out.println("Weight: " + weight);
        System.out.println("Color: " + color);
        System.out.print("Coordinate: " + coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2] + "\n");
    }

    public void moveX(int xCoord) {
        coordinate[0] += xCoord;
    }

    public void moveY(int yCoord) {
        coordinate[1] += yCoord;
    }

    public void moveZ(int zCoord) {
        coordinate[2] += zCoord;
    }

    protected void setValues(float speed, int weight, String color, byte[] coordinate) {
        this.speed = speed;
        this.weight = weight;
        this.color = color;
        this.coordinate = coordinate;
    }
}
