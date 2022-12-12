package Tasks.task_5.src;

import java.util.LinkedList;

public class Task_5 {

    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "encrypt & decrypt");
        System.out.println(encrypt("Hello")); // ("Hello") -> [72, 29, 7, 0, 3]
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68})); // [ 72, 33, -73, 84, -12, -3, 13, -13, -68 ]) -> "Hi there!"
        System.out.println(encrypt("Sunshine")); // "Sunshine" -> [83, 34, -7, 5, -11, 1, 5, -9]

        // (2/10)
        prettyPrint("(2/10)", "canMove");
    }
    
    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (1/10)
    public static LinkedList<Integer> encrypt(String line) {
        LinkedList<Integer> parts = new LinkedList<>();

        for (int index = 0, lenght = line.length(); index < lenght; index++) {
            if (index == 0) {
                parts.add((int) line.charAt(index));
                continue;
            }
            parts.add(((int) line.charAt(index)) - ((int) line.charAt(index - 1)));
        }

        return parts;
    }

    public static String decrypt(int[] code) {
        String line = "";
        char symbol;

        for (int index = 0, lenght = code.length; index < lenght; index++) {
            if (index == 0) {
                symbol = (char) code[index];
                line += Character.toString(symbol);
                continue;
            }
            symbol = (char) ((int) line.charAt(index - 1) + code[index]);
            line += Character.toString(symbol);
        }

        return line;
    }

    // (2/10)
}

