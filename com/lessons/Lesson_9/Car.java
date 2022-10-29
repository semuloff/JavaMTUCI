package com.lessons.Lesson_9;

public class Car extends Transport{
    private boolean worth;

    public Car() {
        super();
        worth = false;
        System.out.println("[Object created with default parameters!]\n");
    }

    public Car(float speed, int weight, String color, boolean worth, byte[] coordinate) {
        super(speed, weight, color, coordinate);
        this.worth = worth;
        System.out.println("[Object created with parameters!]\n");
    }

    public void setValues(float speed, int weight, String color, boolean worth, byte[] coordinate) {
        super.setValues(speed, weight, color, coordinate);
        this.worth = worth;
        System.out.println("Values set!\n");
    }

    public void printValues() {
        System.out.println("Parameters: ");
        super.printValues();
        System.out.println("Worth: " + worth + "\n");
    }



}
