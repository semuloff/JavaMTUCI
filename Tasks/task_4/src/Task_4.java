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
        prettyPrint("(3/10)", " toCamelCase & toSnakeCase");
        System.out.println(toCamelCase("hello_edabit")); // toCamelCase("hello_edabit") -> "helloEdabit"
        System.out.println(toSnakeCase("helloEdabit")); // toSnakeCase("helloEdabit") -> "hello_edabit"
        System.out.println(toCamelCase("is_modal_open")); // toCamelCase("is_modal_open") -> "isModalOpen"
        System.out.println(toSnakeCase("getColor")); // toSnakeCase("getColor") -> "get_color"

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

}

