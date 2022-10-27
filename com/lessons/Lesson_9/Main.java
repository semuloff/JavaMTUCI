package com.lessons.Lesson_9;

public class Main {
    public static void main(String[] args) {
        // basics of OOP.
        Transport tayota = new Transport();
        tayota.setW(234.34f, 2340, "black", new byte[] {0, 0, 0});

        tayota.printOptions();
        tayota.moveX(20);
        tayota.moveZ(23);
        tayota.printOptions();

        Transport bmv = new Transport();
        bmv.setW(235.3f, 2430, "White", new byte[] {1, 3, 64});

        bmv.printOptions();
    }

}
