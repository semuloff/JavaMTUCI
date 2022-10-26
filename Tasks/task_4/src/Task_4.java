public class Task_4 {

    public static void main(String[] args) {
        // (1/10)
        prettyPrint("(1/10)", "wordProcessor");
        System.out.println(wordProcessor(10, 7,"hello my name is Bessie and this is my essay"));

        // (2/10)
        prettyPrint("(2/10)", "split");
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

}

