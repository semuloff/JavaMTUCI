package Tasks.task_5.src;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        prettyPrint("(7/10)", "numToEng & numToRus");
        System.out.println(numToEng(0)); // numToEng(0) -> "zero"
        System.out.println(numToEng(18)); // numToEng(18) -> "eighteen"
        System.out.println(numToEng(126)); // numToEng(126) -> "one hundred twenty six"
        System.out.println(numToEng(909)); // numToEng(909) -> "nine hundred nine"

        // (8/10)
        prettyPrint("(8/10)", "getSha256Hash");
        try {
            System.out.println(getSha256Hash("password123"));
            System.out.println(getSha256Hash("Fluffy@home"));
            System.out.println(getSha256Hash("Hey dude!"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // (9/10)
        prettyPrint("(9/10)", "correctTitle");
        System.out.println(correctTitle("jOn SnoW, kINg IN th-E no-Rth.")); // -> "Jon Snow, King in the North."
        System.out.println(correctTitle("sansa stark, lady of winterfell.")); // -> "Sansa Stark, Lady of Winterfell."
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN.")); // -> "Tyrion Lannister, Hand of the Queen."

        // (10/10)
        prettyPrint("(10/10)", "hexLattice");
        System.out.println(hexLattice(1)); // -> image
        System.out.println(hexLattice(7)); // -> image
        System.out.println(hexLattice(19)); // -> image
        System.out.println(hexLattice(21)); // -> Invalid
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
    public static String numToEng(int number) {
            if (number == 0) {
                return "zero";
            }

            StringBuilder build = new StringBuilder();

            if (number / 100 > 0) {
                switch (number / 100) {
                    case 1:
                        build.append("one hundred");
                        break;
                    case 2:
                        build.append("two hundred");
                        break;
                    case 3:
                        build.append("three hundred");
                        break;
                    case 4:
                        build.append("four hundred");
                        break;
                    case 5:
                        build.append("fife hundred");
                        break;
                    case 6:
                        build.append("six hundred");
                        break;
                    case 7:
                        build.append("seven hundred");
                        break;
                    case 8:
                        build.append("eight hundred");
                        break;
                    case 9:
                        build.append("nine hundred");
                        break;
                }

                number %= 100;

                if (number > 0) {
                    build.append(" ");
                }
            }

            if (number / 10 > 0 && number % 10 >= 0) {
                if (number / 10 == 1) {
                    switch (number % 10) {
                        case 0:
                            build.append("ten");
                            break;
                        case 1:
                            build.append("eleven");
                            break;
                        case 2:
                            build.append("twelve");
                            break;
                        case 3:
                            build.append("thirteen");
                            break;
                        case 4:
                            build.append("fourteen");
                            break;
                        case 5:
                            build.append("fifteen");
                            break;
                        case 6:
                            build.append("sixteen");
                            break;
                        case 7:
                            build.append("seventeen");
                            break;
                        case 8:
                            build.append("eighteen");
                            break;
                        case 9:
                            build.append("nineteen");
                            break;
                    }

                    number = 0;
                } else {
                    switch (number / 10) {
                        case 2:
                            build.append("twenty");
                            break;
                        case 3:
                            build.append("thirty");
                            break;
                        case 4:
                            build.append("fourty");
                            break;
                        case 5:
                            build.append("fifty");
                            break;
                        case 6:
                            build.append("sixty");
                            break;
                        case 7:
                            build.append("seventy");
                            break;
                        case 8:
                            build.append("eighty");
                            break;
                        case 9:
                            build.append("ninety");
                            break;
                    }

                    build.append(" ");
                    number %= 10;
                }
            }

            switch (number) {
                case 1:
                    build.append("one");
                    break;
                case 2:
                    build.append("two");
                    break;
                case 3:
                    build.append("three");
                    break;
                case 4:
                    build.append("four");
                    break;
                case 5:
                    build.append("fife");
                    break;
                case 6:
                    build.append("six");
                    break;
                case 7:
                    build.append("seven");
                    break;
                case 8:
                    build.append("eight");
                    break;
                case 9:
                    build.append("nine");
                    break;
            }

            return build.toString();
    }

    public static String numToRus(int number) {
        if (number == 0) {
            return "ноль";
        }

        StringBuilder build = new StringBuilder();

        if (number / 100 > 0) {
            switch (number / 100) {
                case 1:
                    build.append("сто");
                    break;
                case 2:
                    build.append("двести");
                    break;
                case 3:
                    build.append("триста");
                    break;
                case 4:
                    build.append("четыреста");
                    break;
                case 5:
                    build.append("пятьсот");
                    break;
                case 6:
                    build.append("шестьсот");
                    break;
                case 7:
                    build.append("семьсот");
                    break;
                case 8:
                    build.append("восемьсот");
                    break;
                case 9:
                    build.append("девятьсот");
                    break;
            }

            number %= 100;

            if (number > 0) {
                build.append(" ");
            }
        }

        if (number / 10 > 0 && number % 10 >= 0) {
            if (number / 10 == 1) {
                switch (number % 10) {
                    case 0:
                        build.append("десять");
                        break;
                    case 1:
                        build.append("одиннадцать");
                        break;
                    case 2:
                        build.append("двенадцать");
                        break;
                    case 3:
                        build.append("тринадцать");
                        break;
                    case 4:
                        build.append("четырнадцать");
                        break;
                    case 5:
                        build.append("пятнадцать");
                        break;
                    case 6:
                        build.append("шестнадцать");
                        break;
                    case 7:
                        build.append("семнадцать");
                        break;
                    case 8:
                        build.append("восемнадцать");
                        break;
                    case 9:
                        build.append("девятнадцать");
                        break;
                }

                number = 0;
            } else {
                switch (number / 10) {
                    case 2:
                        build.append("двадцать");
                        break;
                    case 3:
                        build.append("тридцать");
                        break;
                    case 4:
                        build.append("сорок");
                        break;
                    case 5:
                        build.append("пятьдесят");
                        break;
                    case 6:
                        build.append("шестьдесят");
                        break;
                    case 7:
                        build.append("семьдесят");
                        break;
                    case 8:
                        build.append("восемьдесят");
                        break;
                    case 9:
                        build.append("девяносто");
                        break;
                }

                build.append(" ");
                number %= 10;
            }
        }

        switch (number) {
            case 1:
                build.append("один");
                break;
            case 2:
                build.append("два");
                break;
            case 3:
                build.append("три");
                break;
            case 4:
                build.append("четыре");
                break;
            case 5:
                build.append("пять");
                break;
            case 6:
                build.append("шесть");
                break;
            case 7:
                build.append("семь");
                break;
            case 8:
                build.append("восемь");
                break;
            case 9:
                build.append("девять");
                break;
        }

        return build.toString();
    }

    // (8/10)
    public static String getSha256Hash(String str) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] bytes = algorithm.digest(str.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, bytes);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        return hexString.toString();
    }

    // (9/10)
    public static String correctTitle(String title) {
        String[] words  = title.toLowerCase().split(" ");
        StringBuilder builder = new StringBuilder();

        for (int index = 0, lenght = words.length; index < lenght; index++) {
            String word = words[index];
            String firstSymbol = String.valueOf(word.charAt(0));

            if (word.contains("-")) {
                String firstPart = word.substring(0, word.indexOf("-"));
                String secondPart = word.substring(word.indexOf("-") + 1, word.length());
                String symbFirstPart = String.valueOf(firstPart.charAt(0));
                String symbSecondPart = String.valueOf(secondPart.charAt(0));

                builder.append(firstPart.replaceFirst(symbFirstPart, symbFirstPart.toUpperCase()) + "-"
                        + secondPart.replaceFirst(symbSecondPart, symbSecondPart.toUpperCase()));

                if (index != lenght - 1) {
                    builder.append(" ");
                }

                continue;
            }

            if ("and".equals(word) || "the".equals(word) || "of".equals(word) || "in".equals(word)) {
                builder.append(word);
            } else {
                builder.append(word.replaceFirst(firstSymbol, firstSymbol.toUpperCase()));
            }

            if (index != lenght - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    // (10/10)
    public static String hexLattice(int n) {
        if (n == 1) {
            return "o";
        }

        if (((3 + Math.sqrt(12 * n - 3)) / 6) % 1 != 0) {
            return "Invalid";
        }

        // n = 3 * R * (R - 1) + 1.
        int R = 0;

        // assert: n - isn't invalid.
        while (R * R - R != (n - 1) / 3) {
            R++;
        }

        StringBuilder builder = new StringBuilder();

        // First part.
        for (int depthCount = 0, maxDepth = R - 1; depthCount < maxDepth; depthCount++) {
            for (int iteration = 0, maxIteration = R - 1 - depthCount; iteration < maxIteration; iteration++) {
                builder.append(" ");
            }

            for (int iteration = 0, maxIteration = R + depthCount; iteration < maxIteration; iteration++) {
                if (iteration + 1 != maxIteration) {
                    builder.append("o ");
                } else {
                    builder.append("o");
                }
            }

            for (int iteration = 0, maxIteration = R - 1 - depthCount; iteration < maxIteration; iteration++) {
                builder.append(" ");
            }

            builder.append("\n");
        }

        // Horizontal diagonal.
        for (int iteration = 0, maxIteration = 2 * R - 1; iteration < maxIteration; iteration++) {
            if (iteration + 1 != maxIteration) {
                builder.append("o ");
            } else {
                builder.append("o");
            }
        }

        builder.append("\n");

        // Second part;
        for (int depthCount = R - 1, temp = 0; depthCount > 0; depthCount--, temp++) {
            for (int iteration = R - depthCount; iteration > 0; iteration--) {
                builder.append(" ");
            }

            for (int iteration = 0, maxIteration = 2 * R - 2 - temp; iteration < maxIteration; iteration++) {
                if (iteration + 1 != maxIteration) {
                    builder.append("o ");
                } else {
                    builder.append("o");
                }
            }

            for (int iteration = R - depthCount; iteration > 0; iteration--) {
                builder.append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}

