package Tasks.task_6.src;

import java.util.*;

public class Task_6 {
    public static void main(String[] args) {
        // (1/10)
//        prettyPrint("(1/10)", "bell");
//        System.out.println(bell(1)); // 1 -> 1
//        System.out.println(bell(2)); // 2 -> 2
//        System.out.println(bell(3)); // 3 -> 5

        // (2/10)
//        prettyPrint("(2/10)", "translateWord & translateSentence");
//        System.out.println(translateWord("flag")); // "flag" -> "agflay"
//        System.out.println(translateWord("Apple")); // "Apple" -> "Appleyay"
//        System.out.println(translateWord("button")); // "button" -> "uttonbay"
//        System.out.println(translateWord("")); // "" -> ""
//        System.out.println(translateSentence("I like to eat honey waffles.")); // -> "Iyay ikelay otay eatyay oneyhay afflesway."
//        System.out.println(translateSentence("Do you think it is going to rain today?")); // -> "Oday youyay inkthay ityay isyay oinggay otay ainray odaytay?"


        // (3/10)
//        prettyPrint("(3/10)", "validColor");


        // (9/10)
//        prettyPrint("(9/10)", "formula");
//        System.out.println(formula("6 * 4 = 24")); // "6 * 4 = 24" -> true
//        System.out.println(formula("18 / 17 = 2")); // "18 / 17 = 2" -> false
//        System.out.println(formula("16 * 10 = 160 = 14 + 120")); // "16 * 10 = 160 = 14 + 120" -> false

        // (10/10)
//        prettyPrint("(10/10)", "palindromedescendant");
//        System.out.println(palindromeDescendant(11211230)); // 11211230 -> true
//        System.out.println(palindromeDescendant(13001120)); // 13001120 -> true
//        System.out.println(palindromeDescendant(23336014)); // 23336014 -> true
//        System.out.println(palindromeDescendant(11)); // 11 -> true
    }
    
    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (1/10)
    public static int bell(int n) {
        int[][] bell = new int[n + 1][n + 1];
        // Initial condition.
        bell[0][0] = 1;

        // Matrix of Stirling numbers.
        for (int row = 1, rowLimit = n + 1; row < rowLimit; row++) {
            for (int column = 0, columnLimit = row; column <= columnLimit; column++) {
                if (column == 0) {
                    bell[row][0] = 0;
                    continue;
                }
                // Formula from https://neerc.ifmo.ru/wiki/index.php?title=%D0%A7%D0%B8%D1%81%D0%BB%D0%B0_%D0%91%D0%B5%D0%BB%D0%BB%D0%B0.
                bell[row][column] = column * bell[row - 1][column] + bell[row - 1][column - 1];
            }
        }

        int sumOfRow = 0;
        for (int column = 1, limit = n + 1; column < limit; column++) {
            sumOfRow += bell[n][column];
        }
        return sumOfRow;
    }

    // (2/10)
    public static String translateWord(String word) {
        if (word.length() == 0) {
            return word;
        }

        String firstSymbol = word.substring(0, 1);

        if (!firstSymbol.matches("[aeiouyAEIOUY]")) {
            String firstVowel = String.valueOf(word.replaceAll("[^aeiouy]", "").charAt(0));
            if (firstSymbol.matches("^[a-z]")) {
                return word.substring(word.indexOf(firstVowel), word.length()) +
                        word.substring(0, word.indexOf(firstVowel)) + "ay";
            }

            return firstVowel.toUpperCase() + word.substring(word.indexOf(firstVowel) + 1, word.length()) +
                    firstSymbol.toLowerCase() + word.substring(1, word.indexOf(firstVowel)) + "ay";
        }

        return word + "yay";
    }

    public static String translateSentence(String sentence) {
        String[] parts = sentence.split(" ");

        List<String> sentenceWords = new ArrayList<>(Arrays.asList(sentence.split("\\s|\\.|,|\\?|!")));
        sentenceWords.removeIf(String::isEmpty);

        for (int indexWord = 0, lenght = sentenceWords.size(); indexWord < lenght; indexWord++) {
            parts[indexWord] = parts[indexWord].replace(sentenceWords.get(indexWord), translateWord(sentenceWords.get(indexWord)));
        }

        return String.join(" ", parts);
    }

    // (3/10)
    public static void validColor(String rgbValue) {

    }

    // (9/10)
    public static boolean formula(String formula) {
        String[] equations = formula.split("\s+=\s+");
        HashSet<Integer> results = new HashSet<>();

        for (int indexWord = 0, lenght = equations.length; indexWord < lenght; indexWord++) {
            if (!equations[indexWord].contains(" ")) {
                results.add(Integer.parseInt(equations[indexWord]));
                continue;
            }

            String[] parts = equations[indexWord].split("\s+");

            switch (parts[1]) {
                case "*":
                    results.add(Integer.parseInt(parts[0]) * Integer.parseInt(parts[2]));
                    break;
                case "/":
                    results.add(Integer.parseInt(parts[0]) / Integer.parseInt(parts[2]));
                    break;
                case "+":
                    results.add(Integer.parseInt(parts[0]) + Integer.parseInt(parts[2]));
                    break;
                case "-":
                    results.add(Integer.parseInt(parts[0]) - Integer.parseInt(parts[2]));
                    break;
            }
        }

        return (results.size() == 1);
    }

    // (10/10)
    public static boolean palindromeDescendant(int number) {
        // assert: number's lenght is even.
        while (number / 10 > 0) {
            StringBuilder builder = new StringBuilder();

            while (number > 0) {
                int firstPart = number % 10;
                number /= 10;
                builder.append(firstPart + number % 10);
                number /= 10;
            }

            boolean quess = true;
            String line = builder.toString();

            for (int middle = line.length() / 2 - 1, balance = 0; middle >= 0; middle--, balance++) {
                if (!(line.charAt(middle) == line.charAt(middle + 2 * balance + 1))) {
                    quess = false;
                    number = Integer.valueOf(builder.toString());
                }
            }

            return true;
        }

        return false;
    }
}

