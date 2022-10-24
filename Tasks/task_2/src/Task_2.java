public class Task_2 {

    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "repeat");
        System.out.println(repeat("mice", 5)); // ("mice", 5) -> "mmmmmiiiiiccccceeeee"
        System.out.println(repeat("hello", 3)); // ("hello", 3) ➞ "hhheeellllllooo"
        System.out.println(repeat("stop", 1)); // ("stop", 1) ➞ "stop"

        // (2/10)
        prettyPrint("(2/10)", "differenceMaxMin");
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21})); // {10, 4, 1, 4, -10, -50, 32, 21} -> 82
        System.out.println(differenceMaxMin(new int[] {44, 32, 86, 19})); // {44, 32, 86, 19} -> 67

        // (3/10)
        prettyPrint("(3/10)", "isAvgWhole");
        System.out.println(isAvgWhole(new int[] {1, 3})); // {1, 3} -> true
        System.out.println(isAvgWhole(new int[] {1, 2, 3, 4})); // {1, 2, 3, 4} -> false
        System.out.println(isAvgWhole(new int[] {1, 5, 6})); // {1, 5, 6} -> true
        System.out.println(isAvgWhole(new int[] {1, 1, 1})); // {1, 1, 1}} -> true
        System.out.println(isAvgWhole(new int[] {9, 2, 2, 5})); // {9, 2, 2, 5} -> false

        // (4/10)
        prettyPrint("(4/10)", "cumulativeSum");
        int[] ans = cumulativeSum(new int[] {1, 2, 3}); // [1, 2, 3] -> [1, 3, 6]
        for (int i = 0; i < ans.length; i++){
            System.out.print(ans[i] + " ");
        }

        System.out.println();
        ans = cumulativeSum(new int[] {1, -2, 3}); // [1, -2, 3] -> [1, -1, 2]
        for (int i = 0; i < ans.length; i++){
            System.out.print(ans[i] + " ");
        }

        System.out.println();
        ans = cumulativeSum(new int[] {3, 3, -2, 408, 3, 3}); // [3, 3, -2, 408, 3, 3] -> [3, 6, 4, 412, 415, 418]
        for (int i = 0; i < ans.length; i++){
            System.out.print(ans[i] + " ");
        }

        System.out.println();

        // (5/10)
        prettyPrint("(5/10)", "cumulativeSum");
        System.out.println(getDecimalPlaces("43.20")); // "43.20" -> 2
        System.out.println(getDecimalPlaces("400")); // "400" -> 0
        System.out.println(getDecimalPlaces("3.1")); // "3.1" -> 1

        // (6/10)
        prettyPrint("(6/10)", "Fibonacci");
        System.out.println(Fibonacci(3)); // (3) -> 2
        System.out.println(Fibonacci(7)); // (3) -> 13
        System.out.println(Fibonacci(12)); // (3) -> 144

        // (7/10)
        prettyPrint("(7/10)", "isValid");
        System.out.println(isValid("59001")); // "59001" -> true
        System.out.println(isValid("853a7")); // "853a7" -> false
        System.out.println(isValid("732 32")); // "732 32" -> false
        System.out.println(isValid("393939")); // "393939" -> false

        // (8/10)
        prettyPrint("(8/10)", "isStrangePair");
        System.out.println(isStrangePair("sparkling", "groups")); // ("sparkling", "groups") ➞ true
        System.out.println(isStrangePair("bush", "hubris")); // ("bush", "hubris") ➞ false
        System.out.println(isStrangePair("", "")); // ("", "") ➞ true

        // (9/10)
        prettyPrint("(9/10)", "isPrefix && isSuffix");
        System.out.println(isPrefix("automation", "auto-")); // ("automation", "auto-") -> true
        System.out.println(isSuffix("arachnophobia", "-phobia")); // ("arachnophobia", "-phobia") -> true
        System.out.println(isPrefix("retrospect", "sub-")); // ("retrospect", "sub-") -> false
        System.out.println(isSuffix("vocation", "-logy")); // ("vocation", "-logy") -> false

        // (10/10)
        prettyPrint("(10/10)", "boxSeq");
        System.out.println(boxSeq(0)); // (0) -> 0
        System.out.println(boxSeq(1)); // (1) -> 3
        System.out.println(boxSeq(2)); // (2) -> 2
    }

    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (1/10)
    public static String repeat(String arg, int conut) {
        String answer = "";

        for (int i = 0; i < arg.length(); i++) {
            answer += (arg.charAt(i) + "").repeat(conut);
        }

        return answer;
    }

    //(2/10)
    public static int differenceMaxMin(int[] arr) {
        int maxNumber = arr[0];
        int minNumber = maxNumber;

        for (int index = 0; index < arr.length; index++) {
            if (arr[index] > maxNumber)
                maxNumber = arr[index];
            if (arr[index] < minNumber)
                minNumber = arr[index];
        }

        return maxNumber - minNumber;
    }

    // (3/10)
    public static boolean isAvgWhole(int[] arr) {
        int sum = 0;
        int lenght = arr.length;

        for (int index = 0; index < lenght; index++) {
            sum += arr[index];
        }

        return sum % lenght == 0;
    }

    // (4/10)
    public static int[] cumulativeSum(int[] arr) {
        int[] answer = new int[arr.length];

        for (int index = 0; index < arr.length; index++) {
            if (index == 0) {
                answer[0] = arr[0];
                continue;
            }

            int sum = 0;

            for (int index2 = 0; index2 <= index; index2++)
                sum += arr[index2];

            answer[index] = sum;
        }

        return answer;
    }

    // (5/10)
    public static int getDecimalPlaces(String number) {
        for (int index = 0; index < number.length(); index++) {
            if (number.charAt(index) == '.')
                return number.length() - index - 1;
        }

        return 0;
    }

    // (6/10)
    public static long Fibonacci(int n) {
        long[] fibN = new long[n + 1];
        fibN[0] = 0;
        fibN[1] = 1;

        for (int index = 2; index <= n; index++) {
            fibN[index] = fibN[index - 1] + fibN[index - 2];
        }

        return fibN[n];
    }

    // (7/10)
    public static boolean isValid(String input) {
        if (input.length() <= 5) {
            char symbol;

            for (int index = 0; index < input.length(); index++) {
                symbol = input.charAt(index);
                if (symbol == ' ' || !Character.isDigit(symbol))
                    return false;
            }

            return true;
        } else
            return false;
    }

    // (8/10)
    public static boolean isStrangePair(String strOne, String strTwo) {
        if (strOne.length() == 0 && strTwo.length() == 0) {
            return true;
        }

        return strOne.charAt(0) == strTwo.charAt(strTwo.length() - 1)
                && strTwo.charAt(0) == strOne.charAt(strOne.length() - 1);
    }

    // (9/10)
    public static boolean isPrefix(String word, String prefix) {
        for (int index = 0; index < prefix.length() - 1; index++) {
            if (word.charAt(index) != prefix.charAt(index))
                return false;
        }

        return true;
    }

    public static boolean isSuffix(String word, String suffix) {
        final int transition = word.length() - (suffix.length() - 1);

        for (int indexW = transition; indexW < word.length(); indexW++) {
            if (word.charAt(indexW) != suffix.charAt(indexW - transition + 1))
                return false;
        }

        return true;
    }

    // (10/10)
    public static int boxSeq(int n) {
        int count = 0;

        for (int step = 0; step <= n; step++) {
            if (step % 2 == 0) {
                if (step == 0)
                    count = 0;
                else
                    count -= 1;
            } else
                count += 3;
        }

        return count;
    }
}

