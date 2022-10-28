package com.lessons.Lesson_9;

public class Main {
    public static void main(String[] args) {
        // basics of OOP.
        // class instance.
        Transport tayota = new Transport();

        // methods in use example.
        tayota.printOptions();

        // class instance with parameters.
        Transport bmv = new Transport(235.3f, 2430, "White", new byte[] {1, 3, 64});
    }
}
