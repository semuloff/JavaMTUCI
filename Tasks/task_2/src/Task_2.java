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
        System.out.println(cumulativeSum(new int[] {1, 2, 3})); // [1, 2, 3] -> [1, 3, 6]
        System.out.println(cumulativeSum(new int[] {1, -2, 3})); // [1, -2, 3] -> [1, -1, 2]
        System.out.println(cumulativeSum(new int[] {3, 3, -2, 408, 3, 3})); // [3, 3, -2, 408, 3, 3] -> [3, 6, 4, 412, 415, 418]

        // (5/10)
        prettyPrint("(5/10)", "cumulativeSum");
        System.out.println(getDecimalPlaces("43.20")); // "43.20" -> 2
        System.out.println(getDecimalPlaces("400")); // "400" -> 0
        System.out.println(getDecimalPlaces("3.1")); // "3.1" -> 1

        // (6/10)
        prettyPrint("(6/10)", "Fibonacci");
        System.out.println(Fibonacci(3)); // (3) -> 3
        System.out.println(Fibonacci(7)); // (3) -> 13
        System.out.println(Fibonacci(12)); // (3) -> 144

        // (7/10)
        prettyPrint("(7/10)", "isValid");
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

        return (sum % lenght == 0) ? true : false;
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

        // print array's values.
        for (int i=0; i < answer.length; i++) {
            System.out.println(answer[i]);
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
    public static int Fibonacci(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }

    // (7/10)
}

