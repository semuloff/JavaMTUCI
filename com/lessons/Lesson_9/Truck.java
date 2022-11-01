package com.lessons.Lesson_9;

public class Truck extends Transport{
    private boolean workload;

    public Truck() {
        this.setValues(0f, 0, "None", false, new byte[] {0, 0, 0});
        System.out.println("[Object created with default parameters!]\n");
    }

    public Truck(float speed, int weight, String color, boolean workload, byte[] coordinate) {
        this.setValues(speed, weight, color, workload, coordinate);
        System.out.println("[Object created with parameters!]\n");
    }

    public void setValues(float speed, int weight, String color, boolean workload, byte[] coordinate) {
        super.setValues(speed, weight, color, coordinate);
        this.workload = workload;
        System.out.println("[Values set!]\n");
    }

    @Override
    public void printValues() {
        System.out.println("Parameters: ");
        super.printValues();
        System.out.println("Workload: " + workload + "\n");
    }

    @Override
    public void moveX(int xCoord) {
        this.coordinate[0] += xCoord;
        System.out.println("[Truck moved " + xCoord + " on the 'X' axis.]");
    }

    @Override
    public void moveY(int yCoord) {
        this.coordinate[1] += yCoord;
        System.out.println("[Truck moved " + yCoord + " on the 'Y' axis.]");
    }

    @Override
    public void moveZ(int zCoord) {
        this.coordinate[2] += zCoord;
        System.out.println("[Truck moved " + zCoord + " on the 'Z' axis.]");
    }

    @Override
    public void stopObject() {
        super.speed = 0;
        System.out.println("[Truck stopped]\n");
    }
}
