package Tasks.task_5.src;

import java.util.ArrayList;
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
        prettyPrint("(5/10)", "sameVowelGroup");
        System.out.println(sameVowelGroup(new String[] {"toe", "ocelot", "maniac"})); // ["toe", "ocelot", "maniac"] -> ["toe", "ocelot"]
        System.out.println(sameVowelGroup(new String[] {"many", "carriage", "emit", "apricot", "animal"})); // ["many", "carriage", "emit", "apricot", "animal"] -> ["many"]
        System.out.println(sameVowelGroup(new String[] {"hoops", "chuff", "bot", "bottom"})); // ["hoops", "chuff", "bot", "bottom"] -> ["hoops", "bot", "bottom"]

        // (6/10)
        prettyPrint("(6/10)", "validateCard");
        System.out.println(validateCard(1234567890123456L)); // validateCard(1234567890123456) -> false
        System.out.println(validateCard(1234567890123452L)); // validateCard(1234567890123452) -> true

        // (7/10)
        prettyPrint("(7/10)", "numToEng");
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
                line += symbol;
                continue;
            }
            symbol = (char) ((int) line.charAt(index - 1) + code[index]);
            line += symbol;
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
                return (Math.abs(yStartCoord - yEndCoord) <= 1 && Math.abs(((int) xStartCoord - (int) xEndCoord)) <= 1);
            case "Queen":
                if (yStartCoord == yEndCoord) {
                    return (Math.abs(((int) xStartCoord - (int) xEndCoord)) >= 0);
                } else if ((Math.abs(yStartCoord - yEndCoord) == Math.abs(((int) xStartCoord - (int) xEndCoord)))) {
                    return true;
                } else {
                    return ((int) xStartCoord == (int) xEndCoord);
                }
            case "Rook":
                if (yStartCoord == yEndCoord) {
                    return true;
//                    return (Math.abs(((int) xStartCoord - (int) xEndCoord)) >= 0);
                } else {
                    return ((int) xStartCoord == (int) xEndCoord);
                }
            case "Knight":
                if ((Math.abs(((int) xStartCoord - (int) xEndCoord)) == 2 && Math.abs(yStartCoord - yEndCoord) == 1)
                        || Math.abs(yStartCoord - yEndCoord) == 2 && Math.abs(((int) xStartCoord - (int) xEndCoord)) == 1) {
                    return true;
                } else {
                    return (((int) xStartCoord == (int) xEndCoord) && yStartCoord == yEndCoord);
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
                        return (yEndCoord - yStartCoord <= 1 && yEndCoord - yStartCoord >= 0);
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
    public static ArrayList<String> sameVowelGroup(String[] words) {
        // Desirable and undesirable vowels.
        String VOWELS = "aeiou";
        String firstWord = words[0];
        String necessaryVowels = "";

        for (int indexSymb = 0, lenghtWord = firstWord.length(); indexSymb < lenghtWord; indexSymb++) {
            String part = String.valueOf(firstWord.charAt(indexSymb));
            if (VOWELS.contains(part)) {
                necessaryVowels += part;
                VOWELS = VOWELS.replace(part, "");
            }
        }

        ArrayList<String> resultWords = new ArrayList<>();

        for (int indexWord = 0, lenghtArray = words.length; indexWord < lenghtArray; indexWord++) {
            String word = words[indexWord];
            String symbol;
            boolean flag = true;

            for (int indexVowels = 0, necessaryVowelsLenght = necessaryVowels.length(); indexVowels < necessaryVowelsLenght; indexVowels++) {
                if (!word.contains(String.valueOf(necessaryVowels.charAt(indexVowels)))) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                for (int indexVowels = 0, vowelsLenght = VOWELS.length(); indexVowels < vowelsLenght; indexVowels++) {
                    if (word.contains(String.valueOf(VOWELS.charAt(indexVowels)))) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    resultWords.add(word);
                }
            }
        }

        return resultWords;
    }

    // (6/10)
    public static boolean validateCard(long number) {
        String tempOne = Long.toString(number / 10);
        String tempTwo = "";

        for (int start = tempOne.length() - 1, end = 0; start >= end; start--) {
            tempTwo += tempOne.charAt(start);
        }

        tempOne = "";

        for (int start = 0, end = tempTwo.length(); start < end; start++) {
            String partStrType = String.valueOf(tempTwo.charAt(start));
            short partShortType = Short.parseShort(partStrType);

            if ((start + 1) % 2 == 1) {
                if (partShortType * 2 / 10 > 0) {
                    tempOne += String.valueOf(partShortType * 2 / 10 + (partShortType * 2) % 10);
                } else {
                    tempOne += String.valueOf(partShortType * 2);
                }
            } else {
                tempOne += partStrType;
            }
        }

        long pleasant = Long.parseLong(tempOne);
        short sum = 0;

        while (pleasant > 0) {
            sum += pleasant % 10;
            pleasant /= 10;
        }

        return (10 - sum % 10 == number % 10);
    }

    // (7/10)
    public static void numToEng(short number) {

    }
}

