public class Task_1 {
    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "remainder");
        System.out.println(remainder(1, 3)); // (1, 3) -> 1
        System.out.println(remainder(3, 4)); // (3, 4) -> 3
        System.out.println(remainder(-9, 45)); // (-9, 45) -> -9
        System.out.println(remainder(5, 5)); // (5, 5) -> 0

        // (2/10)
        prettyPrint("(2/10)", "triArea");
        System.out.println(triArea(3, 2)); // (3, 2) -> 3
        System.out.println(triArea(7, 4)); // (7, 4) -> 14
        System.out.println(triArea(10, 10)); // (10, 10) -> 50

        // (3/10)
        prettyPrint("(3/10)", "animals");
        System.out.println(animals(2, 3, 5)); // (2, 3, 5) -> 36
        System.out.println(animals(1, 2, 3)); // (1, 2, 3) -> 22
        System.out.println(animals(5, 2, 8)); // (5, 2, 8) -> 50

        // (4/10)
        prettyPrint("(4/10)", "profitableGamble");
        System.out.println(profitableGamble(0.2, 50 ,9)); // (0.2, 5, 9) -> true
        System.out.println(profitableGamble(0.9, 1 ,2)); // (0.9, 1, 2) -> false
        System.out.println(profitableGamble(0.9, 3 ,2)); // (0.9, 3, 2) -> true

        // (5/10)
        prettyPrint("(5/10)", "operation");
        System.out.println(operation(24, 15, 9)); // (24, 15, 9) -> "subtracted"
        System.out.println(operation(24, 26, 2)); // (24, 26, 2) -> "subtracted"
        System.out.println(operation(15, 11, 11)); // (15, 11, 11) -> "none"

        // (6/10)
        prettyPrint("(6/10)", "ctoa");
        System.out.println(ctoa('A')); // ('A') -> 65
        System.out.println(ctoa('m')); // ('m') -> 109
        System.out.println(ctoa('[')); // ('[') -> 91
        System.out.println(ctoa('\\')); // ('\') -> 92

        // (7/10)
        prettyPrint("(7/10)", "addUpTo");
        System.out.println(addUpTo(3)); // (3) -> 6
        System.out.println(addUpTo(10)); // (10) -> 55
        System.out.println(addUpTo(7)); // (7) -> 28

        // (8/10)
        prettyPrint("(8/10)", "nextEdge");
        System.out.println(nextEdge(8, 10)); // (8, 10) -> 17
        System.out.println(nextEdge(5, 7)); // (5, 7) -> 11
        System.out.println(nextEdge(9, 2)); // (9, 2) -> 10

        // (9/10)
        prettyPrint("(9/10)", "sumOfCubes");
        System.out.println(sumOfCubes(new int[] {1, 5, 9})); // ([1, 5, 9]) -> 855
        System.out.println(sumOfCubes(new int[] {3, 4, 5})); // ([3, 4, 5]) -> 216
        System.out.println(sumOfCubes(new int[] {2})); // ([2]) -> 8
        System.out.println(sumOfCubes(new int[] {})); // ([]) -> 0

        // (10/10)
        prettyPrint("(10/10)", "abcmath");
        System.out.println(abcmath(42, 5, 10)); // (42, 5, 10) -> false
        System.out.println(abcmath(5, 2, 1)); // (5, 2, 1) -> true
        System.out.println(abcmath(1, 2, 3)); // (1, 2, 3) -> false
    }

    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // Task (1/10)
    public static int remainder(int firstNumb, int secondNumb) {
        return firstNumb % secondNumb;
    }

    // Task (2/10)
    public static int triArea(int base, int height) {
        return (base * height) / 2;
    }

    // Task (3/10)
    public static int animals(int chickens, int cows, int pigs) {
        // number of legs for each animal.
        chickens *= 2;
        cows *= 4;
        pigs *= 4;

        return chickens + cows + pigs;
    }

    // Task (4/10)
    public static boolean profitableGamble(double prob, int prize, int pay) {
        if (prob * prize > pay) {
            return true;
        } else {
            return false;
        }
    }

    // Task (5/10)
    public static String operation(int first_operand, int second_operand, int rezult) {
        String operator = "none";

        if (first_operand + second_operand == rezult) {
            operator = "added";
        }
        if (first_operand - second_operand == rezult || second_operand - first_operand == rezult) {
            operator = "subtracted";
        }
        if (first_operand * second_operand == rezult) {
            operator = "multiplied";
        }
        if (first_operand / second_operand == rezult) {
            operator = "divited";
        }

        return operator;
    }

    // Task (6/10)
    public static int ctoa(char symbol) {
        return (int) symbol;
    }

    // Task (7/10)
    public static int addUpTo(int upHumb) {
        int sum = 0;

        for (int i = 0; i <= upHumb; ++i) {
            sum += i;
        }

        return sum;
    }

    // Task (8/10)
    public static int nextEdge(int firstSide, int secondSide) {
        return firstSide + secondSide - 1;
    }

    // Task (9/10)
    public static int sumOfCubes(int[] arrey) {
        int sum = 0;

        for (int index = 0; index < arrey.length; index++) {
            sum += arrey[index] * arrey[index] * arrey[index];
        }

        return sum;
    }

    // Task (10/10)
    public static boolean abcmath(int a, int b, int c) {
        for (int i = 0; i < b; i++) {
            a += a;
        }

        return a % c == 0;
    }
}
