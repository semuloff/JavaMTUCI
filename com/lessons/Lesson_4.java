import java.util.Scanner;

public class Lesson_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Login: ");
        String login = scanner.nextLine();

        System.out.print("Enter the password: ");
        String passwod = scanner.nextLine();

        if (login.equals("Admin") && passwod.equals("12345")) {
            System.out.println("Hi, Admin!");
        } else {
            System.out.println("Wrong password or login!");
        }
    }
}
