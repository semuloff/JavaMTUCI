package com.lessons.Lesson_9;

public class Transport {
    public float speed;
    public int weight;
    public String color;
    public byte[] coordinate;

    // class constructor.
    // this constructor will work if we create an instance of the class without parameters.
    public Transport() {
        speed = 0.0f;
        weight = 0;
        color = "White";
        coordinate = new byte[]{0,0,0};

        System.out.println("[Object created!]");
    }

    // this constructor will work if we create an instance of a class with parameters.
    public Transport(float speed, int weight, String color, byte[] coordinate) {
        this.setW(speed, weight, color, coordinate);

        System.out.println("[Object created!]");

        this.printOptions();
    }

    public void printOptions() {
        System.out.println("Speed: " + speed);
        System.out.println("Weight: " + weight);
        System.out.println("Color: " + color);
        System.out.println("Coordinate: " + coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2] + "\n");
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

    public void setW(float speed, int weight, String color, byte[] coordinate) {
        this.speed = speed;
        this.weight = weight;
        this.color = color;
        this.coordinate = coordinate;
    }
}
