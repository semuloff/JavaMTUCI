import java.util.Scanner;


public class Lesson_5 {
    public static void main(String[] args) {
        // simple program with using "break" and "continue" operator's and "for" cycle.
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int number = scanner.nextInt();

        for (int i = 0; i <= number; i++) {
            if (i > 100) {
                break;
            }

            if (i % 2 == 0) {
                continue;
            }

            System.out.println("Number: " + i);
        }

        // "While" cycle.
        int n = 0;

        while (n < 12) {
            System.out.println("Cycle 'while': number - " + n);
            n++;
        }

        // "do-while" cycle.
        n = 0;

        do {
            System.out.println("Cycle 'do-while': number - " + n);
            n = scanner.nextInt();
        } while (n != 0);
    }
}
