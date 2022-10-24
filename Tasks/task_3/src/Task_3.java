public class Task_3 {

    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "solutions");
        System.out.println(solutions(1, 0, -1)); // (1, 0, -1) -> 2
        System.out.println(solutions(1, 0, 0)); // (1, 0, 0) -> 1
        System.out.println(solutions(1, 0, 1)); // (1, 0, 1) -> 0

        // (2/10)
        prettyPrint("(2/10)", "findZip");
        System.out.println(findZip("all zip files are zipped")); // ("all zip files are zipped") -> 18
        System.out.println(findZip("all zip files are compressed")); // ("all zip files are compressed") -> -1

        // (3/10)
        prettyPrint("(3/10)", "checkPerfect");
    }

    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (1/10)
    public static int solutions(int a, int b, int c) {
        int D = b * b - 4 * a * c;
        if (D > 0)
            return 2;
        else if (D == 0)
            return 1;
        else
            return 0;
    }

    // (2/10)
    public static int findZip(String line) {
        boolean flag = false;

        for (int index = 0; index < line.length(); index++) {
            if ((line.charAt(index) == 'Z' || line.charAt(index) == 'z')
                    && line.charAt(index + 1) == 'i' && line.charAt(index + 2) == 'p') {
                if (flag == false)
                    flag = true;
                else
                    return index;
            }
        }

        return -1;
    }

    // (3/10)
}

