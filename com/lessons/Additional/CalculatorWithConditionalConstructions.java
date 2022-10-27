import java.util.Scanner;

public class CalculatorWithConditionalConstructions {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        float firstOperand = scan.nextFloat();
        System.out.print("Enter the second number: ");
        float secondOperand = scan.nextFloat();

        System.out.println("Enter the intent: ");
        String operator = scan.nextLine();
        operator = scan.nextLine();

        switch (operator) {
            case "+":
                System.out.println("Rezult: " + (firstOperand + secondOperand));
                break;
            case "-":
                System.out.println("Rezult: " + (firstOperand - secondOperand));
                break;
            case "/":
                if (secondOperand == 0) {
                    System.out.println("Error!");
                    break;
                } else {
                    System.out.println("Rezult: " + (firstOperand / secondOperand));
                    break;
                }
            case "*":
                System.out.println("Rezult: " + (firstOperand * secondOperand));
                break;
            default:
                System.out.println("Error!");
                break;
            }
        }
    }
