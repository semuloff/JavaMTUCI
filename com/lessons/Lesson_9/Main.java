package com.lessons.Lesson_9;

import com.lessons.Lesson_9.src.Car;
import com.lessons.Lesson_9.src.Truck;


public class Main {
    public static void main(String[] args) {
        // basics of OOP.
        // class instance.
        Car Tayota = new Car();

        // methods in use example.
        Tayota.setValues(245f, 1453, "Purpur", true, new byte[] {122, 0, 12});
        Tayota.printValues();
        Tayota.stopObject();


        // class instance with parameters.
        Car BMV = new Car(245f, 1453, "Purpur", true, new byte[] {122, 0, 12});
        BMV.printValues();
        BMV.moveZ(12);
        BMV.description();


        // other class instance.
        Truck Jac = new Truck();
        Jac.printValues();
        Jac.setValues(123.2f, 3210, "White", true, new byte[] {113, 70, 12});
        Jac.printValues();


        Truck Volvo = new Truck(140.4f, 3300, "Blue", true, new byte[] {122, 0, 111});
        Volvo.printValues();
        Volvo.stopObject();
        Volvo.moveY(12);
        Volvo.description();
        Volvo.info();


        // anonymous class.
        Car flyingCar = new Car(245f, 1453, "Purpur", true, new byte[] {122, 0, 12}) {
            @Override
            public void description() {
                System.out.println("This is a flying car.\n");
            }
        };

        flyingCar.description();
    }
}
