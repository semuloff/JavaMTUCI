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
        System.out.println(checkPerfect(6)); // (6) -> true
        System.out.println(checkPerfect(28)); // (28) -> true
        System.out.println(checkPerfect(496)); // (496) -> true
        System.out.println(checkPerfect(12)); // (12) -> false
        System.out.println(checkPerfect(97)); // (97) -> false

        // (4/10)
        prettyPrint("(4/10)", "flipEndChars");
        System.out.println(flipEndChars("Cat, dog, and mouse.")); // ("Cat, dog, and mouse.") -> ".at, dog, and mouseC"
        System.out.println(flipEndChars("ada")); // ("ada") -> "Two's a pair."
        System.out.println(flipEndChars("Ada")); // ("Ada") -> "adA"
        System.out.println(flipEndChars("z")); // ("z") -> "Incompatible."

        // (5/10)
        prettyPrint("(5/10)", "isValidHexCode");
        System.out.println(isValidHexCode("#CD5C5C")); // ("#CD5C5C") -> true
        System.out.println(isValidHexCode("#EAECEE")); // ("#EAECEE") -> true
        System.out.println(isValidHexCode("#eaecee")); // ("#eaecee") -> true
        System.out.println(isValidHexCode("#CD5C58C")); // ("#CD5C58C") -> false
        System.out.println(isValidHexCode("#CD5C5Z")); // ("#CD5C5Z") -> false
        System.out.println(isValidHexCode("#CD5C&C")); // ("#CD5C&C") -> false
        System.out.println(isValidHexCode("CD5C5C")); // ("CD5C5C") -> false

        // (6/10)
        prettyPrint("(6/10)", "same");

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
    public static boolean checkPerfect(int number) {
        int sum = 0;

        for (int i = 1; i < number; i++) {
            if (number % i == 0)
                sum += i;
        }

        return sum == number;
    }

    // (4/10)
    public static String flipEndChars(String line) {
        String newLine = "";

        if (line.length() < 2)
            return "Incompatible.";
        if (line.charAt(0) == line.charAt(line.length() - 1))
            return "Two's a pair.";

        newLine += line.charAt(line.length() - 1);
        for (int index = 1; index < line.length() - 1; index++) {
            newLine += line.charAt(index);
        }
        newLine += line.charAt(0);

        return newLine;
    }

    // (5/10)
    public static boolean isValidHexCode(String code) {
        char symbol;

        if (code.length() - 1 != 6 || code.charAt(0) != '#')
            return false;

        for (int index = 1; index < code.length(); index++) {
            symbol = code.charAt(index);
            if (!((((int) 'A' <= (int) symbol && (int) symbol <= (int) 'F')
                    || ((int) 'a' <= (int) symbol && (int) symbol <= (int) 'f')
                    || ((int) symbol >= (int) '0' && (int) symbol <= (int) '9'))))
                return false;
        }

        return true;
    }

    // (6/10)
    public static void same(int[] arr1, int[] arr2){

    }
}

