package com.lessons.Lesson_9;

public class Truck extends Transport{
    private boolean workload;

    public Truck() {
        super();
        workload = false;
        System.out.println("[Object created with default parameters!]\n");
    }

    public Truck(float speed, int weight, String color, boolean workload, byte[] coordinate) {
        super(speed, weight, color, coordinate);
        this.workload = workload;
        System.out.println("[Object created with parameters!]\n");
    }

    public void setValues(float speed, int weight, String color, boolean workload, byte[] coordinate) {
        super.setValues(speed, weight, color, coordinate);
        this.workload = workload;
        System.out.println("Values set!\n");
    }

    public void printValues() {
        System.out.println("Parameters: ");
        super.printValues();
        System.out.println("Workload: " + workload + "\n");
    }
}
