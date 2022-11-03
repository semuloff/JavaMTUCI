import java.util.Scanner;

public class Lesson_3 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        System.out.println("Enter the byte number: ");
        byte forByte = cin.nextByte();
        System.out.println("You entered from the console: " + forByte + "\n");

        System.out.println("Enter the short number: ");
        short forShort = cin.nextShort();
        System.out.println("You entered from the console: " + forShort + "\n");

        System.out.println("Enter the integer number: ");
        int forInt = cin.nextInt();
        System.out.println("You entered from the console: " + forInt + "\n");

        System.out.println("Enter the long number: ");
        long forLong = cin.nextLong();
        System.out.println("You entered from the console: " + forLong + "\n");

        System.out.println("Enter the float number: ");
        float forFloat = cin.nextFloat();
        System.out.println("You entered from the console: " + forFloat + "\n");

        System.out.println("Enter the double float number: ");
        double forDoubleFloat = cin.nextDouble();
        System.out.println("You entered from the console: " + forDoubleFloat + "\n");

        System.out.println("Enter the string: ");
        String forLine = cin.nextLine();
        System.out.println("You entered from the console: " + forLine + "\n");

        System.out.println("Enter the next string: ");
        String forFirstWord = cin.next();
        System.out.println("First word entered from the console: " + forFirstWord + "\n");

        // Increment and decrement work exactly the same as in C++.
        int number = 0;
        System.out.println(++number);
        System.out.println(number++);
        System.out.println(number);

        // Сompact version of arithmetic works the same way as in C++.
        number += 5;
        System.out.println(number);
        number -= 5;
        System.out.println(number);
        number *= 5;
        System.out.println(number);
        number /= 5;
        System.out.println(number);

    }
}
