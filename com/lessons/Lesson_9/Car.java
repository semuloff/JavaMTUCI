package com.lessons.Lesson_9;

public class Car extends Transport{
    private boolean worth;
    public Engine engine = new Engine();

    public Car() {
        this.setValues(0f, 0, "None", false, new byte[] {0, 0, 0});
        engine.setValues(true, 13232f);

        System.out.println("[Object created with default parameters!]\n");
    }

    public Car(float speed, int weight, String color, boolean worth, byte[] coordinate) {
        this.setValues(speed, weight, color, worth, coordinate);
        engine.setValues(false, 1323234234f);

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
        System.out.println("[Car moved " + xCoord + " on the 'X' axis.]\n");
    }

    @Override
    public void moveY(int yCoord) {
        this.coordinate[1] += yCoord;
        System.out.println("[Car moved " + yCoord + " on the 'Y' axis.]\n");
    }

    @Override
    public void moveZ(int zCoord) {
        this.coordinate[2] += zCoord;
        System.out.println("[Car moved " + zCoord + " on the 'Z' axis.]\n");
    }

    @Override
    public void stopObject() {
        super.speed = 0;
        System.out.println("[Car stopped]\n");
    }

    @Override
    public void description() {
        System.out.println("This is a car.\n");
    }
}
