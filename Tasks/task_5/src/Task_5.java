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
        System.out.println(canMove("Rook", "A8", "H8")); // ("Rook", "A8", "H8") -> true
        System.out.println(canMove("Bishop", "A7", "G1")); // ("Bishop", "A7", "G1") -> true
        System.out.println(canMove("Queen", "C4", "D6")); // ("Queen", "C4", "D6") -> false

        // (3/10)
        prettyPrint("(3/10)", "canComplete");
        System.out.println(canComplete("butl", "beautiful")); // canComplete("butl", "beautiful") -> true
        System.out.println(canComplete("butlz", "beautiful")); // canComplete("butlz", "beautiful") -> false
        System.out.println(canComplete("tulb", "beautiful")); // canComplete("tulb", "beautiful") -> false
        System.out.println(canComplete("bbutl", "beautiful")); // canComplete("bbutl", "beautiful") -> false

        // (4/10)
        prettyPrint("(4/10)", "sumDigProd");
        System.out.println(sumDigProd(16, 28)); // sumDigProd(16, 28) -> 6
        System.out.println(sumDigProd(0)); // sumDigProd(0) -> 0
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6)); // sumDigProd(1, 2, 3, 4, 5, 6) -> 2

        // (5/10)
        prettyPrint("(4/10)", "sameVowelGroup");
        System.out.println();
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
    public static boolean canMove(String figure, String start, String end) {
        // assert: XY.
        char xStartCoord = start.charAt(0);
        int yStartCoord = Integer.parseInt(Character.toString(start.charAt(1)));

        char xEndCoord = end.charAt(0);
        int yEndCoord = Integer.parseInt(Character.toString(end.charAt(1)));

        // assert: xCoord (A-H), yCoord (1-8).
        switch (figure) {
            case "King":
                return (Math.abs(yStartCoord - yEndCoord) <= 1 && Math.abs(((int) xStartCoord - (int) xEndCoord)) <= 1) ?
                        true : false;
            case "Queen":
                if (yStartCoord == yEndCoord) {
                    return (Math.abs(((int) xStartCoord - (int) xEndCoord)) >= 0) ? true : false;
                } else if ((Math.abs(yStartCoord - yEndCoord) == Math.abs(((int) xStartCoord - (int) xEndCoord)))) {
                    return true;
                } else {
                    return ((int) xStartCoord == (int) xEndCoord) ? true : false;
                }
            case "Rook":
                if (yStartCoord == yEndCoord) {
                    return true;
//                    return (Math.abs(((int) xStartCoord - (int) xEndCoord)) >= 0) ? true : false;
                } else {
                    return ((int) xStartCoord == (int) xEndCoord) ? true : false;
                }
            case "Knight":
                if ((Math.abs(((int) xStartCoord - (int) xEndCoord)) == 2 && Math.abs(yStartCoord - yEndCoord) == 1)
                        || Math.abs(yStartCoord - yEndCoord) == 2 && Math.abs(((int) xStartCoord - (int) xEndCoord)) == 1) {
                    return true;
                } else {
                    return (((int) xStartCoord == (int) xEndCoord) && yStartCoord == yEndCoord) ? true : false;
                }
            case "Bishop":
                return (Math.abs(yStartCoord - yEndCoord) == Math.abs(((int) xStartCoord - (int) xEndCoord))) ? true : false;
            case "Pawn":
                if ((int) xStartCoord == (int) xEndCoord) {
                    if (yStartCoord == 2) {
                        if (yEndCoord - yStartCoord <= 2 && yEndCoord - yStartCoord >= 0) {
                            return true;
                        }
                    } else {
                        return (yEndCoord - yStartCoord <= 1 && yEndCoord - yStartCoord >= 0) ? true : false;
                    }
                }
        }
        return false;
    }

    // (3/10)
    public static boolean canComplete(String line, String completedLine) {
        char[] symbols = line.toCharArray();
        int interim = 0;

        for (int index = 0, arrayLenght = symbols.length; index < arrayLenght; index++) {
            if (completedLine.indexOf(String.valueOf(symbols[index]), interim) != -1) {
                interim = completedLine.indexOf(String.valueOf(symbols[index]), interim) + 1;
            } else {
                return false;
            }
        }

        return true;
    }

    // (4/10)
    public static int sumDigProd(int ... numbers) {
        int result = 0;

        for (int indexArray = 0, lenghtArray = numbers.length; indexArray < lenghtArray; indexArray++) {
            result += numbers[indexArray];
        }

        while (result > 9) {
            int numbProduct = 1;

            while (result != 0) {
                numbProduct *= result % 10;
                result /= 10;
            }

            result = numbProduct;
        }

//        while (result / 10 != 0) {
//            String numbToString = String.valueOf(result);
//            int numbProduct = 1;
//
//            for (int indexSymb = 0, lenghtLine = numbToString.length(); indexSymb < lenghtLine; indexSymb++) {
//                numbProduct *= Integer.parseInt(Character.toString(numbToString.charAt(indexSymb)));
//            }
//
//            result = numbProduct;
//        }

        return result;
    }

    // (5/10)
    public static void sameVowelGroup() {
    }
}

