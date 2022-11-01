package com.lessons.Lesson_9;

public class Car extends Transport{
    private boolean worth;

    public Car() {
        this.setValues(0f, 0, "None", false, new byte[] {0, 0, 0});
        System.out.println("[Object created with default parameters!]\n");
    }

    public Car(float speed, int weight, String color, boolean worth, byte[] coordinate) {
        this.setValues(speed, weight, color, worth, coordinate);
        System.out.println("[Object created with parameters!]\n");
    }

    public void setValues(float speed, int weight, String color, boolean worth, byte[] coordinate) {
        super.setValues(speed, weight, color, coordinate);
        this.worth = worth;
        System.out.println("[Values set!]\n");
    }

    @Override
    public void printValues() {
        System.out.println("Parameters: ");
        super.printValues();
        System.out.println("Worth: " + worth + "\n");
    }

    @Override
    public void moveX(int xCoord) {
        this.coordinate[0] += xCoord;
        System.out.println("[Car moved " + xCoord + " on the 'X' axis.]");
    }

    @Override
    public void moveY(int yCoord) {
        this.coordinate[1] += yCoord;
        System.out.println("[Car moved " + yCoord + " on the 'Y' axis.]");
    }

    @Override
    public void moveZ(int zCoord) {
        this.coordinate[2] += zCoord;
        System.out.println("[Car moved " + zCoord + " on the 'Z' axis.]");
    }

    @Override
    public void stopObject() {
        super.speed = 0;
        System.out.println("[Car stopped]\n");
    }
}
