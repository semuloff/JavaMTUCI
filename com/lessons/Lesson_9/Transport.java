package com.lessons.Lesson_9;

public class Transport {
    public float speed;
    public int weight;
    public String color;
    public byte[] coordinate;

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

    public void setW(float _speed, int _weight, String _color, byte[] _coordinate) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinate = _coordinate;
    }
}
