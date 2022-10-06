package com.lessons;

import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) {
        // simple calculator without conditions
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        float firstNumber = scanner.nextFloat();
        System.out.println("Enter the second number: ");
        float secondNumber = scanner.nextFloat();

        float addition = firstNumber + secondNumber;
        float subtraction = firstNumber - secondNumber;
        float division = firstNumber / secondNumber;
        float multiplication = firstNumber * secondNumber;

        System.out.println("Addition: " + addition);
        System.out.println("Subtraction: " + subtraction);
        System.out.println("Division: " + division);
        System.out.println("Multiplication: " + multiplication);
    }
}
