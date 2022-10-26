import java.util.HashSet;

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
        System.out.println(same(new int[] {1, 3, 4, 4, 4}, new int[] {2, 5, 7})); // ([1, 3, 4, 4, 4], [2, 5, 7]) -> true
        System.out.println(same(new int[] {9, 8, 7, 6}, new int[] {4, 4, 3, 1})); // ([9, 8, 7, 6], [4, 4, 3, 1]) -> false
        System.out.println(same(new int[] {2}, new int[] {3, 3, 3, 3, 3})); // ([2], [3, 3, 3, 3, 3]) -> true

        // (7/10)
        prettyPrint("(7/10)", "isKaprekar");
        System.out.println(isKaprekar(3)); // (3) -> false
        System.out.println(isKaprekar(5)); // (5) -> false
        System.out.println(isKaprekar(297)); // (297) -> true

        // (8/10)
        prettyPrint("(8/10)", "longestZero");
        System.out.println(longestZero("01100001011000")); // ("01100001011000") -> "0000"
        System.out.println(longestZero("100100100")); // ("100100100") -> "00"
        System.out.println(longestZero("11111")); // ("11111") -> ""

        // (9/10)
        prettyPrint("(9/10)", "nextPrime");
        System.out.println(nextPrime(12)); // (12) -> 13
        System.out.println(nextPrime(24)); // (24) -> 29
        System.out.println(nextPrime(11)); // (11) -> 11

        // (10/10)
        prettyPrint("(10/10)", "rightTriangle");
        System.out.println(rightTriangle(3,4,5)); // true
        System.out.println(rightTriangle(145,105,100)); // true
        System.out.println(rightTriangle(70,130,110));  // false
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
    public static boolean same(int[] arr1, int[] arr2){
        HashSet<Integer> uniqueArr1 = new HashSet<>();
        HashSet<Integer> uniqueArr2 = new HashSet<>();

        for (int index = 0; index < arr1.length; index++)
            uniqueArr1.add(arr1[index]);

        for (int index = 0; index < arr2.length; index++)
            uniqueArr2.add(arr2[index]);

        return uniqueArr1.size() == uniqueArr2.size();
    }

    // (7/10)
    public static boolean isKaprekar(int number) {
        String tempLine = Integer.toString(number * number);
        String leftSide = "";
        String rightSide = "";

        // splitting into two lexicographic parts.
        if (tempLine.length() % 2 == 0 || (tempLine.length() != 1 && tempLine.length() % 2 == 1)) {
            for (int index = 0; index < tempLine.length() / 2; index++) {
                leftSide += tempLine.charAt(index);
            }

            for (int index = tempLine.length() / 2; index < tempLine.length(); index++) {
                rightSide += tempLine.charAt(index);
            }
        } else {
            leftSide = "0";
            rightSide = tempLine;
        }

        // condition check.
        return (Integer.parseInt(leftSide) + Integer.parseInt(rightSide) == number);
    }

    // (8/10)
    public static String longestZero(String line) {
        int maxLenght = 0;
        int tempLenght = 0;
        String maxLine = "";
        String tempLine = "";

        // iterate over all characters in a string.
        for (int index = 0; index < line.length(); index++) {
            if (line.charAt(index) == '0') {
                tempLenght += 1;
                tempLine += line.charAt(index);
            } else {
                if (tempLenght > maxLenght) {
                    maxLenght = tempLenght;
                    maxLine = tempLine;
                }
                tempLenght = 0;
                tempLine = "";
            }
        }

        return maxLine;
    }

    // (9/10)
    public static int nextPrime(int n) {
        boolean flag = true;

        while (true) {
            flag = true;

            // looking for dividers.
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag == false)
                n += 1;
            else
                break;
        }

        return n;
    }

    // (10/10)
    public static boolean rightTriangle(int x, int y, int z) {
        int temp;

        // Ascending sorted x, y and z.
        if (x < y) {
            temp = x;
            x = y;
            y = temp;
        }
        if (z > x) {
            temp = x;
            x = z;
            z = y;
            y = temp;
        }
        if (z > y) {
            temp = y;
            y = z;
            z = temp;
        }

        return Math.pow(x, 2) == Math.pow(y, 2) + Math.pow(z, 2);
    }
}

