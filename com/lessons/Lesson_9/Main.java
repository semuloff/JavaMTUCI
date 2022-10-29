package com.lessons.Lesson_9;

public class Main {
    public static void main(String[] args) {
        // basics of OOP.
        // class instance.
        Car Tayota = new Car();

        // methods in use example.
        Tayota.setValues(245f, 1453, "Purpur", true, new byte[] {122, 0, 12});
        Tayota.printValues();

        // class instance with parameters.
        Car BMV = new Car(245f, 1453, "Purpur", true, new byte[] {122, 0, 12});
        BMV.printValues();

        // other class instance.
        Truck Jac = new Truck();
        Jac.printValues();
        Jac.setValues(123.2f, 3210, "White", true, new byte[] {113, 70, 12});
        Jac.printValues();

        Truck Volvo = new Truck(140.4f, 3300, "Blue", true, new byte[] {122, 0, 111});
        Volvo.printValues();
    }
}
