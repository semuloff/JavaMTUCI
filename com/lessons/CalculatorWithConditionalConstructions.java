import java.util.Scanner;

public class CalculatorWithConditionalConstructions {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        float firstOperand = scan.nextFloat();
        System.out.println("Enter the second number: ");
        float secondOperand = scan.nextFloat();

        System.out.println("Enter the intent: ");
        String operator = scan.next();

        switch (operator) {
            case "+":
                System.out.println("Rezult: " + (firstOperand + secondOperand));
                break;
            case "-":
                System.out.println("Rezult: " + (firstOperand - secondOperand));
                break;
            case "/":
                System.out.println("Rezult: " + (firstOperand / secondOperand));
                break;
            case "*":
                System.out.println("Rezult: " + (firstOperand * secondOperand));
                break;
            default:
                System.out.println("Error!");
                break;
            }
        }
    }
