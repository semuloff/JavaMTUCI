package Tasks.task_6.src;

import java.util.*;

public class Task_6 {
    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "bell");
        System.out.println(bell(1)); // 1 -> 1
        System.out.println(bell(2)); // 2 -> 2
        System.out.println(bell(3)); // 3 -> 5

        // (2/10)
        prettyPrint("(2/10)", "translateWord & translateSentence");
        System.out.println(translateWord("flag")); // "flag" -> "agflay"
        System.out.println(translateWord("Apple")); // "Apple" -> "Appleyay"
        System.out.println(translateWord("button")); // "button" -> "uttonbay"
        System.out.println(translateWord("")); // "" -> ""
        System.out.println(translateSentence("I like to eat honey waffles.")); // -> "Iyay ikelay otay eatyay oneyhay afflesway."
        System.out.println(translateSentence("Do you think it is going to rain today?")); // -> "Oday youyay inkthay ityay isyay oinggay otay ainray odaytay?"


        // (3/10)
        prettyPrint("(3/10)", "validColor");
        System.out.println(validColor("rgb(0,0,0)")); // "rgb(0,0,0)" -> true
        System.out.println(validColor("rgb(0,,0)")); // "rgb(0,,0)" -> false
        System.out.println(validColor("rgb(255,256,255)")); // "rgb(255,256,255)" -> false
        System.out.println(validColor("rgba(0,0,0,0.123456789)")); // "rgba(0,0,0,0.123456789)" -> true

        // (4/10)
        prettyPrint("(4/10)", "stripUrlParams");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2")); // -> "https://edabit.com?a=2&b=2"
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"})); // -> "https://edabit.com?a=2"
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"a", "b"})); // -> "https://edabit.com"

        // (5/10)
        prettyPrint("(5/10)", "getHashTags");
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade")); // -> ["#avocado", "#became", "#global"]
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")); // -> ["#christmas", "#probably", "#will"]
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")); // -> ["#surprise", "#parents", "#fruit"]
        System.out.println(getHashTags("Visualizing Science")); // -> ["#visualizing", "#science"]

        // (6/10)
        prettyPrint("(6/10)", "ulam");
        System.out.println(ulam(4)); // 4 -> 4
        System.out.println(ulam(9)); // 9 -> 16
        System.out.println(ulam(206)); // 206 -> 1856

        // (7/10)
        prettyPrint("(7/10)", "longestNonrepeatingSubstring");
        System.out.println(longestNonrepeatingSubstring("abcabcbb")); // "abcabcbb" -> "abc"
        System.out.println(longestNonrepeatingSubstring("aaaaaa")); // "aaaaaa" -> "a"
        System.out.println(longestNonrepeatingSubstring("abcde")); // "abcde" -> "abcde"
        System.out.println(longestNonrepeatingSubstring("abcda")); // "abcda" -> "abcd"

        // (8/10)
        prettyPrint("(8/10)", "convertToRoman");
        System.out.println(convertToRoman(2)); // 2 -> "II"
        System.out.println(convertToRoman(12)); // 12 -> "XII"
        System.out.println(convertToRoman(16)); // 16 -> "XVI"

        // (9/10)
        prettyPrint("(9/10)", "formula");
        System.out.println(formula("6 * 4 = 24")); // "6 * 4 = 24" -> true
        System.out.println(formula("18 / 17 = 2")); // "18 / 17 = 2" -> false
        System.out.println(formula("16 * 10 = 160 = 14 + 120")); // "16 * 10 = 160 = 14 + 120" -> false

        // (10/10)
        prettyPrint("(10/10)", "palindromedescendant");
        System.out.println(palindromeDescendant(11211230)); // 11211230 -> true
        System.out.println(palindromeDescendant(11211230)); // 11211230 -> true
        System.out.println(palindromeDescendant(13001120)); // 13001120 -> true
        System.out.println(palindromeDescendant(23336014)); // 23336014 -> true
        System.out.println(palindromeDescendant(11)); // 11 -> true
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
    public static boolean validColor(String quess) {
        if (!"rgb".equals(quess.substring(0, 3))) {
            return false;
        }

        int n = -1;
        String[] args = new String[]{"", "", "", ""};

        for (char symbol : quess.toCharArray()) {
            if (symbol == '(') {
                n = 0;
            } else if (symbol == ')') {
                n = 4;
            } else if (symbol == ',') {
                n++;
            } else if (n >= 0 && n <= 3) {
                args[n] += symbol;
            }
        }

        for (int i = 0; i <= 2; i++)
            if (args[i].length() == 0) {
                return false;
            } else if (Integer.parseInt(args[i]) < 0 || Integer.parseInt(args[i]) > 255) {
                return false;
            }

        boolean isRGBA = quess.charAt(3) == 'a';
        if (isRGBA) {
            if (Float.parseFloat(args[3]) < 0 || Float.parseFloat(args[3]) > 1) {
                return false;
            }
        }

        return true;
    }

    // (4/10)
    public static String stripUrlParams(String url) {
        String[] parts = url.split("\\?");

        if (parts.length > 1) {
            String[] params = parts[1].split("&");
            HashMap<String,Integer> keyValue = new HashMap<>();

            for (int index = 0, lenght = params.length; index < lenght; index++) {
                String[] splittedKeyValue = params[index].split("=");
                String key = splittedKeyValue[0];
                int value = Integer.valueOf(splittedKeyValue[1]);

                if (!keyValue.containsKey(key)) {
                    keyValue.put(key, value);
                } else if (value > keyValue.get(key)) {
                    keyValue.put(key, value);
                }
            }

            return parts[0] + "?" + String.join("&",keyValue.toString().replace("{", "")
                    .replace("}", "").split(", "));
        } else {
            return url;
        }
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {
        String[] parts = url.split("\\?");
        String filter = String.join("", paramsToStrip);

        if (parts.length > 1) {
            String[] params = parts[1].split("&");
            HashMap<String,Integer> keyValue = new HashMap<>();

            for (int index = 0, lenght = params.length; index < lenght; index++) {
                String[] splittedKeyValue = params[index].split("=");
                String key = splittedKeyValue[0];
                int value = Integer.valueOf(splittedKeyValue[1]);

                if (filter.contains(key)) {
                    continue;
                }

                if (!keyValue.containsKey(key)) {
                    keyValue.put(key, value);
                } else if (value > keyValue.get(key)){
                    keyValue.put(key, value);
                }
            }

            return parts[0] + ((keyValue.size() > 0) ? "?" : "") + String.join("&",keyValue.toString().replace("{", "")
                    .replace("}", "").split(", "));
        } else {
            return url;
        }
    }

    // (5/10)
    public static LinkedList<String> getHashTags(String title) {
        LinkedList<String> words = new LinkedList<>(List.of(title.toLowerCase().
                replaceAll("[?!,\\.]", "").split("\s+")));
        words.sort(Comparator.comparingInt(String::length).reversed());

        LinkedList<String> hashTags = new LinkedList<>();

        for (String word : words) {
            if (hashTags.size() < 3) {
                hashTags.add("#" + word);
            } else {
                break;
            }
        }

        return hashTags;
    }

    // (6/10)
    public static int ulam (int number) {
        ArrayList<Integer> ulamNumbers = new ArrayList<>(Arrays.asList(1, 2));

        if (number == 1) {
            return ulamNumbers.get(0);
        }

        if (number == 2) {
            return ulamNumbers.get(1);
        }

        int nextUlam = 3;

        while (true) {
            if (number == ulamNumbers.size()) {
                return ulamNumbers.get(number - 1);
            }

            int count = 0;
            for (int j = 0; j < ulamNumbers.size() - 1; j++) {
                for (int k = j + 1; k < ulamNumbers.size(); k++) {
                    if (ulamNumbers.get(j) + ulamNumbers.get(k) == nextUlam) {
                        count++;
                    }

                    if (count > 1) {
                        break;
                    }
                }

                if (count > 1) {
                    break;
                }
            }

            if (count == 1) {
                ulamNumbers.add(nextUlam);
            }

            nextUlam++;
        }
    }

    // (7/10)
    public static String longestNonrepeatingSubstring(String line) {
        ArrayList<String> digits = new ArrayList<>(List.of(line.split("")));
        LinkedHashSet<String> totalLongest = new LinkedHashSet<>();
        LinkedHashSet<String> currentLongest = new LinkedHashSet<>();

        for (String element: digits) {
            if (!currentLongest.contains(element)){
                currentLongest.add(element);
                continue;
            }

            if (currentLongest.size() > totalLongest.size()){
                totalLongest.clear();
                totalLongest.addAll(currentLongest);
            }

            currentLongest.clear();
            currentLongest.add(element);
        }

        if (currentLongest.size() > totalLongest.size()){
            totalLongest.clear();
            totalLongest.addAll(currentLongest);
        }

        return totalLongest.toString();
    }

    // (8/10)
    public static String convertToRoman(int number) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000] + hundreds[(number % 1000) / 100] + tens[(number % 100) / 10] + units[number % 10];
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
    public static boolean palindromeDescendant(long number) {
        String s = String.valueOf(number);

        if (s.length() <= 1) {
            return false;
        }

        if (isPalindrome(s)) {
            return true;
        }

        return palindromeDescendant(Long.parseLong(makeSumOfPairs(s)));
    }

    public static boolean isPalindrome(String item) {
        return item.equals(new StringBuilder(item).reverse().toString());
    }

    public static String makeSumOfPairs(String number) {
        String[] parts = number.split("");
        ArrayList<String> newNum = new ArrayList<>();

        for (int index = 0, limit = parts.length - 1; index < limit; index += 2){
            newNum.add(String.valueOf(Integer.parseInt(parts[index]) + Integer.parseInt(parts[index + 1])));
        }

        return String.join("", newNum);
    }
}
