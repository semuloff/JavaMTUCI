import java.util.ArrayList;

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

}

