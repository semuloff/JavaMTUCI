import java.util.ArrayList;
import java.util.HashSet;


public class Task_4 {
    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "wordProcessor");
        System.out.println(wordProcessor(10, 7, "hello my name is Bessie and this is my essay"));

        // (2/10)
        prettyPrint("(2/10)", "split");
        System.out.println(split("()()()")); // split("()()()") -> ["()", "()", "()"]
        System.out.println(split("((()))")); // split("((()))") ->
        System.out.println(split("((()))(())()()(()())")); // split("((()))(())()()(()())") -> ["((()))", "(())", "()", "()", "(()())"]
        System.out.println(split("((())())(()(()()))")); // split("((())())(()(()()))") -> ["((())())", "(()(()()))"]

        // (3/10)
        prettyPrint("(3/10)", "toCamelCase & toSnakeCase");
        System.out.println(toCamelCase("hello_edabit")); // toCamelCase("hello_edabit") -> "helloEdabit"
        System.out.println(toSnakeCase("helloEdabit")); // toSnakeCase("helloEdabit") -> "hello_edabit"
        System.out.println(toCamelCase("is_modal_open")); // toCamelCase("is_modal_open") -> "isModalOpen"
        System.out.println(toSnakeCase("getColor")); // toSnakeCase("getColor") -> "get_color"

        // (4/10)
        prettyPrint("(4/10)", "overTime");
        System.out.println(overTime(new float[] {9, 17, 30, 1.5f})); // [9, 17, 30, 1.5] -> "$240.00"
        System.out.println(overTime(new float[] {16, 18, 30, 1.8f})); // [16, 18, 30, 1.8] -> "$84.00"
        System.out.println(overTime(new float[] {13.25f, 15, 30, 1.5f})); // [13.25, 15, 30, 1.5] -> "$52.50"

        // (5/10)
        prettyPrint("(5/10)", "BMI");
        System.out.println(BMI("205 pounds", "73 inches")); // ("205 pounds", "73 inches") -> "27.0 Overweight"
        System.out.println(BMI("55 kilos", "1.65 meters")); // ("55 kilos", "1.65 meters") -> "20.2 Normal weight"
        System.out.println(BMI("154 pounds", "2 meters")); // ("154 pounds", "2 meters") -> "17.5 Underweight"

        // (6/10)
        prettyPrint("(6/10)", "bugger");
        System.out.println(bugger(39)); // (39) -> 3
        System.out.println(bugger(999)); // (999) -> 4
        System.out.println(bugger(4)); // (4) -> 0

        // (7/10)
        prettyPrint("(7/10)", "toStarShorthand");
        System.out.println(toStarShorthand("abbccc")); // ("abbccc") ➞ "ab*2c*3"
        System.out.println(toStarShorthand("77777geff")); // ("77777geff") ➞ "7*5gef*2"
        System.out.println(toStarShorthand("abc")); // ("abc") ➞ "abc"
        System.out.println(toStarShorthand("")); // ("") ➞ ""

        // (8/10)
        prettyPrint("(8/10)", "doesRhyme");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham.")); // -> true
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM")); // -> true
        System.out.println(doesRhyme("You are off to the races", "a splendid day.")); // -> false
        System.out.println(doesRhyme("and frequently do?", "you gotta move.")); // -> false

        // (9/10)
        prettyPrint("(9/10)", "trouble");
        System.out.println(trouble(451999277, 41177722899L)); // (451999277, 41177722899) -> True
        System.out.println(trouble(1222345, 12345)); // (1222345, 12345) -> False
        System.out.println(trouble(666789, 12345667)); // (666789, 12345667) -> True
        System.out.println(trouble(33789, 12345337)); // (33789, 12345337) -> False

        // (10/10)
        prettyPrint("(10/10)", "countUniqueBooks");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A')); // ("AZYWABBCATTTA", 'A') -> 4
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$')); // "$AA$BBCATT$C$$B$", '$') -> 3
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z')); // ("ZZABCDEF", 'Z') -> 0
    }
    
    // PrettyPrint
    public static void prettyPrint(String task, String functionName) {
        System.out.println("\n~~~~~~ " + task + ": " + functionName + " ~~~~~~");
    }

    // (1/10)
    public static String wordProcessor(int n, int k, String line) {
        String words[] = new String[n];
        int halt = 0;
        String word = "";

        // find each word in a string and put it in an array.
        for (int indexWords = 0; indexWords < n; indexWords++) {
            // find the word.
            for (int indexL = halt; indexL < line.length(); indexL++) {
                if (line.charAt(indexL) != ' ') {
                    word += line.charAt(indexL);
                    words[indexWords] = word;
                }
                else {
                    halt = indexL + 1;
                    word = "";
                    break;
                }
            }
        }

        // concatenate words that match the condition.
        String answer = "";
        int symbolsLenght = 0;

        for (int indexWords = 0; indexWords < words.length; indexWords++) {
            if (symbolsLenght + words[indexWords].length() <= k) {
                answer += words[indexWords] + " ";
                symbolsLenght += words[indexWords].length();
            } else {
                answer += "\n" + words[indexWords] + " ";
                symbolsLenght = words[indexWords].length();
            }
        }

        return answer;
    }

    // (2/10)
    public static ArrayList<String> split(String line) {
        int leftSide = 0;
        int rightSide = 0;
        String pair = "";
        ArrayList<String> pairs = new ArrayList<>();

        for (int index = 0; index < line.length(); index++) {
            if (line.charAt(index) == '(') {
                pair += '(';
                leftSide++;
            } else {
                pair += ')';
                rightSide++;
                if (leftSide == rightSide) {
                    pairs.add(pair);
                    pair = "";
                    leftSide = rightSide = 0;
                }
            }
        }

        return pairs;
    }

    // (3/10)
    public static String toCamelCase(String line) {
        String camelCaseLine = "";
        boolean flag = false;

        for (int index = 0; index < line.length(); index++) {
            if (line.charAt(index) == '_')
                flag = true;
            else if (flag) {
                camelCaseLine += Character.toUpperCase(line.charAt(index));
                flag = false;
            } else
                camelCaseLine += line.charAt(index);
        }

        return camelCaseLine;
    }

    public static String toSnakeCase(String line) {
        String snakeCaseLine = "";

        for (int index = 0; index < line.length(); index++) {
            if ((int) 'A' <= (int) line.charAt(index) && (int) line.charAt(index) <= (int) 'Z') {
                snakeCaseLine += "_" + Character.toLowerCase(line.charAt(index));
            } else
                snakeCaseLine += line.charAt(index);
        }

        return snakeCaseLine;
    }

    // (4/10)
    public static String overTime(float data[]) {
        /** [0] - The begining of the work day.
         * [1] - End of the working day.
         * [2] - Hourly rate.
         * [3] - Overtime multiplier.
         * **/
        float payment = 0;

        if (data[0] >= 9f && data[1] <= 17f)
            payment = (data[1] - data[0]) * data[2];
        else if (data[0] >= 9f && data[1] > 17f)
            payment = (17f - data[0]) * data[2] + (data[1] - 17f) * data[2] * data[3];
        else payment = (data[1] - 17f) * data[2] * data[3];


        return "$" + Float.toString(payment) + "0";
    }

    // (5/10)
    public static String BMI(String massLine, String lenghtLine) {
        String lenghtUnit = "";
        String massUnit = "";
        String lenghtL = "";
        String massL = "";
        boolean flag = false;

        // determine the weight.
        for (int index = 0; index < massLine.length(); index++) {
            if (massLine.charAt(index) == ' ') {
                flag = true;
            } else if (flag) {
                massUnit += massLine.charAt(index);
            } else
                massL += massLine.charAt(index);
        }

        float mass = Float.parseFloat(massL);

        if (massUnit.equals("pounds"))
            mass /= 2.205;

        flag = false;

        // determine height in meters.
        for (int index = 0; index < lenghtLine.length(); index++) {
            if (lenghtLine.charAt(index) == ' ') {
                flag = true;
            } else if (flag) {
                lenghtUnit += lenghtLine.charAt(index);
            } else
                lenghtL += lenghtLine.charAt(index);
        }

        float lenght = Float.parseFloat(lenghtL);

        if (lenghtUnit.equals("inches"))
            lenght /= 39.37;

        // find BMI.
        float BMI = mass / (lenght * lenght);

        if (BMI < 18.5)
            return Float.toString(((float) Math.round(BMI * 10)) / 10) + " Underweight";
        else if (BMI >= 25)
            return Float.toString(((float) Math.round(BMI * 10)) / 10) + " Normal weight";
        else
            return Float.toString(((float) Math.round(BMI * 10)) / 10) + " Overweight";
    }

    // (6/10)
    public static int bugger(int numb) {
        int count = 0;
        int product;
        String numbToString;

        while (numb / 10 > 0) {
            product = 1;
            numbToString = Integer.toString(numb);

            for (int index = 0; index < numbToString.length(); index++) {
                product *= Integer.parseInt(Character.toString(numbToString.charAt(index)));
            }

            count += 1;
            numb = product;
        }

        return count;
    }

    // (7/10)
    public static String toStarShorthand(String line) {
        int count = 1;
        String symbol = "";
        String starShorthand = "";

        for (int index = 0; index < line.length(); index++) {
            if (index == 0) {
                symbol = Character.toString(line.charAt(0));
                continue;
            }

            if (Character.toString(line.charAt(index)).equals(symbol))
                count += 1;
            else {
                if (count == 1) {
                    starShorthand += symbol;
                } else
                    starShorthand += symbol + "*" + count;

                count = 1;
                symbol = Character.toString(line.charAt(index));
            }
        }

        if (count == 1)
            starShorthand += symbol;
        else
            starShorthand += symbol + "*" + count;

        return starShorthand;
    }

    // (8/10)
    public static boolean doesRhyme(String lineOne, String lineTwo) {
        String[] splitedLineOne = lineOne.toLowerCase().split(" ");
        String[] splitedLineTwo = lineTwo.toLowerCase().split(" ");
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'y'};

        // check for the presence of two vowels at the same time in strings in a loop.
        for (int charIndex = 0; charIndex < vowels.length; charIndex++) {
            if (splitedLineOne[splitedLineOne.length - 1].contains(Character.toString(vowels[charIndex]))) {
                if (!splitedLineTwo[splitedLineTwo.length - 1].contains(Character.toString(vowels[charIndex])))
                    return false;
            } else if (splitedLineTwo[splitedLineTwo.length - 1].contains(Character.toString(vowels[charIndex]))) {
                if (!splitedLineOne[splitedLineOne.length - 1].contains(Character.toString(vowels[charIndex])))
                    return false;
            }
        }

        return true;
    }

    // (9/10)
    public static boolean trouble(long numberOne, long numberTwo) {
        int count;
        char symbol;
        boolean flag;

        String toStringNumbOne = Long.toString(numberOne);
        String toStringNumbTwo = Long.toString(numberTwo);

        // loop through each character of the number.
        for (int indexNumbOne = 0; indexNumbOne < toStringNumbOne.length(); indexNumbOne++) {
            symbol = toStringNumbOne.charAt(indexNumbOne);
            count = 0;
            flag = false;

            // from the selected number, we are looking for the desired number of repetitions in a row, that is, 3 repetitions.
            for (int indexSymb = indexNumbOne; indexSymb < toStringNumbOne.length(); indexSymb++) {
                if (toStringNumbOne.charAt(indexSymb) == symbol)
                    count++;

                if (count == 3) {
                    flag = true;
                    break;
                }
            }

            // looking for the required number of repetitions in a row of this character in the second line.
            if (flag) {
                count = 0;
                for (int indexNumbTwo = 0; indexNumbTwo < toStringNumbTwo.length(); indexNumbTwo++) {
                    if (toStringNumbTwo.charAt(indexNumbTwo) == symbol)
                        count++;

                    if (count == 2)
                        return true;
                }
            }
        }

        return false;
    }

    // (10/10)
    public static int countUniqueBooks(String stringSequence, char bookEnd) {
        boolean flag = false;
        HashSet<String> uniqueBooks = new HashSet<String>();

        // loop through the string.
        for (int indexBooks = 0; indexBooks < stringSequence.length(); indexBooks++) {
            // if we have reached the border of the pair.
            if (flag && stringSequence.charAt(indexBooks) == bookEnd) {
                flag = false;
                continue;
            }

            // if there is a pair, then add to the collection.
            if (flag) {
                uniqueBooks.add(Character.toString(stringSequence.charAt(indexBooks)));
            } else {
                // if found the first part of the pair.
                if (stringSequence.charAt(indexBooks) == bookEnd) {
                    // loop through, hoping to find the second part of the pair starting from the position of the first part of the pair + 1.
                    for (int indexSecondPart = indexBooks + 1; indexSecondPart < stringSequence.length(); indexSecondPart++) {
                        if (stringSequence.charAt(indexSecondPart) == bookEnd) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag)
                        continue;
                }
            }
        }

        return uniqueBooks.size();
    }
}

